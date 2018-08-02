package com.fufu.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhoujunfu on 2018/7/30.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 任务队列
    private final LinkedList<Job> jobs = new LinkedList<>();

    // 工作线程队列
    private final LinkedList<Worker> workers = new LinkedList<>();

    // 最大工作线程数量
    private static final int MAX_WORKERS_NUM = 10;

    // 默认工作线程数量
    private static final int DEFAULT_WORKERS_NUM = 5;

    // 线程编号
    private AtomicLong threadNum = new AtomicLong();

    // 工作者线程的数量
    private int workerNum = DEFAULT_WORKERS_NUM;

    public DefaultThreadPool() {
        initWorkers(DEFAULT_WORKERS_NUM);
    }

    private void initWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (jobs != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKERS_NUM) {
                num = MAX_WORKERS_NUM - this.workerNum;
            }
            initWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs) {
            if (num > this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= num;
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public int getJobSize() {
        return 0;
    }

    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {

                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
