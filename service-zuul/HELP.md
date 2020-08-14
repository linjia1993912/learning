#zuul简介
微服务网关

Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务，
/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。

#项目简介
zuul不单单可以做路由，也可以做服务过滤

com.example.servicezuul.filter包下是服务过滤的代码示例

#注意事项
如果zuul启动报错，检查SpringBoot版本兼容问题

##报错信息
Description:
The bean 'proxyRequestHelper', defined in class path resource...

##版本
* SpringCloud.Finchley版本兼容的SpringBoot为2.0.X

#测试
依次启动eureka-server、eureka-client、service-ribbon

* 启动eureka-client时可以修改端口启动多个服务来测试负载均衡

按application.yml配置的路由地址访问，测试是否能转发到指定的服务

访问参数附带token，测试zuul服务过滤

关闭eureka-client服务，再次访问，试验熔断机制


