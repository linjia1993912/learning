#项目简介
学习分布式配置中心组件spring cloud config 

该项目为config client

它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。在spring cloud config 组件中，分两个角色，
一是config server，二是config client

##测试
依次启动config-server、config-client

访问http://localhost:8881/hi

能获取到配置属性，这就说明，config-client从config-server获取了foo的属性，而config-server是从git仓库读取的

#高可用分布式配置中心 将config-server、config-client注册到注册中心
eureka-server作为注册中心

改造config-client、config-server 注册到eureka

修改config-client.application.properties

##测试
依次启动eureka-servr,config-server,config-client 访问网址：http://localhost:8881/hi

##测试高可用分布式配置中心
依次启动eureka-servr,config-server,config-client

config-server修改端口启动2个服务 8888 9999
先测试2个配置服务是否正常访问http://localhost:8888/foo/dev

如果都正常访问在测试config-client ConfigClientController是否能访问

如果能正常访问，随机停掉一个config-server服务

再次访问config-client ConfigClientController，如果能访问到说明达到了高可用

还可以增加Feign作为负载均衡

#使用Spring Cloud Bus 消息总线跟新配置
用于广播配置文件的更改或者服务之间的通讯和监控
在配置文件application.properties中加上RabbitMq的配置

##测试
依次启动eureka-server、confg-cserver,启动两个config-client，端口为：8881、8882

访问http://localhost:8881/hi 或者http://localhost:8882/hi 检查是否显示正确

这时我们去代码仓库将foo的值改为“foo version 21”，即改变配置文件foo的值。如果是传统的做法，需要重启服务，才能达到配置文件的更新

此时，我们只需要发送post请求：http://localhost:8881/actuator/bus-refresh，你会发现config-client会重新读取配置文件

这时我们再访问http://localhost:8881/hi 或者http://localhost:8882/hi 发现配置文件值已经改变

说明使用Spring Cloud Bus 实现配置跟新成功

##流程
当git文件更改的时候，通过pc端用post 向端口为8882的config-client发送请求/bus/refresh／；此时8882端口会发送一个消息，由消息总线向其他服务传递，从而使整个微服务集群都达到更新配置文件。








