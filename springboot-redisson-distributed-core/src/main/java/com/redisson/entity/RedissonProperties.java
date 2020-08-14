package com.redisson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description:读取redis配置信息，封装到当前实体类
 * @Author LinJia
 * @Date 2020/6/19
 **/
@PropertySource({"classpath:application.properties"})//加载指定配置文件
//@PropertySource 和 @ConfigurationProperties 组合使用，可以将属性文件与一个Java类绑定，将属性文件中的变量值注入到该Java类的成员变量中
@ConfigurationProperties(prefix = "redisson.lock.server")  //相当于绑定properties文件redisson.lock.server开头的配置
//@Data 注解的主要作用是提高代码的简洁，使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法，要使用 @Data 注解要先引入lombok
//@AllArgsConstructor ： 注在类上，提供类的全参构造
//@NoArgsConstructor ： 注在类上，提供类的无参构造
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedissonProperties {

    /**
     * redis主机地址，ip：port，有多个用半角逗号分隔
     */
    private String address;

    /**
     * 连接类型，支持standalone-单机节点，sentinel-哨兵，cluster-集群，masterslave-主从
     */
    private String type;

    /**
     * redis 连接密码
     */
    private String password;

    /**
     * 选取那个数据库
     */
    private int database;

    public RedissonProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public RedissonProperties setDatabase(int database) {
        this.database = database;
        return this;
    }

}
