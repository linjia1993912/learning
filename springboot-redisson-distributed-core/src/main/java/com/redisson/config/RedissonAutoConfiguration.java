package com.redisson.config;

import com.redisson.entity.RedissonProperties;
import com.redisson.util.RedissonLock;
import org.redisson.Redisson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Description:redisson自动化配置
 * 自动装配
 * @Author LinJia
 * @Date 2020/6/19
 **/
@Configuration
@ConditionalOnClass(Redisson.class) //如果存在redisson 则加载RedissonAutoConfiguration配置类（自动装配）
//如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，那么在IOC容器中是获取不到properties 配置文件转化的bean
//@EnableConfigurationProperties 相当于把使用 @ConfigurationProperties 的类进行了一次注入
@EnableConfigurationProperties(RedissonProperties.class) //使使用 @ConfigurationProperties 注解的类生效
public class RedissonAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonAutoConfiguration.class);

    /**
     * @Description:注入RedissonManager
     * @Author LinJia
     * @Date 2020/6/19 16:07
     * @Param [redissonProperties]
     * @return com.redisson.config.RedissonManager
     **/
    @Bean
    @ConditionalOnMissingBean  //如果存在RedissonManager就不注入该bean
    @Order(value = 1) //IOC容器bean的执行顺序
    public RedissonManager redissonManager(RedissonProperties redissonProperties) {
        RedissonManager redissonManager =
                new RedissonManager(redissonProperties);
        LOGGER.info("[RedissonManager]组装完毕,当前连接方式:" + redissonProperties.getType() +
                ",连接地址:" + redissonProperties.getAddress());
        return redissonManager;
    }

    /**
     * @Description:注入RedissonLock
     * @Author LinJia
     * @Date 2020/6/19 16:07
     * @Param [redissonManager]
     * @return RedissonLock
     **/
    @Bean
    @ConditionalOnMissingBean
    @Order(value = 2)
    public RedissonLock redissonLock(RedissonManager redissonManager) {
        RedissonLock redissonLock = new RedissonLock(redissonManager);
        LOGGER.info("[RedissonLock]组装完毕");
        return redissonLock;
    }


}
