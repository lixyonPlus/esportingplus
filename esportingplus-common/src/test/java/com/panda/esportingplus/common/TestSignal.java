package com.panda.esportingplus.common;

import sun.misc.Signal;
import sun.misc.SignalHandler;
public class TestSignal implements SignalHandler{

    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        TestSignal tsh = new TestSignal();

        Signal.handle(new Signal("TERM"), tsh);  // kill -15 common kill
        Signal.handle(new Signal("INT"), tsh);   // Ctrl+c
        //Signal.handle(new Signal("KILL"), tsh);  // kill -9  no Support
        //Signal.handle(new Signal("USR1"), tsh);   // kill -10
        //Signal.handle(new Signal("USR2"), tsh);   // kill -12
        while (flag)
        {
            Thread.sleep(3000);
            System.out.println("running ......");
        }
    }

    private void signalCallBack(Signal sn)
    {
        flag = false;
        System.out.println(sn.getName()+" is recevied.");
    }

    @Override
    public void handle(Signal sn) {
        signalCallBack(sn);
    }
}