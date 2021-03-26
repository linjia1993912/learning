#项目简介
本工程为学习使用

#模块说明  
参考各个模块HELP.md

#整合SpringCloud  
  
eureka-server 服务提供者  
eureka-client 服务消费者

在微服务架构中，业务都会被拆分成一个独立的服务，服务与服务的通讯是基于http restful的。  

Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign

service-ribbon:是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为,Feign默认集成了ribbon
ribbon使用hystrix(断路器)

service-feign:Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。
它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。  

Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果
Feign使用hystrix(断路器)

service-zuul用于学习应用zuul，实现路由功能和服务过滤功能

[在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件]
config-server 配置中心服务端  
config-client 配置中心客户端

* 代码中实现了高可用分布式配置中心

sc-f-gateway-first-sight 初识SpringCloud Gateway网关  
sc-f-gateway-predicate 使用Gateway Predicate 断言,匹配分发到路由

###注意事项 
[版本问题]
* SpringCloud.Finchley版本兼容的SpringBoot为2.0.X

