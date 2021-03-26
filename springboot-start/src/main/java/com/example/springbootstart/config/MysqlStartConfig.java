package com.example.springbootstart.config;

import com.example.springbootstart.core.MysqlDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LinJia
 * @description: 自动装配入口，将MysqlStartCore注入IOC
 * 在spring.factories里加载该配置类
 * @date 2021/3/26
 */
@Configuration
@EnableConfigurationProperties(MysqlDriverProperties.class) //引入配置类
public class MysqlStartConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlStartConfig.class);
    /**
     * 相当于将MysqlDriver注入IOC
     * @return
     */
    @Bean
    public MysqlDriver mysqlDriver(MysqlDriverProperties mysqlDriverProperties){
        MysqlDriver mysqlDriver = new MysqlDriver(mysqlDriverProperties);
        LOGGER.info("[MysqlDriver]组装完毕");
        return mysqlDriver;
    }
}