package com.example.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:注册bean
 * 代替spring-config.xml
 * @Author LinJia
 * @Date 2020/8/24
 **/
@Configuration
public class Config {

    @Bean
    public ISomeService iSomeService(){
        return new ISomeService();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }
}
