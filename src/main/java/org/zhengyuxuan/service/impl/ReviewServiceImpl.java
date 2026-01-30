package org.zhengyuxuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhengyuxuan.dao.MovieMapper;
import org.zhengyuxuan.dao.ReviewMapper;
import org.zhengyuxuan.entity.Review;
import org.zhengyuxuan.service.ReviewService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    @Transactional
    public Review addReview(Integer userId, Integer movieId, Integer rating, String content) { // 添加或更新评论
        Review existingReview = reviewMapper.selectByUserAndMovie(userId, movieId); // 检查是否已评论

        Review review;
        if (existingReview != null) { // 更新已存在的评论
            existingReview.setRating(rating);
            existingReview.setContent(content);
            reviewMapper.update(existingReview);
            review = existingReview;
        } else { // 创建新评论
            review = new Review(userId, movieId, rating, content);
            reviewMapper.insert(review);
        }

        updateMovieRating(movieId); // 更新电影平均评分
        return review;
    }

    @Override
    public List<Review> findByMovieId(Integer movieId) { // 根据电影ID查询评论
        return reviewMapper.selectByMovieId(movieId);
    }

    @Override
    public List<Review> findByUserId(Integer userId) { // 根据用户ID查询评论
        return reviewMapper.selectByUserId(userId);
    }

    @Override
    public boolean hasReviewed(Integer userId, Integer movieId) { // 检查用户是否已评论
        return reviewMapper.selectByUserAndMovie(userId, movieId) != null;
    }

    @Override
    public Review findByUserAndMovie(Integer userId, Integer movieId) { // 查询用户对某电影的评论
        return reviewMapper.selectByUserAndMovie(userId, movieId);
    }

    @Override
    @Transactional
    public Review updateReview(Integer reviewId, Integer rating, String content) { // 更新评论
        Review review = reviewMapper.selectById(reviewId);
        if (review == null) {
            return null;
        }

        review.setRating(rating);
        review.setContent(content);
        reviewMapper.update(review);
        updateMovieRating(review.getMovieId()); // 更新电影评分
        return review;
    }

    @Override
    @Transactional
    public boolean deleteReview(Integer reviewId, Integer userId) { // 删除评论
        Review review = reviewMapper.selectById(reviewId);
        if (review == null || !review.getUserId().equals(userId)) { // 权限验证
            return false;
        }

        Integer movieId = review.getMovieId();
        if (reviewMapper.delete(reviewId) > 0) {
            updateMovieRating(movieId); // 更新电影评分
            return true;
        }
        return false;
    }

    private void updateMovieRating(Integer movieId) { // 更新电影平均评分和评分人数
        Double avgRating = Optional.ofNullable(reviewMapper.calculateAvgRating(movieId)).orElse(0.0); // 计算平均分
        Integer ratingCount = Optional.ofNullable(reviewMapper.countByMovieId(movieId)).orElse(0); // 统计人数

        BigDecimal avgRatingDecimal = BigDecimal.valueOf(avgRating)
                .setScale(1, RoundingMode.HALF_UP); // 保留一位小数

        movieMapper.updateRating(movieId, avgRatingDecimal, ratingCount); // 更新数据库
    }
}

