package com.fufu.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhoujunfu on 2018/4/24.
 * 基于Jdk的动态代理，被代理
 */
public class JDKProxy implements InvocationHandler {
    private Creature target;

    public JDKProxy(Creature human) {
        this.target = human;
    }

    public Creature getProxy() {
        return (Creature) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sleep".equals(method.getName())) {
            // 调用前处理
            System.out.println("before sleep action...");
            Object invoke = method.invoke(target, args);
            // 调用后处理
            System.out.println("after sleep action...");
            return invoke;

        }
        //如果不需要增强直接执行原方法
        return method.invoke(target, args);
    }
}
