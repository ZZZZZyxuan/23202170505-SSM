package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhengyuxuan.entity.Movie;
import org.zhengyuxuan.entity.ResultVO;
import org.zhengyuxuan.service.AppConstants;
import org.zhengyuxuan.service.MovieService;
import org.zhengyuxuan.service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/list")
    @ResponseBody
    public ResultVO<List<Movie>> getMovieList( // 获取电影列表接口
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword) {
        return ResultVO.success(movieService.findByCondition(genre, region, keyword));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultVO<Map<String, Object>> getMovieDetail(@PathVariable Integer id) { // 获取电影详情接口
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return ResultVO.error(AppConstants.ERR_MOVIE_NOT_FOUND);
        }

        Map<String, Object> data = new HashMap<>(4); // 封装返回数据
        data.put("movie", movie);
        data.put("reviews", reviewService.findByMovieId(id));
        return ResultVO.success(data);
    }

    @GetMapping("/genres")
    @ResponseBody
    public ResultVO<List<String>> getAllGenres() { // 获取所有电影类型
        return ResultVO.success(movieService.getAllGenres());
    }

    @GetMapping("/regions")
    @ResponseBody
    public ResultVO<List<String>> getAllRegions() { // 获取所有电影地区
        return ResultVO.success(movieService.getAllRegions());
    }

    @GetMapping("/filters")
    @ResponseBody
    public ResultVO<Map<String, List<String>>> getFilters() { // 获取筛选选项
        Map<String, List<String>> filters = new HashMap<>(4);
        filters.put("genres", movieService.getAllGenres());
        filters.put("regions", movieService.getAllRegions());
        return ResultVO.success(filters);
    }
}

