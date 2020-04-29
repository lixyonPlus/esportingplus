package com.panda.esportingplus.common;

public class TestSignal2 {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdownCallback();
            }
        });
        System.out.println("running ...... jvm exit after 10s");
        Thread.sleep(10000);
    }

    private static void shutdownCallback() {
        System.out.println("jvm is exit!");
    }
}