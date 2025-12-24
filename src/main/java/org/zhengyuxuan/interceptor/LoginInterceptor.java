package org.zhengyuxuan.interceptor;
}
    }
               (accept != null && accept.contains("application/json"));
        return (contentType != null && contentType.contains("application/json")) ||
        String accept = request.getHeader("Accept");
        String contentType = request.getContentType();
    private boolean isJsonRequest(HttpServletRequest request) {
     */
     * 判断是否是JSON请求
    /**

    }
        return true;

        }
            return false;
            }
                response.sendRedirect(request.getContextPath() + "/user/login");
                // 普通请求，重定向到登录页
            } else {
                response.getWriter().write(objectMapper.writeValueAsString(ResultVO.unauthorized()));
                response.setContentType("application/json;charset=UTF-8");
                // AJAX请求，返回JSON
            if ("XMLHttpRequest".equals(xRequestedWith) || isJsonRequest(request)) {
            String xRequestedWith = request.getHeader("X-Requested-With");
            // 判断是否是AJAX请求
        if (user == null) {

        User user = (User) session.getAttribute("currentUser");
        HttpSession session = request.getSession();
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    @Override

    private ObjectMapper objectMapper = new ObjectMapper();

public class LoginInterceptor implements HandlerInterceptor {
 */
 * 用于拦截需要登录才能访问的请求
 * 登录拦截器
/**

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.zhengyuxuan.vo.ResultVO;
import org.zhengyuxuan.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;


