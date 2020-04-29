package com.panda.esportingplus.common.event;


import com.panda.esportingplus.common.thread.pool.ThreadPoolManager;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 通过 {@link org.springframework.boot.SpringApplication#addInitializers}初始化EventBus线程池
 * 适用于Spring Boot应用程序
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class EventBusThreadPoolInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //应用上下文只能初始化一次
        EventBus.create(ThreadPoolManager.INSTANCE.getEventBusExecutor());
    }
}
