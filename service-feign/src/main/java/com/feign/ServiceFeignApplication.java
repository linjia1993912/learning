package com.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:Feign是一个声明式的伪Http客户端
 * Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果
 * @Author LinJia
 * @Date 2020/8/11
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
//在程序的启动类ServiceFeignApplication ，加上@EnableFeignClients注解开启Feign的功能
@EnableFeignClients
public class ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run( ServiceFeignApplication.class, args );
    }
}
