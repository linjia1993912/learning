package com.example.processor;

/**
 * @Description: 演示Spring前置后置处理器
 * @Author LinJia
 * @Date 2020/8/24
 **/
public class ISomeService implements BaseService {
    @Override
    public String doSomething() {
        // 增强效果：返回内容全部大写
        return "Hello i am kxm";
    }

    @Override
    public String eat() {
        return "eat food";
    }
}
