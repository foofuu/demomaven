package com.fufu.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Created by zhoujunfu on 2018/9/7.
 * 基于XML的Spring AOP
 */
@Component
public class AgentAdvisorXML {

    public void getMoney() {
        System.out.println("get money");
    }

    public void writeReceipt() {
        System.out.println("write receipt");
    }
}
