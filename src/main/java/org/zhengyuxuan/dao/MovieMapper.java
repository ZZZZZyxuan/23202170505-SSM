package org.zhengyuxuan.dao;

import org.apache.ibatis.annotations.Param;
import org.zhengyuxuan.entity.Movie;

import java.math.BigDecimal;
import java.util.List;

/**
 * 电影Mapper接口
 */
public interface MovieMapper {

    /**
     * 根据ID查询电影
     * @param id 电影ID
     * @return 电影信息
     */
    Movie selectById(@Param("id") Integer id);

    /**
     * 查询所有电影
     * @return 电影列表
     */
    List<Movie> selectAll();

    /**
     * 根据条件查询电影（动态SQL）
     * @param genre 类型（可选）
     * @param region 地区（可选）
     * @param keyword 关键词（可选，匹配标题、导演、演员）
     * @return 电影列表
     */
    List<Movie> selectByCondition(@Param("genre") String genre,
                                   @Param("region") String region,
                                   @Param("keyword") String keyword);

    /**
     * 查询所有电影类型
     * @return 类型列表
     */
    List<String> selectAllGenres();

    /**
     * 查询所有电影地区
     * @return 地区列表
     */
    List<String> selectAllRegions();

    /**
     * 更新电影评分
     * @param movieId 电影ID
     * @param avgRating 平均评分
     * @param ratingCount 评分人数
     * @return 影响行数
     */
    int updateRating(@Param("movieId") Integer movieId,
                     @Param("avgRating") BigDecimal avgRating,
                     @Param("ratingCount") Integer ratingCount);

    /**
     * 插入新电影
     * @param movie 电影信息
     * @return 影响行数
     */
    int insert(Movie movie);
}

