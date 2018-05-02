package com.fufu.aop;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhoujunfu on 2018/4/24.
 */
public class CGLibProxy implements MethodInterceptor {

    /**
     * 被代理的目标类
     */
    private Car target;

    public CGLibProxy(Car car) {
        super();
        this.target = car;
    }

    public Car getProxy() {
        // 使用CGLIB生成代理:
        // 1.声明增强类实例,用于生产代理类
        Enhancer enhancer = new Enhancer();
        // 2.设置被代理类字节码，CGLIB根据字节码生成被代理类的子类
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调函数，即一个方法拦截
        enhancer.setCallback(this);
        // 4.创建代理:
        return (Car) enhancer.create();
    }

    /**
     * 回调函数
     * @param proxy 代理对象
     * @param method 委托类方法
     * @param args 方法参数
     * @param methodProxy 每个被代理的方法都对应一个MethodProxy对象，
     *                    methodProxy.invokeSuper方法最终调用委托类(目标类)的原始方法
     * @return
     * @throws Throwable
     */
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("sleep".equals(method.getName())) {
            // 调用前处理
            System.out.println("before sleep action...");
            Object result = methodProxy.invokeSuper(proxy, args);
            // 调用后处理
            System.out.println("after sleep action...");
            return result;
        }
        //如果不需要增强直接执行原方法
        return methodProxy.invokeSuper(proxy, args);
    }
}
