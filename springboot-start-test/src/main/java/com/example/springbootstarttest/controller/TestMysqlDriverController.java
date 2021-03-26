package com.example.springbootstarttest.controller;

import com.example.springbootstart.core.MysqlDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LinJia
 * @description: 测试start
 * @date 2021/3/26
 */
@RestController
public class TestMysqlDriverController {

    @Autowired
    private MysqlDriver mysqlDriver;

    //启动项目访问该接口测试
    @GetMapping("testMysqlDriver")
    public String testMysqlDriver(){
        return mysqlDriver.printDriverData();
    }
}