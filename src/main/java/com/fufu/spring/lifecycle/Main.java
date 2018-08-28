package com.fufu.spring.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhoujunfu on 2018/8/16.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("现在开始初始化容器");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println("容器初始化成功");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        applicationContext.registerShutdownHook();
    }
}
