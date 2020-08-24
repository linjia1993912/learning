package com.example.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * @Description: Spirng中BeanPostProcessor和InstantiationAwareBeanPostProcessorAdapter两个接口都可以实现对bean前置后置处理的效果，
 * 演示一下BeanPostProcessor处理器的使用
 *
 * 实现BeanPostProcessor接口
 *
 * @Author LinJia
 * @Date 2020/8/24
 **/
public class MyBeanPostProcessor implements BeanPostProcessor{

    // 前置处理器
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        if (beanClass == ISomeService.class) {
            System.out.println("bean 对象初始化之前······");
        }
        return bean;
    }

    // 后置处理器 --- 此处具体的实现用的是Java中的动态代理
    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 为当前 bean 对象注册监控代理对象，负责增强 bean 对象方法的能力
        Class beanClass = bean.getClass();
        if(beanClass == ISomeService.class){
            Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(),bean.getClass().getInterfaces(), new InvocationHandler() {

                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("ISomeService 中的 doSome() 被拦截了···");
                    String result = (String) method.invoke(bean, args);
                    return result.toUpperCase();
                }

            });
            return proxy;
        }
        return bean;
    }

    //测试
    public static void main(String[] args) {
        /**
         * BeanPostProcessor 前置后置处理器
         */
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class);
        ctx.refresh();
        BaseService serviceObj = (BaseService) ctx.getBean("iSomeService");
        System.out.println(serviceObj.doSomething());
    }

}
