package com.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: feign做负载均衡 测试高可用分布式配置中心
 * @Author LinJia
 * @Date 2020/8/12
 **/
@FeignClient(name = "config-client")
public interface IFeignConfigServer {
    /**
     * @Description:消费hi接口
     * @Author LinJia
     * @Date 2020/8/12 15:49
     * @Param [name]
     * @return java.lang.String
     **/
    @GetMapping("/hi")
    String hiCloudConfig(@RequestParam(value = "name") String name);

}
