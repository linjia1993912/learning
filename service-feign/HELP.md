#项目简介
Feign方式实现负载均衡，Feign默认集成了ribbon
消费服务

#实现熔断机制
Feign是自带断路器的,在D版本的Spring Cloud之后，它没有默认打开。需要在配置文件中配置打开它

Feign中使用hystrix(断路器)
```feign.hystrix.enabled=true```

#测试
依次启动eureka-server、eureka-client、service-feign

* 启动eureka-client时可以修改端口启动多个服务来测试负载均衡

多次访问FeignController，实现负载均衡

关闭eureka-client服务，再次访问，试验熔断机制

##feign做负载均衡测试高可用分布式配置中心
启动eureka-server
启动2个config-server服务 8888 9999

启动2个config-client服务 8881 8882

启动service-feign服务

访问FeignController.hiCloudConfig()是否能得到配置信息

此时停掉一个config-client服务，再次访问

再停掉一个config-server服务，再次访问

依然能获得配置信息，说明达到了高可用




