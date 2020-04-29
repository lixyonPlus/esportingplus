package com.panda.esportingplus.common.web;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.panda.esportingplus.common.web.interceptor.CustomInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author
 */
@Configuration
@ConditionalOnBean(CustomInterceptor.class)
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Autowired
    CustomInterceptor customInterceptor;

    protected CacheManager cacheManager = CacheManagerFactory.create();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        // 拦截存在 redis 中的所有请求, 如果出现异常则拦截所有
//        try {
//            Set<String> urlSet = cacheManager.sMembers(RedisKey.NEED_AUTH_URLS);
//            String[] urlStrs = urlSet.toArray(new String[0]);
//            if (urlStrs.length > 0) {
//                registry.addInterceptor(customInterceptor).addPathPatterns(urlStrs);
//                return;
//            }
//        } catch (Exception e) {
//            logger.error("get auth urls error!", e);
//        }
        registry.addInterceptor(customInterceptor).addPathPatterns("/**").excludePathPatterns("/premade/**");
    }
}