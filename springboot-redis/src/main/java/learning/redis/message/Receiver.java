package learning.redis.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:消息接受者
 * REcevier类，它是一个普通的类，需要注入到springboot中。
 * @Author LinJia
 * @Date 2020/9/3
 **/
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    //线程计数器
    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        latch.countDown();
    }
}
