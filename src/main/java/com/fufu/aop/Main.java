package com.fufu.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhoujunfu on 2018/4/16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
       //AspectJ
      /* Human human = new Human();
       human.sleep(1);*/


        //Spring Aop
       /* ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Creature human = (Creature) applicationContext.getBean("human");
        human.sleep(1);*/

        //JDK Proxy
        /*Human humanJdk = new Human();
        JDKProxy jdkProxy = new JDKProxy(humanJdk);
        Creature proxy = jdkProxy.getProxy();
        proxy.sleep(1);*/

        Car car = new Car();
        CGLibProxy cgLibProxy = new CGLibProxy(car);
        Car carProxy = cgLibProxy.getProxy();
        carProxy.start();
    }
}
