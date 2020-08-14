package com.example.scfgatewayfirstsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScFGatewayFirstSightApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScFGatewayFirstSightApplication.class, args);
	}

	/**
	 * @Description: 创建一个简单的路由
	 * 在myRoutes方法中，使用了一个RouteLocatorBuilder的bean去创建路由，
	 * 除了创建路由RouteLocatorBuilder可以让你添加各种predicates和filters，predicates断言的意思，
	 * 顾名思义就是根据具体的请求的规则，由具体的route去处理，filters是各种过滤器，用来对请求做各种判断和修改
	 *
	 * 创建的route可以让请求“/get”请求都转发到“http://httpbin.org/get”。在route配置上，我们添加了一个filter，
	 * 该filter会将请求添加一个header,key为hello，value为world
	 *
	 * @Author LinJia
	 * @Date 2020/8/13 14:39
	 * @Param [builder]
	 * @return org.springframework.cloud.gateway.route.RouteLocator
	 **/
	/*@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://httpbin.org:80"))
				.build();
	}*/

	//在spring cloud gateway中可以使用Hystrix。Hystrix是 spring cloud中一个服务熔断降级的组件，在微服务系统有着十分重要的作用。
	// Hystrix是 spring cloud gateway中是以filter的形式使用的，代码如下

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String httpUri = "http://httpbin.org:80";
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri(httpUri))
				.route(p -> p
						.host("*.hystrix.com")
						.filters(f -> f
								.hystrix(config -> config
										.setName("mycmd")
										.setFallbackUri("forward:/fallback")))
						.uri(httpUri))
				.build();
	}
	//在上面的代码中，我们使用了另外一个router，该router使用host去断言请求是否进入该路由，当请求的host有“*.hystrix.com”，
	//都会进入该router，该router中有一个hystrix的filter,该filter可以配置名称、和指向性fallback的逻辑的地址，
	//比如本案例中重定向到了“/fallback”。

}
