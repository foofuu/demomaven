package com.fufu.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoujunfu on 2018/7/24.
 */
public class ConditionTest {

    private int maxSize = 10;
    private List<Integer> queue = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();


    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private volatile boolean flag = true;

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("队列为空，等待数据");
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            flag = false;
                        }
                    }
                    queue.remove(0);
                    notFull.signal();
                    System.out.println("从队列取走一个数据，队列长度为：" + queue.size());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends  Thread {
        @Override
        public void run() {
            produce();
        }

        private volatile boolean flag = true;

        private void produce() {
            while (flag) {
                lock.lock();
                try {
                    while (queue.size() == maxSize) {
                        System.out.println("队列已满，等待消费");
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            flag = false;
                        }
                    }
                    queue.add(0);
                    notEmpty.signal();
                    System.out.println("向队列加入一个数据，队列长度为：" + queue.size());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        Producer producer = conditionTest.new Producer();
        Consumer consumer = conditionTest.new Consumer();
        producer.start();
        consumer.start();
        Thread.sleep(3);
        producer.interrupt();
        consumer.interrupt();

    }
}
