package com.example.service.impl;

import com.example.annotation.SelfAutowired;
import com.example.annotation.SelfService;
import com.example.service.OrderService;
import com.example.service.UserService;

/**
 * @Description: 自定义@Service类
 * 假如在我们某个标注了@Service的类里面有下面这样的注解，即依赖了其他的某个bean,
 * 比如，在我们的userService类里面依赖了orderService，就形成了所谓的依赖注入
 * @Author LinJia
 * @Date 2020/8/18
 **/
@SelfService
public class UserServiceImpl implements UserService{

    //依赖注入
    //这块的属性名称一定要用实现类来命名 且 按照第一个字母要小写的原则 否则很报错的
    @SelfAutowired
    private OrderService orderServiceImpl;

    @Override
    public void test() {
        orderServiceImpl.test();
        System.out.println("使用java反射机制初始化对象...");
    }
}
