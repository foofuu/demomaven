package com.fufu.algorithm.sort;

import java.util.Arrays;

/**
 * Created by zhoujunfu on 2018/8/2.
 */
public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{2,1,3,4,5,6,7,8};
        System.out.println(Arrays.toString(a));
        InsertSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
