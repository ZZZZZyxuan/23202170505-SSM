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

@Controller
public class PageController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping({"/", "/index"})
    public String index( // 首页-电影列表
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword,
            Model model) {

        model.addAttribute("movies", movieService.findByCondition(genre, region, keyword)); // 电影列表
        model.addAttribute("genres", movieService.getAllGenres()); // 所有类型
        model.addAttribute("regions", movieService.getAllRegions()); // 所有地区

        model.addAttribute("currentGenre", genre); // 当前筛选条件
        model.addAttribute("currentRegion", region);
        model.addAttribute("keyword", keyword);

        return "movie/list";
    }

    @GetMapping("/user/login")
    public String loginPage() { // 登录页面
        return "user/login";
    }

    @GetMapping("/user/register")
    public String registerPage() { // 注册页面
        return "user/register";
    }

    @GetMapping("/user/profile")
    public String profilePage(HttpSession session, Model model) { // 个人中心页面
        User currentUser = getCurrentUser(session);
        if (currentUser == null) { // 登录检查
            return "redirect:/user/login";
        }

        model.addAttribute("myReviews", reviewService.findByUserId(currentUser.getId())); // 我的评论
        model.addAttribute("user", currentUser);
        return "user/profile";
    }

    @GetMapping("/movie/{id}")
    public String movieDetail(@PathVariable Integer id, Model model, HttpSession session) { // 电影详情页面
        Movie movie = movieService.findById(id);
        if (movie == null) { // 电影不存在则跳转首页
            return "redirect:/";
        }

        model.addAttribute("movie", movie); // 电影信息
        model.addAttribute("reviews", reviewService.findByMovieId(id)); // 电影评论

        User currentUser = getCurrentUser(session);
        if (currentUser != null) { // 检查当前用户是否已评价
            model.addAttribute("myReview", reviewService.findByUserAndMovie(currentUser.getId(), id));
        }

        return "movie/detail";
    }

    private User getCurrentUser(HttpSession session) { // 从Session获取当前用户
        return (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);
    }
}

