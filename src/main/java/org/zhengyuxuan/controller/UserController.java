package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.UserService;
import org.zhengyuxuan.vo.ResultVO;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 * 处理用户注册、登录、登出等请求
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * POST /api/user/register
     */
    @PostMapping("/register")
    @ResponseBody
    public ResultVO<User> register(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        String nickname = params.get("nickname");

        // 参数校验
        if (username == null || username.trim().isEmpty()) {
            return ResultVO.error("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            return ResultVO.error("密码不能为空");
        }
        if (username.length() < 3 || username.length() > 20) {
            return ResultVO.error("用户名长度应为3-20个字符");
        }
        if (password.length() < 6) {
            return ResultVO.error("密码长度不能少于6个字符");
        }

        // 检查用户名是否存在
        if (userService.isUsernameExists(username)) {
            return ResultVO.error("用户名已存在");
        }

        // 注册用户
        User user = userService.register(username, password, nickname);
        if (user != null) {
            user.setPassword(null); // 不返回密码
            return ResultVO.success("注册成功", user);
        }
        return ResultVO.error("注册失败，请稍后重试");
    }

    /**
     * 用户登录
     * POST /api/user/login
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultVO<User> login(@RequestBody Map<String, String> params, HttpSession session) {
        String username = params.get("username");
        String password = params.get("password");

        // 参数校验
        if (username == null || username.trim().isEmpty()) {
            return ResultVO.error("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            return ResultVO.error("密码不能为空");
        }

        // 登录验证
        User user = userService.login(username, password);
        if (user != null) {
            user.setPassword(null); // 不返回密码
            session.setAttribute("currentUser", user);
            return ResultVO.success("登录成功", user);
        }
        return ResultVO.error("用户名或密码错误");
    }

    /**
     * 用户登出
     * GET /api/user/logout
     */
    @GetMapping("/logout")
    @ResponseBody
    public ResultVO<Void> logout(HttpSession session) {
        session.removeAttribute("currentUser");
        session.invalidate();
        return ResultVO.success("登出成功", null);
    }

    /**
     * 获取当前登录用户信息
     * GET /api/user/current
     */
    @GetMapping("/current")
    @ResponseBody
    public ResultVO<User> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            return ResultVO.success(user);
        }
        return ResultVO.error(401, "未登录");
    }

    /**
     * 检查用户名是否可用
     * GET /api/user/check?username=xxx
     */
    @GetMapping("/check")
    @ResponseBody
    public ResultVO<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("available", !userService.isUsernameExists(username));
        return ResultVO.success(result);
    }
}

