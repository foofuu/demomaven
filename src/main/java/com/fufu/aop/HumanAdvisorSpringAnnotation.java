package com.fufu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by zhoujunfu on 2018/4/18.
 */

@Aspect
public class HumanAdvisorSpringAnnotation {

    /**
     * 前置通知
     */
    @Before("execution(* com.fufu.aop.Creature.sleep(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("前置通知....");
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="execution(* com.fufu.aop.Creature.sleep(..))",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }



}
