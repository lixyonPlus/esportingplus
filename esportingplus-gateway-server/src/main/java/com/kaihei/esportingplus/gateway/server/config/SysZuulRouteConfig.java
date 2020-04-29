package com.kaihei.esportingplus.gateway.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysZuulRouteConfig {

    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper(
            @Value("${base.url:}") String baseUrl) {
        // 调用构造函数 PatternServiceRouteMapper(String servicePattern,String routePattern)
        // servicePattern 指定微服务的正则
        // routePattern 指定路由正则
        return new PatternServiceRouteMapper(
                "^esportingplus-(\\w*)-?(\\w*)-(service|server)$", baseUrl + "/api/$1$2/**");
    }
}
