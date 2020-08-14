#项目简介
spring cloud gateway之filter篇

过滤器

Predict决定了请求由哪一个路由处理，在路由处理之前，需要经过“pre”类型的过滤器处理，处理返回响应之后，可以由“post”类型的过滤器处理
 
过滤器允许以某种方式修改传入的HTTP请求或传出的HTTP响应。过滤器可以限定作用在某些特定请求路径上。  

Spring Cloud Gateway包含许多内置的GatewayFilter工厂

在Spring Cloud Gateway中，filter从作用范围可分为另外两种，一种是针对于单个路由的gateway filter，它在配置文件中的写法同predict类似；
另外一种是针对于所有路由的global gateway filer。

本项目基于这两种方式进行使用








