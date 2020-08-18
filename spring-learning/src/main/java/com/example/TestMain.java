package com.example;

import com.example.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: 简单的Ioc示例
 * @Author LinJia
 * @Date 2020/8/18
 **/
public class TestMain {

    public static void main(String[] args) {
        //创建spring应用上下文
        //创建一个新的ClassPathXmlApplicationContext，从给定的XML文件中加载定义并自动刷新上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        //获取User bean
        User user = applicationContext.getBean("user", User.class);

        System.out.println(user.toString());
    }

}
