package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhengyuxuan.constant.AppConstants;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.UserService;
import org.zhengyuxuan.util.ValidationUtil;
import org.zhengyuxuan.vo.ResultVO;

import javax.servlet.http.HttpSession;
import java.util.Collections;
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
        String usernameError = ValidationUtil.validateUsername(username);
        if (usernameError != null) {
            return ResultVO.error(usernameError);
        }
        String passwordError = ValidationUtil.validatePassword(password);
        if (passwordError != null) {
            return ResultVO.error(passwordError);
        }

        // 检查用户名是否存在
        if (userService.isUsernameExists(username)) {
            return ResultVO.error(AppConstants.ERR_USERNAME_EXISTS);
        }

        // 注册用户
        User user = userService.register(username, password, nickname);
        if (user != null) {
            user.setPassword(null); // 不返回密码
            return ResultVO.success(AppConstants.MSG_REGISTER_SUCCESS, user);
        }
        return ResultVO.error(AppConstants.ERR_REGISTER_FAILED);
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
        if (ValidationUtil.isBlank(username)) {
            return ResultVO.error(AppConstants.ERR_USERNAME_EMPTY);
        }
        if (ValidationUtil.isBlank(password)) {
            return ResultVO.error(AppConstants.ERR_PASSWORD_EMPTY);
        }

        // 登录验证
        User user = userService.login(username, password);
        if (user != null) {
            user.setPassword(null); // 不返回密码
            session.setAttribute(AppConstants.SESSION_CURRENT_USER, user);
            return ResultVO.success(AppConstants.MSG_LOGIN_SUCCESS, user);
        }
        return ResultVO.error(AppConstants.ERR_LOGIN_FAILED);
    }

    /**
     * 用户登出
     * GET /api/user/logout
     */
    @GetMapping("/logout")
    @ResponseBody
    public ResultVO<Void> logout(HttpSession session) {
        session.removeAttribute(AppConstants.SESSION_CURRENT_USER);
        session.invalidate();
        return ResultVO.success(AppConstants.MSG_LOGOUT_SUCCESS, null);
    }

    /**
     * 获取当前登录用户信息
     * GET /api/user/current
     */
    @GetMapping("/current")
    @ResponseBody
    public ResultVO<User> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);
        if (user != null) {
            return ResultVO.success(user);
        }
        return ResultVO.error(AppConstants.CODE_UNAUTHORIZED, AppConstants.MSG_UNAUTHORIZED);
    }

    /**
     * 检查用户名是否可用
     * GET /api/user/check?username=xxx
     */
    @GetMapping("/check")
    @ResponseBody
    public ResultVO<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        boolean available = !userService.isUsernameExists(username);
        return ResultVO.success(Collections.singletonMap("available", available));
    }
}

