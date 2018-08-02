package com.fufu.concurrent;

import java.util.concurrent.Executors;

/**
 * Created by zhoujunfu on 2018/7/26.
 */
public class PoolTest {

    public static void main(String[] args) {
        Executors.newFixedThreadPool(10);
    }
}
