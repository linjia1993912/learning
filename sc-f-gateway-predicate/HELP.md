#项目简介
学习Spring Cloud Gateway Predicate

客户端向Spring Cloud Gateway发出请求。 如果Gateway Handler Mapping确定请求与路由匹配（这个时候就用到predicate），
则将其发送到Gateway web handler处理。 Gateway web handler处理请求时会经过一系列的过滤器链。 
过滤器链被虚线划分的原因是过滤器链可以在发送代理请求之前或之后执行过滤逻辑。 先执行所有“pre”过滤器逻辑，
然后进行代理请求。 在发出代理请求之后，收到代理服务的响应之后执行“post”过滤器逻辑。这跟zuul的处理过程很类似。
在执行所有“pre”过滤器逻辑时，往往进行了鉴权、限流、日志输出等功能，以及请求头的更改、协议的转换；
转发之后收到响应之后，会执行所有“post”过滤器的逻辑，在这里可以响应数据进行了修改，比如响应头、协议的转换等。

在上面的处理过程中，有一个重要的点就是讲请求和路由进行匹配，这时候就需要用到predicate，它是决定了一个请求走哪一个路由。

Predicate来自于java8的接口。Predicate 接受一个输入参数，返回一个布尔值结果。
该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）。
可以用于接口请求参数校验、判断新老数据是否有变化需要进行更新操作。add--与、or--或、negate--非。

Spring Cloud Gateway内置了许多Predict,这些Predict的源码在org.springframework.cloud.gateway.handler.predicate包中


##测试
启动项目，使用curl来模拟发送请求,测试各个Predicate是否正确匹配路由
