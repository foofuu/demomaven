package com.fufu.algorithm.sort;

import java.util.Arrays;

/**
 * Created by zhoujunfu on 2018/9/11.
 * 计数排序
 */
public class CountSort {

    public static void main(String[] args) {
        int[] a  = new int[]{2, 1, 3, 1, 5};
        sort(a);
    }

    public static void sort(int[] a) {

        int min = a[0];
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i]> max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }

        int[] counter = new int[max - min + 1];

        for (int i = 0; i < a.length; i++) {
            int index = a[i] - min;
            counter[index]++;
        }

        int m = 0;
        for (int j = 0; j< counter.length; j++) {
            int times = counter[j];
            int value = j + min;
            for (int k =0; k < times; k++) {
                a[m++] = value;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
