package com.example.serviceribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:RestTemplate和Ribbon相结合
 * 实现负载均衡
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 * @Author LinJia
 * @Date 2020/8/11
 **/
@Configuration
public class RibbonConfig {

    //向程序的ioc注入一个bean: restTemplate;并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
