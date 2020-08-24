package learning.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: controller是单例还是多例？
 * controller默认是单例的，不要使用非静态的成员变量，否则会发生数据逻辑混乱。正因为单例所以不是线程安全的。
 * @Author LinJia
 * @Date 2020/8/24
 **/
@Scope("prototype")
@RestController
public class ScopeTestController {
    private int num = 0;

    @GetMapping("/testScope")
    public void testScope() {
        System.out.println(++num);
    }

    @GetMapping("/testScope2")
    public void testScope2() {
        System.out.println(++num);
    }

    //我们首先访问 http://localhost:8080/testScope，得到的答案是1；
    //然后我们再访问 http://localhost:8080/testScope2，得到的答案是 2。
    //得到的不同的值，这是线程不安全的


    //接下来我们再来给controller增加作用多例 @Scope("prototype")
    //再次访问
    //我们依旧首先访问 http://localhost:8080/testScope，得到的答案是1；
    //然后我们再访问 http://localhost:8080/testScope2，得到的答案还是 1。
    //相信大家不难发现 ：
    //单例是不安全的，会导致属性重复使用。

    //解决方案
    //不要在controller中定义成员变量。
    //万一必须要定义一个非静态成员变量时候，则通过注解@Scope(“prototype”)，将其设置为多例模式
    //在Controller中使用ThreadLocal变量
}

