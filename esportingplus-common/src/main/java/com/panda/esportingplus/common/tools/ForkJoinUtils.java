package com.panda.esportingplus.common.tools;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinUtils {

    //默认并行度，cpu核心数两倍减1
//    private static long parallelism = (Runtime.getRuntime().availableProcessors() << 1) - 1;
//    /**
//     *@Description: 定制并发线程数
//     *
//     *@author  Orochi-Yzh
//     *@dateTime  2018/11/16 16:41
//    */
//    static {
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",  parallelism + "");
//    }

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool(2000);
    public static ForkJoinPool getCommonPool(){
        return ForkJoinPool.commonPool();
    }

    public static ForkJoinPool defaultPool() {
        return forkJoinPool;
    }
}