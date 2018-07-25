package com.fufu.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoujunfu on 2018/7/10.
 */
public class Test implements Runnable{

    private CountDownLatch countDownLatch;

    public Test(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            Test test = new Test(countDownLatch);
            executorService.submit(test);
        }
        countDownLatch.await();

        System.out.println("count end");
    }

    @Override
    public void run() {
        Random random = new Random(47);
        int i = random.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " count down.");
        countDownLatch.countDown();
    }
}
