package com.fufu.concurrent;

/**
 * Created by zhoujunfu on 2018/7/12.
 *
 * synchronized修饰实例方法
 */
public class SynchronizedDemo implements Runnable {
    // 共享变量
    static int i = 0;


    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public synchronized void increase() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo instance = new SynchronizedDemo();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
