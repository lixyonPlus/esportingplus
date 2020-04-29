package com.panda.esportingplus.backend.gateway.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * 后台服务之间的接口调用
 * 目前仅适配Python端的接口规范输出
 *
 * @author 谢思勇
 */
@SpringBootApplication
@EnableZuulProxy
public class BackendGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendGatewayServerApplication.class, args);
    }

    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper(
            @Value("${zuul.backend.prefix:}") String baseUrl) {
        return new PatternServiceRouteMapper(
                "^esportingplus-(\\w*)-?(\\w*)-service$", baseUrl + "/$1$2");
    }
}
