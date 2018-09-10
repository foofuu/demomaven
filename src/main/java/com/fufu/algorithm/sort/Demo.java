package com.fufu.algorithm.sort;

import java.util.Arrays;

/**
 * Created by zhoujunfu on 2018/9/10.
 */
public class Demo {

    public static void main(String[] args) {
        int[] array = {10, 20, 5, 4};
        insertSort(array);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 交换数组array的i和j位置的数据
     * @param array 数组
     * @param i 下标i
     * @param j 下标j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void insertSort(int[] a) {
        if (a == null || a.length < 0) {
            return;
        }

        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int tmp = a[i];
            while (j >=0 && a[j] > tmp) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = tmp;
        }

    }

    public static void quickSort(int[] a, int low, int high) {
        if (a == null ||a.length < 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;
        int key = a[low];

        while (left < right) {
            while (left < right && a[right] >= key ) {
                right--;
            }
            a[left] = a[right];
            while (left < right && a[left] <= key) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = key;
        quickSort(a, low, left - 1);
        quickSort(a, left + 1, high);
    }

    public static void bubble(int[] array) {
        if (array == null || array.length < 0){
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j + 1, j);
                }
            }
        }
    }
}
