package com.noc.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.noc.security.bean.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证
 */
@Slf4j
@Component
public class OAuthFilter extends ZuulFilter {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String filterType() {
        // 过滤器类型：pre post error route
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 控制过滤器的执行顺序
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        // 是否起作用
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("oauth start");
        // 获取请求和响应
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = currentContext.getRequest();
        if (StringUtils.startsWith(httpServletRequest.getRequestURI(), "/token")) {
            // 不校验，继续往下走
            return null;
        }
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isBlank(authHeader)) {
            return null;
        }
        if (!StringUtils.startsWithIgnoreCase(authHeader, "bearer ")) {
            return null;
        }
        try {
            TokenInfo tokenInfo = getTokenInfo(authHeader);
            httpServletRequest.setAttribute("tokenInfo", tokenInfo);
            log.info("get token info success");
        } catch (Exception exception) {
            log.info("get token info fail", exception);
        }
        return null;
    }

    private TokenInfo getTokenInfo(String authHeader) {
        String token = StringUtils.substringAfter(authHeader, "Bearer ");
        String oauthServiceUrl = "http://localhost:9090/oauth/check_token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth("gateWay", "qwer1234");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        log.info("token :" + token);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<TokenInfo> responseEntity =
                restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
        log.info("token info:" + responseEntity.getBody().toString());
        return responseEntity.getBody();
    }

}
