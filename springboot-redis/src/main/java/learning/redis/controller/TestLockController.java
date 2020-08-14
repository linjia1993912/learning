package learning.redis.controller;

import learning.redis.util.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description: jmeter模拟多用户抢单
 * @Author LinJia
 * @Date 2020/6/17
 **/
@RestController
public class TestLockController {

    private Logger logger = LoggerFactory.getLogger(TestLockController.class);

    @Autowired
    private RedisPoolUtil redisPoolUtil;

    //总库存
    private long inventory = 10;
    //商品key名字
    private String goodsKey = "coke";
    //设置锁超时时间
    final int timeoutSecond = 10;

    /**
     * @return java.lang.String
     * @Description:抢单
     * @Author LinJia
     * @Date 2020/6/17 15:31
     * @Param []
     **/
    @PostMapping("/grabSingle")
    public String grabSingle() {
        //商品是否剩余
        if (inventory <= 0) {
            logger.info("来晚了，没货了");
            return "无库存";
        }
        //得到线程id  当作用户
        long threadId = Thread.currentThread().getId();
        //加锁
        if (redisPoolUtil.tryGetDistributedLock(goodsKey, String.valueOf(threadId), timeoutSecond)) {
            //用户拿到锁
            logger.info("用户 "+threadId+" 拿到锁");
            try {
                //商品是否剩余
                if (inventory <= 0) {
                    logger.info("来晚了，没货了");
                    return "无库存";
                }
                //模拟生成订单耗时操作
                TimeUnit.SECONDS.sleep(1);

                //抢购成功，商品递减，记录用户
                inventory -= 1;

                //抢单成功跳出
                logger.info("抢单成功...所剩库存：" + inventory);

                return "抢单成功，所剩库存：" + inventory;

            } catch (Exception e) {
                e.printStackTrace();
                return "抢单失败，系统出错";
            } finally {
                //防止误删锁 释放锁之前判断一下是否是当前用户
                if(String.valueOf(threadId).equals(redisPoolUtil.get(goodsKey))){
                    logger.info("用户 "+threadId+" 释放锁...");
                    //释放锁
                    redisPoolUtil.releaseDistributedLock(goodsKey, String.valueOf(threadId));
                }
            }
        } else {
            logger.info("手慢了");
        }
        return "";
    }
}
