package com.panda.esportingplus.common;

import com.panda.esportingplus.common.tools.PerformanceProfilerMonitor;

public class ProfilerPerformanceLogTest {

    public static void main(String[] args) throws InterruptedException {
        testProfiler();
    }

    private static void testProfiler() throws InterruptedException {
        PerformanceProfilerMonitor.start();
        PerformanceProfilerMonitor.enter("time cost on [1111]");
        Thread.sleep(1000);
        PerformanceProfilerMonitor.release();
        PerformanceProfilerMonitor.enter("time cost on [2222]");
        Thread.sleep(1000);
        PerformanceProfilerMonitor.release();
        PerformanceProfilerMonitor.enter("time cost on [2222]");
        PerformanceProfilerMonitor.release();
        Thread.sleep(1000);
        PerformanceProfilerMonitor.release();
        System.out.println(PerformanceProfilerMonitor.dump());
    }

}
