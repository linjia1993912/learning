#项目简介
本项目实现Spring Cloud Gateway如何配合服务注册中心进行路由转发

本案例中使用spring boot的版本为2.0.3.RELEASE,spring cloud版本为Finchley.RELEASE

在中涉及到了三个工程， 分别为注册中心eureka-server、服务提供者eureka-client、 服务网关service-gateway

* eureka-server	8761	注册中心
* eureka-client	8762	服务提供者
* service-gateway	8081	路由网关 eureka client

这三个工程中，其中eureka-client、service-gateway向注册中心eureka-server注册。
用户的请求首先经过service-gateway，根据路径由gateway的predict 去断言进到哪一个 router， 
router经过各种过滤器处理后，最后路由到具体的业务服务，比如 eureka-client

