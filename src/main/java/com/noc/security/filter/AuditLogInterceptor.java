package com.noc.security.filter;

import com.noc.security.log.AuditLog;
import com.noc.security.repository.AuditLogRepository;
import com.noc.security.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO HandlerInterceptorAdapter
 */
@Slf4j
@Component
@Order(3)
public class AuditLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("3.审计日志");
        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(request.getMethod());
        auditLog.setPath(request.getRequestURI());
        // 已经在AuditorAware实现
//        User user = (User) request.getAttribute("user");
//        if (user != null) {
//            auditLog.setUsername(request.getRequestURI());
//        }
        auditLogRepository.save(auditLog);
        request.setAttribute("auditLogId", auditLog.getId());
        return true;
    }

    /**
     * 不管成功失败都会调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        Long auditLogId = (Long) request.getAttribute("auditLogId");
        AuditLog auditLog = auditLogRepository.findById(auditLogId).get();
        auditLog.setStatus(response.getStatus());
        auditLogRepository.save(auditLog);
    }

}
