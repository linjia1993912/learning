package com.example.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
//通过@EnableDiscoveryClient向其它服务中心注册
@EnableDiscoveryClient
//@EnableEurekaClient只针对Eureka
@EnableEurekaClient

//ribbon中使用Hystrix(断路器)
//在程序的启动类ServiceRibbonApplication 加@EnableHystrix注解开启Hystrix：
@EnableHystrix
public class ServiceRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRibbonApplication.class, args);
	}
}
