package com.example.springbootstart.core;

import com.example.springbootstart.config.MysqlDriverProperties;

import java.text.MessageFormat;

/**
 * @author LinJia
 * @description: 模拟加载一个Mysql驱动
 * 业务自定，这里简单化,实际比这个复杂的多
 * @date 2021/3/26
 */

public class MysqlDriver {

    private String ip;
    private String port;

    public MysqlDriver(MysqlDriverProperties mysqlDriverProperties) {
        this.ip = mysqlDriverProperties.getIp();
        this.port = mysqlDriverProperties.getPort();
    }

    public String printDriverData(){
        System.out.println(MessageFormat.format("MysqlDriver-{0}:{1}",ip,port));
        return "MysqlDriver";
    }
}