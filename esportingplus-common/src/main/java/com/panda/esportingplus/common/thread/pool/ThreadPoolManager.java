package com.panda.esportingplus.common.thread.pool;

import com.panda.esportingplus.common.thread.NamedThreadFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public final class ThreadPoolManager {
    public static final ThreadPoolManager INSTANCE = new ThreadPoolManager();

    private final ExecutorFactory executorFactory = ExecutorFactory.create();
    private final NamedThreadFactory threadFactory = new NamedThreadFactory();

    private final Map<String, Executor> pools = new ConcurrentHashMap<>();

    public final Thread newThread(String name, Runnable target) {
        return threadFactory.newThread(name, target);
    }

    public Executor getDefaultExecutor() {
        return pools.computeIfAbsent(ExecutorFactory.ESPORTINGPLUS_COMMON,s -> executorFactory.get(ExecutorFactory.ESPORTINGPLUS_COMMON));
    }

    public Executor getEventBusExecutor() {
        return pools.computeIfAbsent(ExecutorFactory.ESPORTINGPLUS_EVENT_BUS, s -> executorFactory.get(ExecutorFactory.ESPORTINGPLUS_EVENT_BUS));
    }

    public Executor getTokenBusExecutor() {
        return pools.computeIfAbsent(ExecutorFactory.ESPORTINGPLUS_TOKEN_BUS, s -> executorFactory.get(ExecutorFactory.ESPORTINGPLUS_TOKEN_BUS));
    }

    public ScheduledExecutorService getTaskTimer() {
        return (ScheduledExecutorService) pools.computeIfAbsent(ExecutorFactory.ESPORTINGPLUS_TASK_TIMER
                , s -> executorFactory.get(ExecutorFactory.ESPORTINGPLUS_TASK_TIMER));
    }

    public void register(String name, Executor executor) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(executor);
        pools.put(name, executor);
    }

    public Map<String, Executor> getActivePools() {
        return pools;
    }

    public static Map<String, Object> getPoolInfo(ThreadPoolExecutor executor) {
        Map<String, Object> info = new HashMap<>(5);
        info.put("corePoolSize", executor.getCorePoolSize());
        info.put("maxPoolSize", executor.getMaximumPoolSize());
        info.put("activeCount(workingThread)", executor.getActiveCount());
        info.put("poolSize(workThread)", executor.getPoolSize());
        info.put("queueSize(blockedTask)", executor.getQueue().size());
        return info;
    }

}
