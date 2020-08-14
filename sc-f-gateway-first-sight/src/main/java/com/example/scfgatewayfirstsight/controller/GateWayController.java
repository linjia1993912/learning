package com.example.scfgatewayfirstsight.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/8/13
 **/
@RestController
public class GateWayController {

    /**
     * @Description: Mono是一个返回流 返回fallback字符串
     * @Author LinJia
     * @Date 2020/8/13 14:43
     * @Param []
     * @return reactor.core.publisher.Mono<java.lang.String>
     **/
    @GetMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

}
