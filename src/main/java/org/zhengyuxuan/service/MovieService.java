package org.zhengyuxuan.service;

import org.zhengyuxuan.entity.Movie;

import java.util.List;

public interface MovieService { // 电影服务接口

    List<Movie> findAll(); // 查询所有电影

    Movie findById(Integer id); // 根据ID查询

    List<Movie> findByCondition(String genre, String region, String keyword); // 条件查询

    List<String> getAllGenres(); // 获取所有类型

    List<String> getAllRegions(); // 获取所有地区
}

