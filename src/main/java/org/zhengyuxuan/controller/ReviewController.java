package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhengyuxuan.entity.Review;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.ReviewService;
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
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }

        Integer movieId = (Integer) params.get("movieId");
        Integer rating = (Integer) params.get("rating");
        String content = (String) params.get("content");

        // 参数校验
        if (movieId == null) {
            return ResultVO.error("电影ID不能为空");
        }
        if (rating == null || rating < 1 || rating > 5) {
            return ResultVO.error("评分必须在1-5之间");
        }
        if (content == null || content.trim().isEmpty()) {
            return ResultVO.error("评论内容不能为空");
        }
        if (content.length() > 500) {
            return ResultVO.error("评论内容不能超过500字");
        }

        // 添加评论
        Review review = reviewService.addReview(currentUser.getId(), movieId, rating, content);
        if (review != null) {
            return ResultVO.success("评论成功", review);
        }
        return ResultVO.error("评论失败，请稍后重试");
    }

    /**
     * 获取我的评论列表
     * GET /api/review/my
     */
    @GetMapping("/my")
    @ResponseBody
    public ResultVO<List<Review>> getMyReviews(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }

        List<Review> reviews = reviewService.findByUserId(currentUser.getId());
        return ResultVO.success(reviews);
    }

    /**
     * 获取电影的评论列表
     * GET /api/review/movie/{movieId}
     */
    @GetMapping("/movie/{movieId}")
    @ResponseBody
    public ResultVO<List<Review>> getMovieReviews(@PathVariable Integer movieId) {
        List<Review> reviews = reviewService.findByMovieId(movieId);
        return ResultVO.success(reviews);
    }

    /**
     * 检查是否已评价过该电影
     * GET /api/review/check/{movieId}
     */
    @GetMapping("/check/{movieId}")
    @ResponseBody
    public ResultVO<Review> checkReviewed(@PathVariable Integer movieId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResultVO.error(401, "未登录");
        }

        Review review = reviewService.findByUserAndMovie(currentUser.getId(), movieId);
        if (review != null) {
            return ResultVO.success(review);
        }
        return ResultVO.success(null);
    }

    /**
     * 删除评论
     * DELETE /api/review/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultVO<Void> deleteReview(@PathVariable Integer id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResultVO.unauthorized();
        }

        boolean success = reviewService.deleteReview(id, currentUser.getId());
        if (success) {
            return ResultVO.success("删除成功", null);
        }
        return ResultVO.error("删除失败，可能没有权限");
    }
}

