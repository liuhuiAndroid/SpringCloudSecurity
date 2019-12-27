package com.noc.security.filter;

import com.noc.security.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ACL访问控制
 */
@Slf4j
@Component
@Order(4)
public class AclInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("4.访问控制");
        boolean result = true;
        User user = (User) request.getAttribute("user");
        if (user == null) {
            response.setContentType("text/plain");
            response.getWriter().write("need authentication");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            result = false;
        } else {
            String method = request.getMethod();
            if(!user.hasPermission(method)){
                response.setContentType("text/plain");
                response.getWriter().write("forbidden");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                result = false;
            }
        }
        return result;
    }

}
