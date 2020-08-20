package com.example.singletonbeanregistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/8/19
 **/
@Component
public class A {

    @Autowired
    private B b;

    public B getB(){
        return b;
    }
}
