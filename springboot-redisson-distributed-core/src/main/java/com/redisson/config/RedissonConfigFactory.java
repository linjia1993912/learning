package com.redisson.config;

import com.google.common.base.Preconditions;
import com.redisson.constant.RedisConnectionType;
import com.redisson.entity.RedissonProperties;
import com.redisson.strategy.RedissonConfigService;
import com.redisson.strategy.impl.ClusterConfigImpl;
import com.redisson.strategy.impl.MasterslaveConfigImpl;
import com.redisson.strategy.impl.SentineConfigImpl;
import com.redisson.strategy.impl.StandaloneConfigImpl;
import org.redisson.config.Config;

/**
 * @Description:redisson连接方式配置工厂
 * 根据不同的类型 获取不同的配置
 *
 * 单例模式双重锁 保证只有一个实例，在双重检查锁中，代码会检查两次单例类是否有已存在的实例，一次加锁一次不加锁，一次确保不会有多个实例被创建
 *
 * @Author LinJia
 * @Date 2020/6/19
 **/
public class RedissonConfigFactory {

    private RedissonConfigFactory(){

    }

    /** volatile关键字的作用：保证了变量的可见性（visibility）。被volatile关键字修饰的变量，如果值发生了变更，其他线程立马可见，
     * 避免出现脏读的现象
    */
    private static volatile RedissonConfigFactory factory = null;

    public static RedissonConfigFactory getInstance() {
        if (factory == null) {
            synchronized (Object.class) {
                if (factory == null) {
                    factory = new RedissonConfigFactory();
                }
            }
        }
        return factory;
    }

    /**
     * @Description:根据连接类型获取对应连接方式的配置
     * @Author LinJia
     * @Date 2020/6/19 15:54
     * @Param [redissonProperties]
     * @return org.redisson.config.Config
     **/
    Config createConfig(RedissonProperties redissonProperties) {
        Preconditions.checkNotNull(redissonProperties);
        Preconditions.checkNotNull(redissonProperties.getAddress(), "redisson.lock.server.address cannot be NULL!");
        Preconditions.checkNotNull(redissonProperties.getType(), "redisson.lock.server.password cannot be NULL");
        Preconditions.checkNotNull(redissonProperties.getDatabase(), "redisson.lock.server.database cannot be NULL");
        String connectionType = redissonProperties.getType();
        //声明配置上下文
        RedissonConfigService redissonConfigService = null;
        if (connectionType.equals(RedisConnectionType.STANDALONE.getConnection_type())) {
            redissonConfigService = new StandaloneConfigImpl();
        } else if (connectionType.equals(RedisConnectionType.SENTINEL.getConnection_type())) {
            redissonConfigService = new SentineConfigImpl();
        } else if (connectionType.equals(RedisConnectionType.CLUSTER.getConnection_type())) {
            redissonConfigService = new ClusterConfigImpl();
        } else if (connectionType.equals(RedisConnectionType.MASTERSLAVE.getConnection_type())) {
            redissonConfigService = new MasterslaveConfigImpl();
        } else {
            throw new IllegalArgumentException("创建Redisson连接Config失败！当前连接方式:" + connectionType);
        }
        return redissonConfigService.createRedissonConfig(redissonProperties);
    }
}
