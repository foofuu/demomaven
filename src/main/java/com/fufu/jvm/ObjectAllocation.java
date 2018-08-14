package com.fufu.jvm;

/**
 * Created by zhoujunfu on 2018/8/13.
 */
public class ObjectAllocation {

    private static final int _1MB = 1024*1024;

    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
