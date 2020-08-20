package com.example.singletonbeanregistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/8/19
 **/
@Component
public class B {

    @Autowired
    private A a;

    public A getA(){
        return a;
    }
}
