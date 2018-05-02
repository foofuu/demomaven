package com.fufu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by zhoujunfu on 2018/4/18.
 * 基于注解的Spring AOP
 * 在Spring中与AspectJ一样，通知主要分5种类型，分别是前置通知、后置通知、异常通知、最终通知以及环绕通知
 */

@Aspect
public class HumanAdvisorSpringAnnotation {

    /**
     * 环绕通知
     */
    @Around(value = "execution(* com.fufu.aop.Creature.sleep(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("我是环绕通知前....");
        //执行目标函数
        Object obj= (Object) joinPoint.proceed();
        System.out.println("我是环绕通知后....");
        return obj;
    }

    /**
     * 前置通知
     * @param joinPoint 可选，该参数可以获取目标对象的信息,如类名称,方法参数,方法名称等
     */
    @Before(value = "execution(* com.fufu.aop.Creature.sleep(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("前置通知....");
    }

    /**
     * 后置通知，不需要参数时可以不提供
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value="execution(* com.fufu.aop.Creature.sleep(..))",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("后置通知...."+returnVal);
    }

    /**
     * 抛出通知
     * @param e 抛出异常的信息
     */
    @AfterThrowing(value="execution(* com.fufu.aop.Creature.sleep(..))", throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("出现异常:msg="+e.getMessage());
    }

    /**
     * 最终通知
     * joinPoint 参数
     */
    @After(value = "execution(* com.fufu.aop.Creature.sleep(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("最终通知....");
    }
}
