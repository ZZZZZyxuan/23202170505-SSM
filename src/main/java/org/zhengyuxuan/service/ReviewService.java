package org.zhengyuxuan.service;

import org.zhengyuxuan.entity.Review;

import java.util.List;

/**
 * 评论服务接口
 */
public interface ReviewService {

    /**
     * 发表评论评分
     * @param userId 用户ID
     * @param movieId 电影ID
     * @param rating 评分(1-5)
     * @param content 评论内容
     * @return 评论信息
     */
    Review addReview(Integer userId, Integer movieId, Integer rating, String content);

    /**
     * 根据电影ID获取评论列表
     * @param movieId 电影ID
     * @return 评论列表
     */
    List<Review> findByMovieId(Integer movieId);

    /**
     * 根据用户ID获取评论列表（我的评论）
     * @param userId 用户ID
     * @return 评论列表
     */
    List<Review> findByUserId(Integer userId);

    /**
     * 检查用户是否已评价过该电影
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return true表示已评价
     */
    boolean hasReviewed(Integer userId, Integer movieId);

    /**
     * 获取用户对某电影的评论
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return 评论信息
     */
    Review findByUserAndMovie(Integer userId, Integer movieId);

    /**
     * 更新评论
     * @param reviewId 评论ID
     * @param rating 评分
     * @param content 评论内容
     * @return 更新后的评论
     */
    Review updateReview(Integer reviewId, Integer rating, String content);

    /**
     * 删除评论
     * @param reviewId 评论ID
     * @param userId 用户ID（用于验证权限）
     * @return 是否删除成功
     */
    boolean deleteReview(Integer reviewId, Integer userId);
}

