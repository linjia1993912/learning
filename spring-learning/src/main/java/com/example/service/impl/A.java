package com.example.service.impl;

import com.example.annotation.SelfAutowired;
import com.example.annotation.SelfService;

/**
 * @Description:循环依赖
 * A注入B
 * @Author LinJia
 * @Date 2020/8/19
 **/
@SelfService
public class A {

    @SelfAutowired
    private B b;

    public B getB(){
        return b;
    }
}
