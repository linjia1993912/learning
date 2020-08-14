package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//在程序的入口Application类加上@EnableConfigServer注解开启配置服务器的功能
@EnableConfigServer
//注册服务,实现高可用分布式配置中心，将其注册到注册中心，如果不需要高可用则不需添加
@EnableEurekaClient
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
