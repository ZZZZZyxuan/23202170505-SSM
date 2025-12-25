package org.zhengyuxuan.dao;

import org.apache.ibatis.annotations.Param;
import org.zhengyuxuan.entity.Review;

import java.util.List;

/**
 * 评论Mapper接口
 */
public interface ReviewMapper {

    /**
     * 根据ID查询评论
     * @param id 评论ID
     * @return 评论信息
     */
    Review selectById(@Param("id") Integer id);

    /**
     * 根据电影ID查询评论列表
     * @param movieId 电影ID
     * @return 评论列表（包含用户信息）
     */
    List<Review> selectByMovieId(@Param("movieId") Integer movieId);

    /**
     * 根据用户ID查询评论列表
     * @param userId 用户ID
     * @return 评论列表（包含电影信息）
     */
    List<Review> selectByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户ID和电影ID查询评论
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return 评论信息
     */
    Review selectByUserAndMovie(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    /**
     * 插入新评论
     * @param review 评论信息
     * @return 影响行数
     */
    int insert(Review review);

    /**
     * 更新评论
     * @param review 评论信息
     * @return 影响行数
     */
    int update(Review review);

    /**
     * 删除评论
     * @param id 评论ID
     * @return 影响行数
     */
    int delete(@Param("id") Integer id);

    /**
     * 计算电影的平均评分
     * @param movieId 电影ID
     * @return 平均评分
     */
    Double calculateAvgRating(@Param("movieId") Integer movieId);

    /**
     * 统计电影的评分人数
     * @param movieId 电影ID
     * @return 评分人数
     */
    Integer countByMovieId(@Param("movieId") Integer movieId);
}

