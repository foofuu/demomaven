package com.fufu.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by zhoujunfu on 2018/8/17.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("【BeanPostProcessor接口】调用postProcessAfterInitialization()");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("【BeanPostProcessor接口】调用postProcessAfterInitialization()");
        return o;
    }
}
