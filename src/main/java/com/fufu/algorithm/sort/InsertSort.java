package com.fufu.algorithm.sort;

/**
 * Created by zhoujunfu on 2018/8/2.
 */
public class InsertSort {
    public static void sort(int[] a) {
        int length = a.length;
        int temp;
        for (int i = 1; i < a.length; i++) {
            temp = a[i];
            int j = i - 1;
            while (j>=0&&a[j] > temp) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = temp;
        }
    }
}
