package com.fufu.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhoujunfu on 2018/9/7.
 */
public class MethodInterceptorImpl implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 表演开始前收钱
        getMoney();
        // 明星开始唱歌
        Object invoke = methodProxy.invokeSuper(o, objects);
        // 表演结束后开发票
        writeReceipt();

        return invoke;
    }

    private void getMoney() {
        System.out.println("get money");
    }

    private void writeReceipt() {
        System.out.println("write receipt");
    }
}
