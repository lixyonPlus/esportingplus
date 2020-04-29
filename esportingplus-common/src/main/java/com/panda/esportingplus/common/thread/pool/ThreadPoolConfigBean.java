package com.panda.esportingplus.common.thread.pool;

import java.util.Optional;
import org.springframework.core.env.PropertySource;

/**
 * 读取{@code ConfigServer application.properties}中的线程池配置
 *
 * @author shusong.liang
 * @date 2020/04/13 16:18:51
 */
public interface ThreadPoolConfigBean {

    ThreadLocal<PropertySource<?>> THREAD_LOCAL_HOLDER = new ThreadLocal<>();

    int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    int MAX_POOL_SIZE = 100;
    // 单位：秒
    int KEEP_ALIVE_TIME = 60;
    int WORK_QUEUE_SIZE = 1000;

    int TASKTIMER_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    interface CommonPool {

        PropertySource<?> propertySource = THREAD_LOCAL_HOLDER.get();
        int corePoolSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.common.corePoolSize").toString())).orElse(CORE_POOL_SIZE);
        int maxPoolSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.common.maximumPoolSize").toString())).orElse(MAX_POOL_SIZE);
        int keepAliveTime = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.common.keepAliveTime").toString())).orElse(KEEP_ALIVE_TIME);
        int workQueueSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.common.workQueueSize").toString())).orElse(WORK_QUEUE_SIZE);
    }

    interface EventBusPool {

        PropertySource<?> propertySource = THREAD_LOCAL_HOLDER.get();
        int corePoolSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.eventBus.corePoolSize").toString())).orElse(CORE_POOL_SIZE);
        int maxPoolSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.eventBus.maximumPoolSize").toString())).orElse(MAX_POOL_SIZE);
        int keepAliveTime = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.eventBus.keepAliveTime").toString())).orElse(KEEP_ALIVE_TIME);
        int workQueueSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.eventBus.workQueueSize").toString())).orElse(WORK_QUEUE_SIZE);

    }

    interface TokenBusPool {

        PropertySource<?> propertySource = THREAD_LOCAL_HOLDER.get();
        int corePoolSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.tokenBus.corePoolSize").toString())).orElse(CORE_POOL_SIZE);
        int maxPoolSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.tokenBus.maximumPoolSize").toString())).orElse(MAX_POOL_SIZE);
        int keepAliveTime = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.tokenBus.keepAliveTime").toString())).orElse(KEEP_ALIVE_TIME);
        int workQueueSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.tokenBus.workQueueSize").toString())).orElse(WORK_QUEUE_SIZE);

    }

    interface TaskTimerPool {

        PropertySource<?> propertySource = THREAD_LOCAL_HOLDER.get();
        int corePoolSize = Optional.ofNullable(propertySource).map( source -> Integer.parseInt(source.getProperty("thread.pool.taskTimer.corePoolSize").toString())).orElse(TASKTIMER_CORE_POOL_SIZE);
    }

}
