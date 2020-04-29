package com.panda.esportingplus.common;

import org.springframework.util.StopWatch;

public class SpringPerformanceLogTest {

    public static void main(String[] args) throws InterruptedException {

        StopWatch watch = new StopWatch("matching");
        matching(watch);
        System.out.println(watch.prettyPrint());
    }

    static void matching(StopWatch watch) throws InterruptedException {
        check1(watch);
        check2(watch);
        check3(watch);
    }

    static void check1(StopWatch watch) throws InterruptedException {
        watch.start("check1");
        Thread.sleep(1000);
        watch.stop();
    }
    static void check2(StopWatch watch) throws InterruptedException {
        watch.start("check2");
        Thread.sleep(1000);
        watch.stop();
    }
    static void check3(StopWatch watch) throws InterruptedException {
        watch.start("check3");
        Thread.sleep(1000);
        check33(watch);
        watch.stop();
    }

    static void check33(StopWatch watch) throws InterruptedException {
        try{
            watch.start("check33");
            Thread.sleep(1000);
            watch.stop();
        }catch (Throwable e){
            if(watch.isRunning()){
                watch.stop();
            }
            System.out.println(e.getMessage());
        }

    }
}
