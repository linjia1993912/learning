package com.example.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: Gateway自定义过滤器
 * pring Cloud Gateway内置了19种强大的过滤器工厂，能够满足很多场景的需求，那么能不能自定义自己的过滤器呢，当然是可以的。
 * 在spring Cloud Gateway中，过滤器需要实现GatewayFilter和Ordered2个接口
 * @Author LinJia
 * @Date 2020/8/14
 **/
@Component
public class RequestTimeFilter implements GatewayFilter,Ordered {

    private static final Log log = LogFactory.getLog(GatewayFilter.class);

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    /**
     * @Description: pre类型的过滤器
     * @Author LinJia
     * @Date 2020/8/14 17:02
     * @Param [exchange, chain]
     * @return reactor.core.publisher.Mono<java.lang.Void>
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //先记录了请求的开始时间，并保存在ServerWebExchange中
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        //chain.filter的内部类中的run()方法中相当于”post”过滤器
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }

    /**
     * @Description:给过滤器设置优先级
     * 值越大则优先级越低
     * @Author LinJia
     * @Date 2020/8/14 16:59
     * @Param []
     * @return int
     **/
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * @Description: 将自定义过滤器注册到router中
     * .filters(f -> f.filter(new RequestTimeFilter())
     * @Author LinJia
     * @Date 2020/8/14 17:05
     * @Param [builder]
     * @return org.springframework.cloud.gateway.route.RouteLocator
     **/
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(r -> r.path("/customer/**")
                        .filters(f -> f.filter(new RequestTimeFilter())
                                //往ResponseHeader添加一个X-Response-Default-Foo:Default-Bar
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("http://httpbin.org:80/get")
                        .order(0)
                        .id("customer_filter_router")
                )
                .build();
        // @formatter:on
    }

}
