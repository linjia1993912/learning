package com.example.springbootstart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LinJia
 * @description: 模拟读取用户配置
 * @date 2021/3/26
 */
@ConfigurationProperties(prefix = "mysql.test")  //相当于绑定properties文件redisson.lock.server开头的配置
public class MysqlDriverProperties {

    //默认的配置 如果配置文件没有配置 则使用默认的配置
    private static final String IP = "192.168.0.1";
    private static final String PORT = "3306";

    private String ip = IP;
    private String port = PORT;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}