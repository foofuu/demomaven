package com.fufu.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by zhoujunfu on 2018/9/7.
 * 基于注解的Spring AOP
 */
@Aspect
@Component
public class AgentAdvisor {

    @Before(value = "execution(* com.fufu.proxy.ShowService.sing(..))")
    public void getMoney() {
        System.out.println("get money");
    }

    @After(value = "execution(* com.fufu.proxy.ShowService.sing(..))")
    public void writeReceipt() {
        System.out.println("write receipt");
    }
}
