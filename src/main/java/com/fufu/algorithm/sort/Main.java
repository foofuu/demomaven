package com.fufu.algorithm.sort;

import java.util.Arrays;

/**
 * Created by zhoujunfu on 2018/8/2.
 */
public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{3,1,2,5,7,6,8,4};
        System.out.println(Arrays.toString(a));
        InsertSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
