package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhengyuxuan.entity.Movie;
import org.zhengyuxuan.entity.Review;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.MovieService;
import org.zhengyuxuan.service.ReviewService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 页面控制器
 * 处理页面跳转请求
 */
@Controller
public class PageController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    /**
     * 首页 - 电影列表
     */
    @GetMapping({"/", "/index"})
    public String index(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword,
            Model model) {

        // 获取电影列表
        List<Movie> movies = movieService.findByCondition(genre, region, keyword);
        model.addAttribute("movies", movies);

        // 获取筛选选项
        model.addAttribute("genres", movieService.getAllGenres());
        model.addAttribute("regions", movieService.getAllRegions());

        // 当前筛选条件
        model.addAttribute("currentGenre", genre);
        model.addAttribute("currentRegion", region);
        model.addAttribute("keyword", keyword);

        return "movie/list";
    }

    /**
     * 登录页面
     */
    @GetMapping("/user/login")
    public String loginPage() {
        return "user/login";
    }

    /**
     * 注册页面
     */
    @GetMapping("/user/register")
    public String registerPage() {
        return "user/register";
    }

    /**
     * 个人中心页面
     */
    @GetMapping("/user/profile")
    public String profilePage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/user/login";
        }

        // 获取我的评论
        List<Review> myReviews = reviewService.findByUserId(currentUser.getId());
        model.addAttribute("myReviews", myReviews);
        model.addAttribute("user", currentUser);

        return "user/profile";
    }

    /**
     * 电影详情页面
     */
    @GetMapping("/movie/{id}")
    public String movieDetail(@PathVariable Integer id, Model model, HttpSession session) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "redirect:/";
        }

        // 获取评论列表
        List<Review> reviews = reviewService.findByMovieId(id);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);

        // 检查当前用户是否已评价
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            Review myReview = reviewService.findByUserAndMovie(currentUser.getId(), id);
            model.addAttribute("myReview", myReview);
        }

        return "movie/detail";
    }
}

