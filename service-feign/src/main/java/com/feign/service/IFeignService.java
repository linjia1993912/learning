package com.feign.service;

import com.feign.service.impl.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:Feign客户端
 * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务
 * @Author: linJia
 * @Date: 2020/8/11 15:17
 */
//Feign是自带断路器的，在D版本的Spring Cloud之后，它没有默认打开。需要在配置文件中配置打开它
//只需要在FeignClient的IFeignService接口的注解中加上fallback指定一个类就行了
//SchedualServiceHiHystric类就是处理服务不可用时马上返回指定值
@FeignClient(name = "eureka-client", fallback = SchedualServiceHiHystric.class)
public interface IFeignService {

    @GetMapping("/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
