package com.panda.esportingplus.common.thread.pool;



import com.panda.esportingplus.common.spi.Spi;
import com.panda.esportingplus.common.thread.NamedPoolThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 此线程池可伸缩，线程空闲一定时间后回收，新请求重新创建线程
 * ThreadPoolManager -> CommonExecutorFactory -> DefaultExecutor -> NamedPoolThreadFactory(ThreadFactory)
 *
 * @author shusong.liang
 * @date 2020/04/13 16:18:51
 */
@Spi(order = 1)
public final class CommonExecutorFactory implements ExecutorFactory {
  private static final Logger LOGGER = LoggerFactory.getLogger(CommonExecutorFactory.class);

  @Override
  public Executor get(String name) {
    final ThreadPoolConfig config;
    switch (name) {
      case ESPORTINGPLUS_COMMON:
        config = ThreadPoolConfig.build(ESPORTINGPLUS_COMMON)
                .setCorePoolSize(ThreadPoolConfigBean.CommonPool.corePoolSize)
                .setMaxPoolSize(ThreadPoolConfigBean.CommonPool.maxPoolSize)
                .setKeepAliveSeconds(ThreadPoolConfigBean.CommonPool.keepAliveTime)
                .setQueueCapacity(ThreadPoolConfigBean.CommonPool.workQueueSize)
                .setRejectedPolicy(ThreadPoolConfig.REJECTED_POLICY_CALLER_RUNS);
        break;
      case ESPORTINGPLUS_EVENT_BUS:
        config = ThreadPoolConfig.build(ESPORTINGPLUS_EVENT_BUS)
                .setCorePoolSize(ThreadPoolConfigBean.EventBusPool.corePoolSize)
                .setMaxPoolSize(ThreadPoolConfigBean.EventBusPool.maxPoolSize)
                .setKeepAliveSeconds(ThreadPoolConfigBean.EventBusPool.keepAliveTime)
                .setQueueCapacity(ThreadPoolConfigBean.EventBusPool.workQueueSize)
                .setRejectedPolicy(ThreadPoolConfig.REJECTED_POLICY_CALLER_RUNS);
        break;
      case ESPORTINGPLUS_TOKEN_BUS:
        config = ThreadPoolConfig.build(ESPORTINGPLUS_TOKEN_BUS)
                .setCorePoolSize(ThreadPoolConfigBean.TokenBusPool.corePoolSize)
                .setMaxPoolSize(ThreadPoolConfigBean.TokenBusPool.maxPoolSize)
                .setKeepAliveSeconds(ThreadPoolConfigBean.TokenBusPool.keepAliveTime)
                .setQueueCapacity(ThreadPoolConfigBean.TokenBusPool.workQueueSize)
                .setRejectedPolicy(ThreadPoolConfig.REJECTED_POLICY_CALLER_RUNS);
        break;
      case ESPORTINGPLUS_TASK_TIMER: {
        ScheduledThreadPoolExecutor executor =
            new ScheduledThreadPoolExecutor(ThreadPoolConfigBean.TaskTimerPool.corePoolSize, new NamedPoolThreadFactory(ESPORTINGPLUS_TASK_TIMER),
                    (r, e) -> LOGGER.error("one task timer was rejected, context=" + r));
        executor.setRemoveOnCancelPolicy(true);
        return executor;
      }
      default:
        throw new IllegalArgumentException("no executor for " + name);
    }

    return get(config);
  }

  protected Executor get(ThreadPoolConfig config) {
    String name = config.getName();
    int corePoolSize = config.getCorePoolSize();
    int maxPoolSize = config.getMaxPoolSize();
    int keepAliveSeconds = config.getKeepAliveSeconds();
    BlockingQueue<Runnable> queue = config.getQueue();

    return new DefaultExecutor(corePoolSize
            , maxPoolSize
            , keepAliveSeconds
            , TimeUnit.SECONDS
            , queue
            , new NamedPoolThreadFactory(name)
            , new DumpThreadRejectedHandler(config));
  }

}
