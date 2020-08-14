package learning.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @Description:redis常用操作
 * @Author LinJia
 * @Date 2020/6/16
 **/
@Service
public class RedisPoolUtil {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    //当key不存在时set值
    private static final String SET_IF_NOT_EXIST = "NX";
    //超时时间单位是毫秒
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    @Autowired
    private JedisPool redisPool;

    public void set(String key, String value) {
        Jedis jedis = redisPool.getResource();
        try {
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = redisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            return result;
        }
    }

    /**
     * @return java.lang.Long
     * @Description:加锁 有风险写法
     * @Author LinJia
     * @Date 2020/6/17 9:52
     * @Param [key, value]
     **/
    public Long setnx(String key, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = redisPool.getResource();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            return result;
        }
    }

    /**
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return boolean
     * @Description:加锁 推荐写法
     * @Author LinJia
     * @Date 2020/6/17 9:54
     **/
    public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = redisPool.getResource();
            //添加参数
            /**
             *   SetParams 参数说明
             *   private static final String XX = "xx"; 当key存在时set值
                 private static final String NX = "nx"; 当key不存在时set值
                 private static final String PX = "px"; 超时时间单位是毫秒
                 private static final String EX = "ex"; 超时时间单位是秒
             **/
            SetParams setParams = SetParams.setParams().ex(expireTime).nx();
            //3.1.0版本无此方法
            //String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            String result = jedis.set(lockKey, requestId, setParams);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public String getSet(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = redisPool.getResource();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            return result;
        }
    }

    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = redisPool.getResource();
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            return result;
        }
    }

    /**
     * @return java.lang.Long
     * @Description: 最常见的解锁代码就是直接使用jedis.del()方法删除锁，这种不先判断锁的拥有者而直接解锁的方式，
     * 会导致任何客户端都可以随时进行解锁，即使这把锁不是它的
     * @Author LinJia
     * @Date 2020/6/17 9:31
     * @Param [key]
     **/
    public Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = redisPool.getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            return result;
        }
    }

    /**
     * 释放分布式锁 推荐
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            jedis = redisPool.getResource();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

}
