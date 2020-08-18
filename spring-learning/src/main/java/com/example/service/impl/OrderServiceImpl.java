package com.example.service.impl;

import com.example.annotation.SelfService;
import com.example.service.OrderService;

/**
 * @Description: 模拟被注入对象
 * @Author LinJia
 * @Date 2020/8/18
 **/
@SelfService
public class OrderServiceImpl implements OrderService {

    @Override
    public void test() {
        System.out.println("OrderServiceImpl注入成功");
    }
}
