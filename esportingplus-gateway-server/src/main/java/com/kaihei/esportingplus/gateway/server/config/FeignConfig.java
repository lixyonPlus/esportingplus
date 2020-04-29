package com.kaihei.esportingplus.gateway.server.config;

import com.google.common.base.Strings;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author Orochi-Yzh
 * @Description: 1.设置远程调用把cookie传到内网微服务中
 * 2.zuul 路由配置中sensitiveHeaders设置为空，默认不为空，会屏蔽Cookie
 * 3.更改hystrix的默认策略为SEMAPHOR：否则找报不到与线程绑定的request的异常
 * @dateTime 2018/8/1 15:07
 */
@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
            if (!Strings.isNullOrEmpty(sessionId)) {
                requestTemplate.header("Cookie", "SESSION=" + sessionId);
            }
        };
    }
}