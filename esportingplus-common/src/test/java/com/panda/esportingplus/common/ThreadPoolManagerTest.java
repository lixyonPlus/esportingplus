package com.panda.esportingplus.common;


import com.panda.esportingplus.common.thread.pool.ThreadPoolManager;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class ThreadPoolManagerTest {

  public static void main(String[] args) throws InterruptedException {
    ThreadPoolManager.INSTANCE.getDefaultExecutor().execute(() -> {
      System.out.println(Thread.currentThread().getName() + " -> hello world!!!");
    });

    ThreadPoolManager.INSTANCE.newThread("xx", () -> {
        System.out.println(Thread.currentThread().getName() + " -> test");
    }).start();


    ScheduledExecutorService findOrdersExecutor =  ThreadPoolManager.INSTANCE
            .getTaskTimer();

    System.out.println("111111");
    findOrdersExecutor.schedule(new Callable<Integer>(){
      @Override
      public Integer call() throws Exception {
        System.out.println(2);
        return 11;
      }
    },3, TimeUnit.SECONDS);

    LockSupport.park();
    findOrdersExecutor.shutdownNow();
  }
}
