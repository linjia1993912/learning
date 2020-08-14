package com.example.serviceribbon.controller;

import com.example.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:测试负载均衡
 * 在浏览器多次访问改接口
 * 看看是否在两个注册的服务之间切换调用
 * @Author LinJia
 * @Date 2020/8/11
 **/
@RestController
public class HelloControler {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hi")
    public String hiController(@RequestParam String name){
        return helloService.hiService(name);
    }
}
