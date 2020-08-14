package com.redisson.config;

import com.redisson.entity.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * @Description:Redisson核心配置，用于提供初始化的redisson实例
 * @Author LinJia
 * @Date 2020/6/19
 **/
@Slf4j
public class RedissonManager {

    private Config config = new Config();

    private Redisson redisson = null;

    public RedissonManager(RedissonProperties redissonProperties) {
        try{
            //通过不同部署方式获得不同cofig实体
            config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
            redisson = (Redisson) Redisson.create(config);
        }catch (Exception e){
            log.error("Redisson init error", e);
            throw new IllegalArgumentException("please input correct configurations," +
                    "connectionType must in standalone/sentinel/cluster/masterslave");
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }
}
