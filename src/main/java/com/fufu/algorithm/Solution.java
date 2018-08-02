package com.fufu.algorithm;

/**
 * Created by zhoujunfu on 2018/7/31.
 */
class Solution {


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int size = array.length;
        for (int i = 0; i < size; i++) {
            int[] data = new int[3];
            data[0] = array[i];
            for (int j = i + 1; j < size; j++) {
                data[1] = array[j];
                for (int k = j + 1; k< size; k++) {
                    data[2] = array[k];
                    for (int item : data) {
                        System.out.print(item);
                    }
                    System.out.println();
                }
            }
        }
    }
}