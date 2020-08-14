package com.example.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:写一个API接口“／hi”，返回从配置中心读取的foo变量的值
 * @Author LinJia
 * @Date 2020/8/12
 **/
@RestController

//使用消息总线刷新配置文件
//RefreshScope是spring cloud提供的一种特殊的scope实现，用来实现配置、实例热加载。
@RefreshScope
public class ConfigClientController {

    @Value("${foo}")
    String foo;

    /**
     * @Description:测试
     * 如果获取到配置，这就说明，config-client从config-server获取了foo的属性，而config-server是从git仓库读取的
     * @Author LinJia
     * @Date 2020/8/12 14:53
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "forezp") String name){
        return foo;
    }
}
