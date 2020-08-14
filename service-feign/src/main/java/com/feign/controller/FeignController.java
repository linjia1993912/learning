package com.feign.controller;

import com.feign.service.IFeignConfigServer;
import com.feign.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:在Web层的controller层，对外暴露一个”/hi”的API接口，通过上面定义的Feign客户端IFeignService 来消费服务
 * 多次访问，服务交替访问，达到了负载均衡的效果
 * @Author LinJia
 * @Date 2020/8/11
 **/
@RestController
public class FeignController {

    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    private IFeignService iFeignService;

    @Autowired
    private IFeignConfigServer iFeignConfigServer;

    @GetMapping("/hi")
    public String sayHi(@RequestParam String name) {
        return iFeignService.sayHiFromClientOne( name );
    }

    /**
     * @Description:测试高可用分布式配置中心
     * @Author LinJia
     * @Date 2020/8/12 15:49
     * @Param [name]
     * @return java.lang.String
     **/
    @GetMapping("/hiCloudConfig")
    public String hiCloudConfig(@RequestParam String name) {
        return iFeignConfigServer.hiCloudConfig( name );
    }
}
