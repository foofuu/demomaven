package com.fufu.concurrent;

import jdk.management.resource.internal.inst.ThreadRMHooks;

import java.util.concurrent.Semaphore;

/**
 * Created by zhoujunfu on 2018/7/25.
 */
public class SemaphoreTest {

    static class Parking {
        private static Semaphore semaphore;

        public Parking(int count) {
            semaphore = new Semaphore(count);
        }


        public void park() {
            try {
                semaphore.acquire();
                long time = (long)(Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + "drive in parking, stop " + time + " secs");
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "drive out parking");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    static class Car extends Thread{
        private Parking parking;

        public Car(Parking parking) {
            this.parking = parking;
        }

        @Override
        public void run() {
            parking.park();
        }
    }

    public static void main(String[] args) {
        Parking parking = new Parking(1);
        for (int i = 0; i < 5; i++) {
            new Car(parking).start();
        }
    }
}
