package com.example.springbootrabbitmq.message;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:消息接收者
 * @Author LinJia
 * @Date 2020/9/3
 **/
@Component
public class Receiver {

    //线程计数器
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        //将count值减1
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
