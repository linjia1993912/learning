package com.example.springbootrabbitmq;

import com.example.springbootrabbitmq.config.Config;
import com.example.springbootrabbitmq.message.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description:测试程序
 * 消息发送和订阅的程序
 * @Author LinJia
 * @Date 2020/9/3
 **/
@Component
public class Test implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Test(Receiver receiver, RabbitTemplate rabbitTemplate,
                ConfigurableApplicationContext context){
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
        this.context = context;
    }

    /**
     * @Description: springboot项目启动后执行
     * @Author LinJia
     * @Date 2020/9/3 14:15
     * @Param [args]
     * @return void
     **/
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Config.queueName, "Hello from RabbitMQ!");

        //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        //等待一定的时间后count值还没变为0的话就会继续执行
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        System.out.println("CountDownLatch Count <" + receiver.getLatch().getCount() + ">");
        context.close();
    }
}
