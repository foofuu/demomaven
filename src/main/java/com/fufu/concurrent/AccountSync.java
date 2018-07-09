package com.fufu.concurrent;

/**
 * Created by zhoujunfu on 2018/7/5.
 */
public class AccountSync implements Runnable {
    static int account = 0;

    public synchronized void increase() {
        account++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AccountSync accountSync = new AccountSync();
        Thread t1 = new Thread(accountSync);
        Thread t2 = new Thread(accountSync);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(account);
    }
}
