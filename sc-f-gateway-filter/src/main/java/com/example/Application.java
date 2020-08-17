package com.example;

import com.example.filter.RequestTimeGatewayFilterFactory;
import com.example.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}

	//需要将TokenFilter在工程的启动类中注入到Spring Ioc容器中
	@Bean
	public TokenFilter tokenFilter(){
		return new TokenFilter();
	}
}
