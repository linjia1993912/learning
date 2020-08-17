package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:服务消费者
 * 模拟被消费的服务
 * @Author LinJia
 * @Date 2020/8/11
 **/
@RestController
public class EurekaClientController {


    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name){
        return "hi " + name + " ,i am from port:" + port;
    }
}
