package com.redisson.strategy;

import com.redisson.entity.RedissonProperties;
import org.redisson.config.Config;

/**
 * @Description:Redisson配置构建接口
 * @Author: linJia
 * @Date: 2020/6/19 15:55
 */
public interface RedissonConfigService {

    Config createRedissonConfig(RedissonProperties redissonProperties);
}
