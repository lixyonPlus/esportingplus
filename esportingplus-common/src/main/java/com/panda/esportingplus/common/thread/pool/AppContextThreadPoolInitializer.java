package com.panda.esportingplus.common.thread.pool;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySources;

/**
 * 读取 {@code ConfigServer application.properties }配置的线程池设置，初始化线程池
 * 读取配置参考 {@link org.springframework.cloud.config.client.ConfigServicePropertySourceLocator#locate}
 * 适用于Spring Boot应用程序
 * @author shusong.liang
 * @date 2020/04/13 16:18:51
 */
public class AppContextThreadPoolInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        PropertySources sources = applicationContext.getEnvironment().getPropertySources();
        ThreadPoolConfigBean.THREAD_LOCAL_HOLDER.set(sources.get("bootstrapProperties"));
    }
}
