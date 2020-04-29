package com.panda.esportingplus.common.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolManager -> CommonExecutorFactory -> DefaultExecutor -> NamedPoolThreadFactory(ThreadFactory)
 * @author shusong.liang
 * @date 2020/04/13 16:18:51
 */
public final class DefaultExecutor extends ThreadPoolExecutor {

    public DefaultExecutor(int corePoolSize, int maximumPoolSize,
                           long keepAliveTime, TimeUnit unit,
                           BlockingQueue<Runnable> workQueue,
                           ThreadFactory threadFactory,
                           RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

}
