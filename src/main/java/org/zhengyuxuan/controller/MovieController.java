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

/**
 * 电影控制器
 * 处理电影列表、详情、筛选等请求
 */
@Controller
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    /**
     * 获取电影列表
     * GET /api/movie/list
     */
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<List<Movie>> getMovieList(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword) {
        return ResultVO.success(movieService.findByCondition(genre, region, keyword));
    }

    /**
     * 获取电影详情
     * GET /api/movie/{id}
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResultVO<Map<String, Object>> getMovieDetail(@PathVariable Integer id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return ResultVO.error(AppConstants.ERR_MOVIE_NOT_FOUND);
        }

        Map<String, Object> data = new HashMap<>(4);
        data.put("movie", movie);
        data.put("reviews", reviewService.findByMovieId(id));
        return ResultVO.success(data);
    }

    /**
     * 获取所有电影类型
     * GET /api/movie/genres
     */
    @GetMapping("/genres")
    @ResponseBody
    public ResultVO<List<String>> getAllGenres() {
        return ResultVO.success(movieService.getAllGenres());
    }

    /**
     * 获取所有电影地区
     * GET /api/movie/regions
     */
    @GetMapping("/regions")
    @ResponseBody
    public ResultVO<List<String>> getAllRegions() {
        return ResultVO.success(movieService.getAllRegions());
    }

    /**
     * 获取筛选选项（类型和地区）
     * GET /api/movie/filters
     */
    @GetMapping("/filters")
    @ResponseBody
    public ResultVO<Map<String, List<String>>> getFilters() {
        Map<String, List<String>> filters = new HashMap<>(4);
        filters.put("genres", movieService.getAllGenres());
        filters.put("regions", movieService.getAllRegions());
        return ResultVO.success(filters);
    }
}

