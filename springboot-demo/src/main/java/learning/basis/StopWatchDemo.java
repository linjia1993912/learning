package learning.basis;

import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @Description:java计时器StopWatch
 * @Author LinJia
 * @Date 2021/1/29
 **/
public class StopWatchDemo {

    public static void main(String[] args) {
        //一般我们检测某段代码执行的时间，都是以如下方式来进行的：
        long startTime=System.currentTimeMillis();   //获取开始时间
        //业务代码...
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");


        //使用StopWatch类
        //引入依赖jar包：
        /*<dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.6</version>
        </dependency>*/

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //业务代码
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println("程序运行时间： "+stopWatch.getTotalTimeMillis()+"ms");

    }
}