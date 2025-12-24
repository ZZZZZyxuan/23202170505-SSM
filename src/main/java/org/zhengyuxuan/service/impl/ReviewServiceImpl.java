package org.zhengyuxuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhengyuxuan.entity.Review;
import org.zhengyuxuan.mapper.MovieMapper;
import org.zhengyuxuan.mapper.ReviewMapper;
import org.zhengyuxuan.service.ReviewService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 评论服务实现类
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    @Transactional
    public Review addReview(Integer userId, Integer movieId, Integer rating, String content) {
        // 检查是否已评价过
        Review existingReview = reviewMapper.selectByUserAndMovie(userId, movieId);
        if (existingReview != null) {
            // 如果已存在，则更新
            existingReview.setRating(rating);
            existingReview.setContent(content);
            reviewMapper.update(existingReview);
            // 更新电影评分
            updateMovieRating(movieId);
            return existingReview;
        }

        // 创建新评论
        Review review = new Review(userId, movieId, rating, content);
        reviewMapper.insert(review);

        // 更新电影评分
        updateMovieRating(movieId);

        return review;
    }

    @Override
    public List<Review> findByMovieId(Integer movieId) {
        return reviewMapper.selectByMovieId(movieId);
    }

    @Override
    public List<Review> findByUserId(Integer userId) {
        return reviewMapper.selectByUserId(userId);
    }

    @Override
    public boolean hasReviewed(Integer userId, Integer movieId) {
        return reviewMapper.selectByUserAndMovie(userId, movieId) != null;
    }

    @Override
    public Review findByUserAndMovie(Integer userId, Integer movieId) {
        return reviewMapper.selectByUserAndMovie(userId, movieId);
    }

    @Override
    @Transactional
    public Review updateReview(Integer reviewId, Integer rating, String content) {
        Review review = reviewMapper.selectById(reviewId);
        if (review == null) {
            return null;
        }

        review.setRating(rating);
        review.setContent(content);
        reviewMapper.update(review);

        // 更新电影评分
        updateMovieRating(review.getMovieId());

        return review;
    }

    @Override
    @Transactional
    public boolean deleteReview(Integer reviewId, Integer userId) {
        Review review = reviewMapper.selectById(reviewId);
        if (review == null || !review.getUserId().equals(userId)) {
            return false;
        }

        Integer movieId = review.getMovieId();
        int result = reviewMapper.delete(reviewId);

        if (result > 0) {
            // 更新电影评分
            updateMovieRating(movieId);
            return true;
        }
        return false;
    }

    /**
     * 更新电影的平均评分
     * @param movieId 电影ID
     */
    private void updateMovieRating(Integer movieId) {
        Double avgRating = reviewMapper.calculateAvgRating(movieId);
        Integer ratingCount = reviewMapper.countByMovieId(movieId);

        if (avgRating == null) {
            avgRating = 0.0;
        }
        if (ratingCount == null) {
            ratingCount = 0;
        }

        // 保留一位小数
        BigDecimal avgRatingDecimal = BigDecimal.valueOf(avgRating)
                .setScale(1, RoundingMode.HALF_UP);

        movieMapper.updateRating(movieId, avgRatingDecimal, ratingCount);
    }
}

