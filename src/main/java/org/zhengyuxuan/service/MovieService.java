package org.zhengyuxuan.service;

import org.zhengyuxuan.entity.Movie;

import java.util.List;

/**
 * 电影服务接口
 */
public interface MovieService {

    /**
     * 查询所有电影
     * @return 电影列表
     */
    List<Movie> findAll();

    /**
     * 根据ID查询电影
     * @param id 电影ID
     * @return 电影信息
     */
    Movie findById(Integer id);

    /**
     * 根据条件查询电影
     * @param genre 类型
     * @param region 地区
     * @param keyword 关键词
     * @return 电影列表
     */
    List<Movie> findByCondition(String genre, String region, String keyword);

    /**
     * 获取所有电影类型
     * @return 类型列表
     */
    List<String> getAllGenres();

    /**
     * 获取所有电影地区
     * @return 地区列表
     */
    List<String> getAllRegions();
}

