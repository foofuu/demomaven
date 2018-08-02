package com.fufu.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhoujunfu on 2018/7/30.
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute =( end - start ) <= THRESHOLD;

        if (canCompute) {
            for (int i = start; i <=end ; i++) {
                sum += i;
            }
            return sum;
        } else {
            int middle = ( start + end ) / 2;
            CountTask left = new CountTask(start, middle);
            CountTask right = new CountTask(middle+1, end);
            left.fork();
            right.fork();
            sum =  left.join() + right.join();
        }
        return sum;
    }

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 4);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(countTask);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
