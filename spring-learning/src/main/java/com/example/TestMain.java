package com.example;

import com.example.entity.User;
import com.example.service.impl.A;
import com.example.service.impl.B;
import com.example.service.UserService;
import com.example.spring.context.SelfPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: 测试简单的Ioc示例
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

        //测试模拟Ioc
        try{
            SelfPathXmlApplicationContext selfPathXmlApplicationContext = new SelfPathXmlApplicationContext("com.example.service");
            UserService userService = (UserService) selfPathXmlApplicationContext.getBean("userServiceImpl");
            userService.test();
            System.out.println(userService);
            //看看是否是同一个实例对象
            UserService userService1 = (UserService) selfPathXmlApplicationContext.getBean("userServiceImpl");
            System.out.println(userService1);

            //是否解决循环依赖
            A a = (A) selfPathXmlApplicationContext.getBean("a");
            B b = (B) selfPathXmlApplicationContext.getBean("b");
            System.out.println(b.getA() == a);
            System.out.println(a.getB() == b);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
