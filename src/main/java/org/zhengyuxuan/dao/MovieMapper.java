package org.zhengyuxuan.dao;

import org.apache.ibatis.annotations.Param;
import org.zhengyuxuan.entity.Movie;

import java.math.BigDecimal;
import java.util.List;

public interface MovieMapper { // 电影数据访问接口

    Movie selectById(@Param("id") Integer id); // 根据ID查询

    List<Movie> selectAll(); // 查询所有电影

    List<Movie> selectByCondition(@Param("genre") String genre, // 条件查询
                                   @Param("region") String region,
                                   @Param("keyword") String keyword);

    List<String> selectAllGenres(); // 查询所有类型

    List<String> selectAllRegions(); // 查询所有地区

    int updateRating(@Param("movieId") Integer movieId, // 更新评分
                     @Param("avgRating") BigDecimal avgRating,
                     @Param("ratingCount") Integer ratingCount);

    int insert(Movie movie); // 插入电影
}

