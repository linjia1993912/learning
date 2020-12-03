package com.example.springbootelasticsearch.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Description:Spring配置线程池
 * 还可以基于xml配置
 * @Author LinJia
 * @Date 2019/10/25
 **/
@Configuration
@EnableAsync
public class ThreadPoolTaskExecutorConfig {


    /**
     * @Description:
     * 拒绝策略
     * rejectedExectutionHandler参数字段用于配置绝策略，常用拒绝策略如下
     * AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
     * CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
     * DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
     * DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
     **/
    @Bean
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(5);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(120);
        //配置队列大小
        executor.setQueueCapacity(100);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("grapData-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        System.out.println("线程池初始化完成");
        return executor;
    }

}
