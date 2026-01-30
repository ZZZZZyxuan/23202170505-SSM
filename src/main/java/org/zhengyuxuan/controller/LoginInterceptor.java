package org.zhengyuxuan.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.service.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 * 用于拦截需要登录才能访问的请求
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);

        if (user == null) {
            // 用户未登录
            // 判断是否为AJAX请求
            String xRequestedWith = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(xRequestedWith)) {
                // AJAX请求返回JSON
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"success\":false,\"message\":\"请先登录\"}");
            } else {
                // 普通请求重定向到登录页
                response.sendRedirect(request.getContextPath() + "/user/login");
            }
            return false;
        }

        return true;
    }
}

