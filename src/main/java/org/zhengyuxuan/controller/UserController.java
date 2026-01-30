package org.zhengyuxuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhengyuxuan.entity.ResultVO;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.AppConstants;
import org.zhengyuxuan.service.UserService;
import org.zhengyuxuan.service.ValidationUtil;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResultVO<User> register(@RequestBody Map<String, String> params) { // 用户注册接口
        String username = params.get("username");
        String password = params.get("password");
        String nickname = params.get("nickname");

        String usernameError = ValidationUtil.validateUsername(username); // 校验用户名
        if (usernameError != null) {
            return ResultVO.error(usernameError);
        }
        String passwordError = ValidationUtil.validatePassword(password); // 校验密码
        if (passwordError != null) {
            return ResultVO.error(passwordError);
        }

        if (userService.isUsernameExists(username)) { // 检查用户名是否已存在
            return ResultVO.error(AppConstants.ERR_USERNAME_EXISTS);
        }

        User user = userService.register(username, password, nickname); // 注册用户
        if (user != null) {
            user.setPassword(null); // 不返回密码
            return ResultVO.success(AppConstants.MSG_REGISTER_SUCCESS, user);
        }
        return ResultVO.error(AppConstants.ERR_REGISTER_FAILED);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVO<User> login(@RequestBody Map<String, String> params, HttpSession session) { // 用户登录接口
        String username = params.get("username");
        String password = params.get("password");

        if (ValidationUtil.isBlank(username)) { // 参数校验
            return ResultVO.error(AppConstants.ERR_USERNAME_EMPTY);
        }
        if (ValidationUtil.isBlank(password)) {
            return ResultVO.error(AppConstants.ERR_PASSWORD_EMPTY);
        }

        User user = userService.login(username, password); // 登录验证
        if (user != null) {
            user.setPassword(null); // 不返回密码
            session.setAttribute(AppConstants.SESSION_CURRENT_USER, user); // 保存到Session
            return ResultVO.success(AppConstants.MSG_LOGIN_SUCCESS, user);
        }
        return ResultVO.error(AppConstants.ERR_LOGIN_FAILED);
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResultVO<Void> logout(HttpSession session) { // 用户登出接口
        session.removeAttribute(AppConstants.SESSION_CURRENT_USER);
        session.invalidate(); // 清空Session
        return ResultVO.success(AppConstants.MSG_LOGOUT_SUCCESS, null);
    }

    @GetMapping("/current")
    @ResponseBody
    public ResultVO<User> getCurrentUser(HttpSession session) { // 获取当前登录用户
        User user = (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);
        if (user != null) {
            return ResultVO.success(user);
        }
        return ResultVO.error(AppConstants.CODE_UNAUTHORIZED, AppConstants.MSG_UNAUTHORIZED);
    }

    @GetMapping("/check")
    @ResponseBody
    public ResultVO<Map<String, Boolean>> checkUsername(@RequestParam String username) { // 检查用户名是否可用
        boolean available = !userService.isUsernameExists(username);
        return ResultVO.success(Collections.singletonMap("available", available));
    }
}

