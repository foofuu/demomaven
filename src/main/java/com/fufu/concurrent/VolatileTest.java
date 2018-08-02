package com.fufu.concurrent;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujunfu on 2018/7/27.
 */
public class VolatileTest {

    private int count;

    public synchronized int get() {
        return count;
    }

    public void add() {
        count = get();
        count = count + 1;
        set(count);
    }

    public synchronized void set(int count) {
        this.count = count;
    }


    public VolatileTest(int count) {
        this.count = count;
    }



    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest(0);

        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileTest.add();
                }
            });
            thread.start();
            ts.add(thread);
        }

        ts.forEach(item->{
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(volatileTest.get());
    }
}
