package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhengyuxuan.constant.AppConstants;
import org.zhengyuxuan.entity.Review;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.ReviewService;
import org.zhengyuxuan.util.ValidationUtil;
import org.zhengyuxuan.vo.ResultVO;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 评论控制器
 * 处理评论评分相关请求
 */
@Controller
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 发表评论评分
     * POST /api/review/add
     */
    @PostMapping("/add")
    @ResponseBody
    public ResultVO<Review> addReview(@RequestBody Map<String, Object> params, HttpSession session) {
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }

        Integer movieId = (Integer) params.get("movieId");
        Integer rating = (Integer) params.get("rating");
        String content = (String) params.get("content");

        // 参数校验
        if (movieId == null) {
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

        // 添加评论
        Review review = reviewService.addReview(currentUser.getId(), movieId, rating, content);
        if (review != null) {
            return ResultVO.success(AppConstants.MSG_REVIEW_SUCCESS, review);
        }
        return ResultVO.error(AppConstants.ERR_REVIEW_FAILED);
    }

    /**
     * 获取我的评论列表
     * GET /api/review/my
     */
    @GetMapping("/my")
    @ResponseBody
    public ResultVO<List<Review>> getMyReviews(HttpSession session) {
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }
        return ResultVO.success(reviewService.findByUserId(currentUser.getId()));
    }

    /**
     * 获取电影的评论列表
     * GET /api/review/movie/{movieId}
     */
    @GetMapping("/movie/{movieId}")
    @ResponseBody
    public ResultVO<List<Review>> getMovieReviews(@PathVariable Integer movieId) {
        return ResultVO.success(reviewService.findByMovieId(movieId));
    }

    /**
     * 检查是否已评价过该电影
     * GET /api/review/check/{movieId}
     */
    @GetMapping("/check/{movieId}")
    @ResponseBody
    public ResultVO<Review> checkReviewed(@PathVariable Integer movieId, HttpSession session) {
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return ResultVO.error(AppConstants.CODE_UNAUTHORIZED, AppConstants.MSG_UNAUTHORIZED);
        }
        Review review = reviewService.findByUserAndMovie(currentUser.getId(), movieId);
        return ResultVO.success(review);
    }

    /**
     * 删除评论
     * DELETE /api/review/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultVO<Void> deleteReview(@PathVariable Integer id, HttpSession session) {
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }

        boolean success = reviewService.deleteReview(id, currentUser.getId());
        if (success) {
            return ResultVO.success(AppConstants.MSG_DELETE_SUCCESS, null);
        }
        return ResultVO.error(AppConstants.ERR_DELETE_FAILED);
    }

    /**
     * 从Session中获取当前用户
     */
    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);
    }
}

