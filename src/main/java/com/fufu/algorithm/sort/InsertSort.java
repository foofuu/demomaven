package com.fufu.algorithm.sort;

import java.util.Arrays;

/**
 * Created by zhoujunfu on 2018/8/2.
 */
public class InsertSort {
    public static void sort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j = i - 1;
            while (j > 0 && a[j] > temp) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = temp;
        }
    }

    public static void sort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 1; i < arr.length; i ++) {
            for (int j=i-1;j>0;j++) {
                if (arr[j] < arr[i]) {
                    break;
                }
                int temp = arr[j + 1];      //交换操作
                arr[j + 1] = arr[j];
                arr[j] = temp;
                System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
    }
}
