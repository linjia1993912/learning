package com.example.mybatisplus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LinJia
 * @projectName demoparent
 * @description: TODO
 * @date 2021/4/2820:51
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Ngrok";
    }
}
