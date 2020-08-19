package learning.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 为什么阿里开发手册禁止使用Executors创建线程池
 * 理论上会出现OOM异常，必须测试一波验证之前的说法
 * @Author LinJia
 * @Date 2020/8/19
 **/
public class OOMTest {

    //使用Executors创建的CachedThreadPool，往线程池中无限添加线程在启动测试类之前先将JVM内存调整小一点，不然很容易将电脑跑出问题
    //在idea里：Run -> Edit Configurations
    //JVM参数说明 -Xms10M => Java Heap内存初始化值        -Xmx10M => Java Heap内存最大值
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        int i = 0;
        while (true) {
            int num = i++;
            es.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第 "+num+" 个");
                }
            });
        }
    }

    //创建到5w多个线程的时候开始报OOM异常
}
