package com.panda.esportingplus.common.thread;

import static com.panda.esportingplus.common.thread.ThreadNames.THREAD_NAME_PREFIX;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NamedThreadFactory
 * @author shusong.liang
 * @date 2020/04/13 16:18:51
 */
public final class NamedThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final ThreadGroup group;


    public NamedThreadFactory() {
        this(THREAD_NAME_PREFIX);
    }

    public NamedThreadFactory(final String namePrefix) {
        this.namePrefix = namePrefix;
        this.group = Thread.currentThread().getThreadGroup();
    }

    /**
     * Daemon的作用是为其他线程的运行提供服务，比如说GC线程。其实User Thread线程和Daemon Thread守护线程本质上来说去没啥区别的，
     * 唯一的区别之处就在虚拟机的离开：如果User Thread全部撤离，那么Daemon Thread也就没啥线程好服务的了，所以虚拟机也就退出了。
     * 守护线程并非虚拟机内部可以提供，用户也可以自行的设定守护线程，方法：public final void setDaemon(boolean on) ；
     * <p>
     * 但是有几点需要注意：
     * 1）、thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程。
     * <p>
     * 2）、 在Daemon线程中产生的新线程也是Daemon的。
     * <p>
     * 3）、不是所有的应用都可以分配给Daemon线程来进行服务，比如读写操作或者计算逻辑。因为在Daemon Thread还没来的及进行操作时，虚拟机可能已经退出了。
     *
     * @param name name
     * @param r    runnable
     * @return new Thread
     */
    public Thread newThread(String name, Runnable r) {
        Thread thread = new Thread(group, r, namePrefix + "-" + threadNumber.getAndIncrement() + "-" + name);
        thread.setDaemon(true); //设置为非守护线程，否则jvm会立即退出
        return thread;
    }

    @Override
    public Thread newThread(Runnable r) {
        return newThread("none", r);
    }

    public static NamedThreadFactory build() {
        return new NamedThreadFactory();
    }

    public static NamedThreadFactory build(String namePrefix) {
        return new NamedThreadFactory(namePrefix);
    }


    public static void  start(){

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,
            TimeUnit. SECONDS, new ArrayBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPool.execute(new ThreadPoolTask(""));
        threadPool.submit(new ThreadPoolTask(""));
    }

    public static class ThreadPoolTask extends Thread {
        private static final long serialVersionUID = 0;
        //保存任务所需要的数据
        private Object threadPoolTaskData;
        ThreadPoolTask(Object works){
            this. threadPoolTaskData =works;
        }
        public void run(){
            //处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
            System. out.println( "start------"+threadPoolTaskData );
            try {
                //便于观察，等待一段时间
                Thread. sleep(11);
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadPoolTaskData = null;
        }
        public Object getTask(){
            return this. threadPoolTaskData;
        }
    }
}
