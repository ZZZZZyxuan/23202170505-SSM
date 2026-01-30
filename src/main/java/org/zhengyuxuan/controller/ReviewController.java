package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhengyuxuan.entity.ResultVO;
import org.zhengyuxuan.entity.Review;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.AppConstants;
import org.zhengyuxuan.service.ReviewService;
import org.zhengyuxuan.service.ValidationUtil;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    @ResponseBody
    public ResultVO<Review> addReview(@RequestBody Map<String, Object> params, HttpSession session) { // 发表评论接口
        User currentUser = getCurrentUser(session);
        if (currentUser == null) { // 登录检查
            return ResultVO.unauthorized();
        }

        Integer movieId = (Integer) params.get("movieId");
        Integer rating = (Integer) params.get("rating");
        String content = (String) params.get("content");

        if (movieId == null) { // 参数校验
            return ResultVO.error(AppConstants.ERR_MOVIE_ID_EMPTY);
        }
        String ratingError = ValidationUtil.validateRating(rating);
        if (ratingError != null) {
            return ResultVO.error(ratingError);
        }
        String contentError = ValidationUtil.validateReviewContent(content);
        if (contentError != null) {
            return ResultVO.error(contentError);
        }

        Review review = reviewService.addReview(currentUser.getId(), movieId, rating, content); // 添加评论
        if (review != null) {
            return ResultVO.success(AppConstants.MSG_REVIEW_SUCCESS, review);
        }
        return ResultVO.error(AppConstants.ERR_REVIEW_FAILED);
    }

    @GetMapping("/my")
    @ResponseBody
    public ResultVO<List<Review>> getMyReviews(HttpSession session) { // 获取我的评论列表
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }
        return ResultVO.success(reviewService.findByUserId(currentUser.getId()));
    }

    @GetMapping("/movie/{movieId}")
    @ResponseBody
    public ResultVO<List<Review>> getMovieReviews(@PathVariable Integer movieId) { // 获取电影的评论列表
        return ResultVO.success(reviewService.findByMovieId(movieId));
    }

    @GetMapping("/check/{movieId}")
    @ResponseBody
    public ResultVO<Review> checkReviewed(@PathVariable Integer movieId, HttpSession session) { // 检查是否已评价
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return ResultVO.error(AppConstants.CODE_UNAUTHORIZED, AppConstants.MSG_UNAUTHORIZED);
        }
        Review review = reviewService.findByUserAndMovie(currentUser.getId(), movieId);
        return ResultVO.success(review);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultVO<Void> deleteReview(@PathVariable Integer id, HttpSession session) { // 删除评论接口
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }

        boolean success = reviewService.deleteReview(id, currentUser.getId()); // 执行删除
        if (success) {
            return ResultVO.success(AppConstants.MSG_DELETE_SUCCESS, null);
        }
        return ResultVO.error(AppConstants.ERR_DELETE_FAILED);
    }

    private User getCurrentUser(HttpSession session) { // 从Session获取当前用户
        return (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);
    }
}

