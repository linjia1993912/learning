package com.example.service.impl;

import com.example.annotation.SelfAutowired;
import com.example.annotation.SelfService;

/**
 * @Description:循环依赖
 * B注入A
 * @Author LinJia
 * @Date 2020/8/19
 **/
@SelfService
public class B {

    @SelfAutowired
    private A a;

    public A getA(){
        return a;
    }
}
