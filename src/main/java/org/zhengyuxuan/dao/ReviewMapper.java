package org.zhengyuxuan.dao;

import org.apache.ibatis.annotations.Param;
import org.zhengyuxuan.entity.Review;

import java.util.List;

public interface ReviewMapper { // 评论数据访问接口

    Review selectById(@Param("id") Integer id); // 根据ID查询

    List<Review> selectByMovieId(@Param("movieId") Integer movieId); // 根据电影ID查询

    List<Review> selectByUserId(@Param("userId") Integer userId); // 根据用户ID查询

    Review selectByUserAndMovie(@Param("userId") Integer userId, @Param("movieId") Integer movieId); // 根据用户和电影查询

    int insert(Review review); // 插入评论

    int update(Review review); // 更新评论

    int delete(@Param("id") Integer id); // 删除评论

    Double calculateAvgRating(@Param("movieId") Integer movieId); // 计算平均评分

    Integer countByMovieId(@Param("movieId") Integer movieId); // 统计评论数
}

