package com.fufu.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by zhoujunfu on 2018/8/17.
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("【BeanFactoryPostProcessor】调用postProcessBeanFactory方法");
        BeanDefinition person = configurableListableBeanFactory.getBeanDefinition("person");
        person.getPropertyValues().addPropertyValue("name", "jack");
    }
}
