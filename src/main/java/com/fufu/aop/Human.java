package com.fufu.aop;

import org.springframework.stereotype.Repository;

/**
 * Created by zhoujunfu on 2018/4/16.
 */
@Repository
public class Human implements Creature {
    @Override
    public int sleep(int hours) throws Exception{
        System.out.println("I will sleep " + hours + " hours.");
        return hours;
    }

    @Override
    public void eat(String food) throws Exception {
        System.out.println("I will eat " + food);
    }
}
