package redis;

import learning.App;
import learning.redis.util.RedisPoolUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/6/17
 **/
@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest(classes = App.class)
public class LockTest {

    private Logger logger = LoggerFactory.getLogger(LockTest.class);

    @Autowired
    private RedisPoolUtil redisPoolUtil;

    //总库存
    private long inventory = 0;
    //商品key名字
    private String goodsKey = "coke";
    //锁超时时间
    final int timeoutSecond = 60;
    //获取锁的超时时间
    final int timeout = 30 * 1000;

    /**
     * @return void
     * @Description:模拟抢单
     * @Author LinJia
     * @Date 2020/6/17 14:22
     * @Param []
     **/
    @Test
    public void grabSingle() {
        //抢到商品的用户
        List<String> shopUsers = new ArrayList<>();
        //制造抢单用户
        List<String> users = new ArrayList<>();
        IntStream.range(0, 30).parallel().forEach(b -> {
            users.add("linjia-" + b);
        });

        //初始化库存
        inventory = 10;

        //模拟开抢
        users.parallelStream().forEach(b -> {
            String shopUser = qiang(b);
            if (!StringUtils.isEmpty(shopUser)) {
                shopUsers.add(shopUser);
            }
        });

        logger.info("抢单结束" + shopUsers.toString());
    }

    /**
     * @Description:抢单操作
     * @Author LinJia
     * @Date 2020/6/17 15:21
     * @Param [user]
     * @return java.lang.String
     **/
    public String qiang(String user) {
        //用户开抢时间
        long startTime = System.currentTimeMillis();

        //未抢到的情况下，30秒内继续获取锁
        while ((startTime + timeout) >= System.currentTimeMillis()) {
            //商品是否剩余
            if (inventory <= 0) {
                break;
            }

            if (redisPoolUtil.tryGetDistributedLock(goodsKey, user, timeoutSecond)) {
                //用户拿到锁
                logger.info("用户 " + user + " 拿到锁");

                try {
                    //商品是否剩余
                    if (inventory <= 0) {
                        break;
                    }
                    //模拟生成订单耗时操作
                    TimeUnit.SECONDS.sleep(3);

                    //抢购成功，商品递减，记录用户
                    inventory -= 1;

                    //抢单成功跳出
                    logger.info("用户 " + user + " 抢单成功跳出...所剩库存：" + inventory);

                    return user + "抢单成功，所剩库存：" + inventory;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    logger.info("用户 " + user + " 释放锁...");
                    //释放锁
                    redisPoolUtil.releaseDistributedLock(goodsKey, user);
                }

            } else {
                //用户没拿到锁，在超时范围内继续请求锁
//                logger.info("用户 " + user + " 等待获取锁...");
            }
        }
        return "";
    }


    /**
     * @return void
     * @Description:测试插入数据
     * @Author LinJia
     * @Date 2020/6/17 13:36
     * @Param []
     **/
    @Test
    public void testSet() {
        redisPoolUtil.set("20200617", "1111");
    }
}
