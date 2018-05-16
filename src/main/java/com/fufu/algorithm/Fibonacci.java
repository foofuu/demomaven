package com.fufu.algorithm;

/**
 * Created by zhoujunfu on 2018/5/2.
 */
public class Fibonacci {

    public static long fibonacciR(int n) {
        if (n < 1) {
            return -1;
        }
        if (n <= 2) {
            return 1;
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public static long fibonacci(int n) {
        if (n < 1) {
            return -1;
        }
        if (n <= 2) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 0; i < n - 2; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println(fibonacci(i));
        }
    }
}
