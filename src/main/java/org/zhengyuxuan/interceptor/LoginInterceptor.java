package org.zhengyuxuan.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zhengyuxuan.constant.AppConstants;
import org.zhengyuxuan.entity.User;
import org.zhengyuxuan.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 * 用于拦截需要登录才能访问的请求
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final String AJAX_HEADER = "XMLHttpRequest";
    private static final String JSON_CONTENT_TYPE = "application/json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstants.SESSION_CURRENT_USER);

        if (user == null) {
            if (isAjaxOrJsonRequest(request)) {
                // AJAX请求，返回JSON
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(ResultVO.unauthorized()));
            } else {
                // 普通请求，重定向到登录页
                response.sendRedirect(request.getContextPath() + "/user/login");
            }
            return false;
        }
        return true;
    }

    /**
     * 判断是否是AJAX或JSON请求
     */
    private boolean isAjaxOrJsonRequest(HttpServletRequest request) {
        String xRequestedWith = request.getHeader("X-Requested-With");
        String contentType = request.getContentType();
        String accept = request.getHeader("Accept");

        return AJAX_HEADER.equals(xRequestedWith) ||
               (contentType != null && contentType.contains(JSON_CONTENT_TYPE)) ||
               (accept != null && accept.contains(JSON_CONTENT_TYPE));
    }
}

