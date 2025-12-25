package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhengyuxuan.entity.Movie;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.AppConstants;
import org.zhengyuxuan.service.MovieService;
import org.zhengyuxuan.service.ReviewService;

import javax.servlet.http.HttpSession;

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

        // 获取电影列表和筛选选项
        model.addAttribute("movies", movieService.findByCondition(genre, region, keyword));
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
        User currentUser = getCurrentUser(session);
        if (currentUser == null) {
            return "redirect:/user/login";
        }

        model.addAttribute("myReviews", reviewService.findByUserId(currentUser.getId()));
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

        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviewService.findByMovieId(id));

        // 检查当前用户是否已评价
        User currentUser = getCurrentUser(session);
        if (currentUser != null) {
            model.addAttribute("myReview", reviewService.findByUserAndMovie(currentUser.getId(), id));
        }

        return "movie/detail";
    }

    /**
     * 从Session中获取当前用户
     */
    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);
    }
}

