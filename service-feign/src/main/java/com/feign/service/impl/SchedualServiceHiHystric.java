package com.feign.service.impl;

import com.feign.service.IFeignService;
import org.springframework.stereotype.Component;

/**
 * @Description:Feign使用Hystrix断路器
 * 需要在FeignClient的注解上fallback指定此类即可
 * 需要实现IFeignService接口,并注入到Ioc容器中
 * @Author LinJia
 * @Date 2020/8/12
 **/
@Component
public class SchedualServiceHiHystric implements IFeignService {

    /**
     * @Description:服务不可用时，立刻返回该方法值
     * @Author LinJia
     * @Date 2020/8/12 9:44 
     * @Param [name]
     * @return java.lang.String
     **/
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
