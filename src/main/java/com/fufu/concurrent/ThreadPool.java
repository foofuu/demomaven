package com.fufu.concurrent;

/**
 * Created by zhoujunfu on 2018/7/30.
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 添加一个工作任务
     * @param job 任务
     */
    void execute(Job job);

    /**
     * 添加num个工作者线程
     * @param num 添加数量
     */
    void addWorkers(int num);

    /**
     * 减少num个工作中线程
     * @param num 减少数量
     */
    void removeWorkers(int num);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取当前任务数量
     * @return 任务数量
     */
    int getJobSize();
}
