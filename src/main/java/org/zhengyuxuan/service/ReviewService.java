package org.zhengyuxuan.service;

import org.zhengyuxuan.entity.Review;

import java.util.List;

public interface ReviewService { // 评论服务接口

    Review addReview(Integer userId, Integer movieId, Integer rating, String content); // 发表评论

    List<Review> findByMovieId(Integer movieId); // 根据电影ID查询评论

    List<Review> findByUserId(Integer userId); // 根据用户ID查询评论

    boolean hasReviewed(Integer userId, Integer movieId); // 检查是否已评论

    Review findByUserAndMovie(Integer userId, Integer movieId); // 查询用户对某电影的评论

    Review updateReview(Integer reviewId, Integer rating, String content); // 更新评论

    boolean deleteReview(Integer reviewId, Integer userId); // 删除评论
}

