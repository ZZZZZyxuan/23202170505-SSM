package org.zhengyuxuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhengyuxuan.dao.MovieMapper;
import org.zhengyuxuan.entity.Movie;
import org.zhengyuxuan.service.MovieService;
import org.zhengyuxuan.service.ValidationUtil;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> findAll() { // 查询所有电影
        return movieMapper.selectAll();
    }

    @Override
    public Movie findById(Integer id) { // 根据ID查询电影
        return movieMapper.selectById(id);
    }

    @Override
    public List<Movie> findByCondition(String genre, String region, String keyword) { // 条件查询电影
        return movieMapper.selectByCondition(
                ValidationUtil.emptyToNull(genre), // 空字符串转null
                ValidationUtil.emptyToNull(region),
                ValidationUtil.emptyToNull(keyword)
        );
    }

    @Override
    public List<String> getAllGenres() { // 获取所有电影类型
        return movieMapper.selectAllGenres();
    }

    @Override
    public List<String> getAllRegions() { // 获取所有电影地区
        return movieMapper.selectAllRegions();
    }
}

