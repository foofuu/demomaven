package com.fufu.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * Created by zhoujunfu on 2018/9/7.
 */
public class CglibProxyDemo {

    public static void main(String[] args) {
        Star star = new Star("Eminem");

        MethodInterceptor methodInterceptor = new MethodInterceptorImpl();

        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(star.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(methodInterceptor);

        ShowService showService = (ShowService) enhancer.create();
        showService.sing("Mockingbird");
    }
}
