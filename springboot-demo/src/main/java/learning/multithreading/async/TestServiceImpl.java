package learning.multithreading.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author LinJia
 * @description: Springboot 使用异步任务
 * @Async使用
 * （1）类或者方法中使用@Async注解，类上标有该注解表示类中方法都是异步方法，方法上标有该注解表示方法是异步方法；
 * （2）@Async(“threadPool”),threadPool为自定义线程池，这样可以保证主线程中调用多个异步任务时能更高效的执行。
 * Spring boot异步任务@Async失效问题
 * 总结：1、启动类是否开启异步服务；
 * 2、在定义异步方法的同一个类中，调用带有@Async注解方法，该方法则无法异步执行；
 * 3、注解的方法必须是public方法，不能是static；
 * 4、没有走Spring的代理类。因为@Transactional和@Async注解的实现都是基于Spring的AOP，而AOP的实现是基于动态代理模式实现的。那么注解失效的原因就很明显了，有可能因为调用方法的是对象本身而不是代理对象，因为没有经过Spring容器管理。
 * @date 2021/4/1
 */
@Service
public class TestServiceImpl implements TestCRMService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //定义异步类或方法：如果异步方法需要返回值，可以用Future<Object>接收

    @Override
    @Async("asyncServiceExecutor")// 自定义执行线程池
    public void sendMessage() {
        try {
            Thread.sleep(1000);
            logger.info("sendMessage");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //实际业务
    }

    @Override
    @Async("asyncServiceExecutor")// 自定义执行线程池
    public void sendMail() {
        try {
            Thread.sleep(1000);
            logger.info("sendMail");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //实际业务
    }
}