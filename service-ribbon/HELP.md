##项目简介
ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon
Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign

ribbon使用hystrix(断路器)

#测试
依次启动eureka-server、eureka-client、service-ribbon

* 启动eureka-client时可以修改端口启动多个服务来测试负载均衡

多次访问HelloControler，实现负载均衡

关闭eureka-client服务，再次访问，试验熔断机制