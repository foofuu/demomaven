package com.fufu.aop;

/**
 * Created by zhoujunfu on 2018/4/20.
 * 基于AspectJ的AOP，需要使用AspectJ编译器
 */
public aspect HumanAdvisorAspectJ {
    /**
     * 定义切点
     */
    pointcut sleepPointCut():call(* Human.sleep(..));

    /**
     * 定义切点
     */
    pointcut eatPointCut():call(* Human.eat(..));

    /**
     * 定义前置通知
     *
     * before(参数):连接点函数{
     *     函数体
     * }
     */
    before():sleepPointCut(){
        System.out.println("前置通知");
    }

    /**
     * 定义后置通知
     * after(参数):连接点函数{
     *     函数体
     * }
     */
    after():sleepPointCut(){
        System.out.println("后置通知");
    }

    /**
     * 定义后置通知带返回值
     * after(参数)returning(返回值类型):连接点函数{
     *     函数体
     * }
     */
    after() returning(int x): sleepPointCut(){
        System.out.println("后置通知带返回值:"+x);
    }

    /**
     * 异常通知
     * after(参数) throwing(返回值类型):连接点函数{
     *     函数体
     * }
     */
    after() throwing(Exception e):sleepPointCut(){
        System.out.println("异常通知:"+e.toString());
    }

    /**
     * 环绕通知 可通过proceed()控制目标函数是否执行
     * Object around(参数):连接点函数{
     *     函数体
     *     Object result=proceed();//执行目标函数
     *     return result;
     * }
     */
    Object around():eatPointCut(){
        System.out.println("环绕通知，前执行");
        Object result=proceed();//执行目标函数
        System.out.println("环绕通知，执行后");
        return result;
    }
}
