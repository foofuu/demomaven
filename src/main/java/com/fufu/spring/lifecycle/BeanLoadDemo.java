package com.fufu.spring.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhoujunfu on 2018/9/5.
 */
public class BeanLoadDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        Object car = applicationContext.getBean("car");

        System.out.println(car);

    }
}
