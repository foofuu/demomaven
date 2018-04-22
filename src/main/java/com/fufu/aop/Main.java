package com.fufu.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhoujunfu on 2018/4/16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
       // AspectJ
       /*  Human human = new Human();
       human.sleep(1);*/

        // Spring AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Creature human = (Creature) applicationContext.getBean("human");
        human.sleep(1);

    }
}
