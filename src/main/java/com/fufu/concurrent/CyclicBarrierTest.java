package com.fufu.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhoujunfu on 2018/7/25.
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("到齐了");
        }
    });

    static class CyclicBarrierThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", 到了");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new CyclicBarrierThread().start();
        }
    }


}
