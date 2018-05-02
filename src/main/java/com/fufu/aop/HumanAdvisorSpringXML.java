package com.fufu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Repository;

/**
 * Created by zhoujunfu on 2018/4/24.
 */
@Repository
public class HumanAdvisorSpringXML {

    public void before(){
        System.out.println("HumanAdvisorSpringXML====前置通知");
    }

    public void afterReturn(Object returnVal){
        System.out.println("后置通知-->返回值:"+returnVal);
    }

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("HumanAdvisorSpringXML=====环绕通知前");
        Object object= joinPoint.proceed();
        System.out.println("HumanAdvisorSpringXML=====环绕通知后");
        return object;
    }

    public void afterThrowing(Throwable throwable){
        System.out.println("HumanAdvisorSpringXML======异常通知:"+ throwable.getMessage());
    }

    public void after(){
        System.out.println("HumanAdvisorSpringXML=====最终通知..来了");
    }
}
