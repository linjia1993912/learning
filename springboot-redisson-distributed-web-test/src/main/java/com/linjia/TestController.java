package com.linjia;

import com.redisson.util.RedissonLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:测试redisson分布式锁
 * @Author LinJia
 * @Date 2020/6/19
 **/
@RestController
@Slf4j
public class TestController {

    @Autowired
    RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static volatile Integer TOTAL = 10;

    /**
     * @Description:模拟减少商品库存
     * 没有出现超卖现象
     * lock性能要比tryLock高
     * @Author LinJia
     * @Date 2020/6/19 16:46
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("lock-decrease-stock")
    public String lockDecreaseStock() throws InterruptedException {
        redissonLock.lock("lock", 10L);
        if (TOTAL > 0) {
            TOTAL--;
        }
        Thread.sleep(50);
        log.info("===lock===减完库存后,当前库存===" + TOTAL);
        //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
        if (redissonLock.isHeldByCurrentThread("lock")) {
            redissonLock.unlock("lock");
        }
        return "=================================";
    }

    /**
     * @Description:tryLock锁是可能会跳过减库存的操作，因为当过了等待时间还没有获取锁，就会返回false,这显然很致命
     * @Author LinJia
     * @Date 2020/6/19 17:31
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("trylock-decrease-stock")
    public String trylockDecreaseStock() throws InterruptedException {
        if (redissonLock.tryLock("trylock", 10L, 5L)) {
            if (TOTAL > 0) {
                TOTAL--;
            }
            Thread.sleep(50);
            redissonLock.unlock("trylock");
            log.info("====tryLock===减完库存后,当前库存===" + TOTAL);
        } else {
            log.info("[ExecutorRedisson]获取锁失败");
        }
        return "===================================";
    }

}
