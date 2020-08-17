package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description:限流解析器
 * application.yml key-resolver: '#{@hostAddrKeyResolver}'
 * @Author LinJia
 * @Date 2020/8/13
 **/
public class HostAddrKeyResolver implements KeyResolver {

    private static Logger log = LoggerFactory.getLogger(HostAddrKeyResolver.class);

    //KeyResolver需要实现resolve方法，比如根据Hostname进行限流，则需要用hostAddress去判断。
    // 实现完KeyResolver之后，需要将这个类的Bean注册到Ioc容器中。

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

}
