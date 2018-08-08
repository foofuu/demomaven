package com.fufu.jvm;

/**
 * Created by zhoujunfu on 2018/8/8.
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//        classLoader.loadClass("com.fufu.jvm.Test");

        Class.forName("com.fufu.jvm.Test");

    }
}
