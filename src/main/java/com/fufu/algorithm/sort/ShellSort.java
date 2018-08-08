package com.fufu.algorithm.sort;

import java.util.Arrays;

/**
 * Created by zhoujunfu on 2018/8/8.
 */
public class ShellSort {

    public static void sort(int[] arr) {
        int gap = arr.length / 2;
        for (;gap > 0; gap = gap/2) {
            for (int j = 0; (j + gap) < arr.length; j++) {
                for (int k = 0; (k + gap) < arr.length; k+=gap) {
                    if (arr[k] > arr[k+gap]) {
                        arr[k] = arr[k] + arr[k+gap];
                        arr[k+gap] = arr[k] - arr[k+gap];
                        arr[k] = arr[k] - arr[k+gap];
                        System.out.println("    Sorting:  " + Arrays.toString(arr));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1,3,2,5,4,6,4,9,12};
        sort(a);
    }
}
