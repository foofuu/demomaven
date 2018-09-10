package com.fufu.proxy;

/**
 * Created by zhoujunfu on 2018/9/7.
 */
public aspect AgentAspectJ {

    /**
     * 定义切点
     */
    pointcut sleepPointCut():call(* Star.sing(..));

    /**
     * 定义切点
     */
    pointcut eatPointCut():call(* Star.eat(..));

    /**
     * 定义前置通知
     *
     * before(参数):连接点函数{
     *     函数体
     * }
     */
    before():sleepPointCut(){
        getMoney();
    }

    /**
     * 定义后置通知
     * after(参数):连接点函数{
     *     函数体
     * }
     */
    after():sleepPointCut(){
        writeReceipt();
    }

    private void getMoney() {
        System.out.println("get money");
    }

    private void writeReceipt() {
        System.out.println("write receipt");
    }
}
