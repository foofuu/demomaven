package com.fufu.concurrent;

/**
 * Created by zhoujunfu on 2018/7/26.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public int addCount() {
        count.set(count.get() + 1);
        return count.get();
    }

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();

        ThreadLocalThread thread = new ThreadLocalThread(threadLocalTest);
        ThreadLocalThread thread2 = new ThreadLocalThread(threadLocalTest);
        ThreadLocalThread thread3 = new ThreadLocalThread(threadLocalTest);
        thread.start();
        thread2.start();
        thread3.start();
    }


    static class ThreadLocalThread extends Thread {

        ThreadLocalThread(ThreadLocalTest threadLocalTest) {
            this.threadLocalTest = threadLocalTest;
        }

        private ThreadLocalTest threadLocalTest;

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " threadLocalTest :" + threadLocalTest.addCount());
            }
        }


    }
}
