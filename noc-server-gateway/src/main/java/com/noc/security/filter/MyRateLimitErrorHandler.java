package com.noc.security.filter;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义限流错误处理，注意耦合
 */
@Slf4j
@Component
public class MyRateLimitErrorHandler extends DefaultRateLimiterErrorHandler {

}
