package org.zhengyuxuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhengyuxuan.entity.Movie;
import org.zhengyuxuan.mapper.MovieMapper;
import org.zhengyuxuan.service.MovieService;

import java.util.List;

/**
 * 电影服务实现类
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> findAll() {
        return movieMapper.selectAll();
    }

    @Override
    public Movie findById(Integer id) {
        return movieMapper.selectById(id);
    }

    @Override
    public List<Movie> findByCondition(String genre, String region, String keyword) {
        // 处理空字符串为null，便于动态SQL判断
        if (genre != null && genre.trim().isEmpty()) {
            genre = null;
        }
        if (region != null && region.trim().isEmpty()) {
            region = null;
        }
        if (keyword != null && keyword.trim().isEmpty()) {
            keyword = null;
        }
        return movieMapper.selectByCondition(genre, region, keyword);
    }

    @Override
    public List<String> getAllGenres() {
        return movieMapper.selectAllGenres();
    }

    @Override
    public List<String> getAllRegions() {
        return movieMapper.selectAllRegions();
    }
}

