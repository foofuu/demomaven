package com.fufu.spring.aop;

import com.fufu.proxy.ShowService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhoujunfu on 2018/9/7.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop-annotation.xml");

        Object star = applicationContext.getBean("star");

        ShowService showService = (ShowService)star;
        showService.sing("Mockingbird");
    }
}
