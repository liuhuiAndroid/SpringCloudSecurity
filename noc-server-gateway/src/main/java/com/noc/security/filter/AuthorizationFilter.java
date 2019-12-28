package com.noc.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.noc.security.bean.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权
 */
@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("authorization start");
        // 获取请求和响应
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = currentContext.getRequest();
        // 当前请求是否需要做权限判断
        if (isNeedAuth(httpServletRequest)) {
            TokenInfo tokenInfo = (TokenInfo) httpServletRequest.getAttribute("tokenInfo");
            if (tokenInfo != null && tokenInfo.isActive()) {
                if (!hasPermission(tokenInfo, httpServletRequest)) {
                    log.info("audit log update fail 403");
                    handleError(403, currentContext);
                } else {
                    currentContext.addZuulRequestHeader("username", tokenInfo.getUsername());
                }
            } else {
                if (!StringUtils.startsWith(httpServletRequest.getRequestURI(), "/token")) {
                    log.info("audit log update fail 401");
                    handleError(401, currentContext);
                }
            }
        }
        return null;
    }

    /**
     * 判断用户权限
     */
    private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest httpServletRequest) {
        return true;
    }

    /**
     * 处理错误
     */
    private void handleError(int status, RequestContext requestContext) {
        requestContext.getResponse().setContentType("application/json");
        requestContext.setResponseStatusCode(status);
        requestContext.setResponseBody("{\"message\":\"auth fail\":}");
        // 请求不会往下执行
        requestContext.setSendZuulResponse(false);
    }

    private boolean isNeedAuth(HttpServletRequest httpServletRequest) {
        // TODO 查数据库判断权限
        return true;
    }

}
