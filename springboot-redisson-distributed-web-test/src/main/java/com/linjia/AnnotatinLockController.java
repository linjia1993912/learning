package com.linjia;

import com.redisson.annotation.DistributedLock;
import com.redisson.util.RedissonLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:测试基于注解加锁
 * @Author LinJia
 * @Date 2020/6/22
 **/
@RestController
@Slf4j
public class AnnotatinLockController {

    @Autowired
    RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static volatile Integer TOTAL = 10;

    /**
     * @Description:模拟减少库存
     * @Author LinJia
     * @Date 2020/6/22 14:21
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("annotatin-lock-decrease-stock")
    @DistributedLock(value="goods", leaseTime=5)
    public String lockDecreaseStock() throws InterruptedException {
        if (TOTAL > 0) {
            TOTAL--;
        }
        log.info("===注解模式=== 减完库存后,当前库存===" + TOTAL);
        return "=================================";
    }

}
