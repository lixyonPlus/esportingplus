package com.kaihei.esportingplus.gateway.server.limiter;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.RateLimiterErrorHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@Description: 限流降级处理
 *
 *@author  Orochi-Yzh
 *@dateTime  2018/8/22 11:37
*/
@Configuration
public class ZuulRateLimiterErrorHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Bean
    public RateLimiterErrorHandler rateLimitErrorHandler() {
        return new DefaultRateLimiterErrorHandler() {
            @Override
            public void handleSaveError(String key, Exception e) {
                LOGGER.error("保存限流规则Key:[{}]异常", key, e);
            }

            @Override
            public void handleFetchError(String key, Exception e) {
                LOGGER.error("限流路由失败:[{}]异常", key);
            }

            @Override
            public void handleError(String msg, Exception e) {
                LOGGER.error("限流异常:[{}]", msg, e);
            }
        };
    }
}
