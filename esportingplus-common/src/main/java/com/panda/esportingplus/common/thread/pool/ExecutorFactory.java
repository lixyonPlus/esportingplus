package com.panda.esportingplus.common.thread.pool;

import com.panda.esportingplus.common.spi.SpiLoader;
import java.util.concurrent.Executor;

/**
 * 线程池Executor工厂
 * ThreadPoolManager -> CommonExecutorFactory -> DefaultExecutor -> NamedPoolThreadFactory(ThreadFactory)
 * @author shusong.liang
 * @date 2020/04/13 16:18:51
 */
public interface ExecutorFactory {
    String ESPORTINGPLUS_COMMON = "esportingplus-common";
    String ESPORTINGPLUS_EVENT_BUS = "esportingplus-event-bus";
    String ESPORTINGPLUS_TOKEN_BUS = "esportingplus-token-bus";
    String ESPORTINGPLUS_TASK_TIMER = "esportingplus-task-timer";

    Executor get(String name);

    static ExecutorFactory create() {
        return SpiLoader.load(ExecutorFactory.class);
    }
}
