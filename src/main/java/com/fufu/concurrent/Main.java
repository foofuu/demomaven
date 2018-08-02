package com.fufu.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoujunfu on 2018/7/30.
 */
public class Main {
    public static void main(String[] args) {
        DefaultThreadPool defaultThreadPool = new DefaultThreadPool();
        ReentrantLock reentrantLock = new ReentrantLock();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + "running");
                }
            });
            defaultThreadPool.execute(thread);
        }
    }
}
