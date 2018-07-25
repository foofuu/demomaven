package com.fufu.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by zhoujunfu on 2018/7/24.
 */
public class AtomicTest {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.compareAndSet(100, 110);
                atomicInteger.compareAndSet(110, 100);
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println( "result1: " + atomicInteger.compareAndSet(100, 120));
            }

        });

        t2.start();

        int stamp = atomicStampedReference.getStamp();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicStampedReference.compareAndSet(100, 110,
                        atomicStampedReference.getStamp(), atomicStampedReference.getStamp() +1 );
                atomicStampedReference.compareAndSet(110, 100,
                        atomicStampedReference.getStamp(), atomicStampedReference.getStamp() +1 );
            }
        });

        t3.start();

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("result2: " + atomicStampedReference.compareAndSet(100, 120,
                        stamp, stamp + 1));

            }
        });
        t4.start();
    }
}
