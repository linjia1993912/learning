package learning.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:java并发原子类AtomicInteger
 * 基于JDK1.8分析
 * @Author LinJia
 * @Date 2020/8/6
 **/
public class AtomicIntegerDemo {

    //我们知道java并发机制中主要有三个特性需要我们去考虑，原子性、可见性和有序性。
    // synchronized关键字可以保证可见性和有序性却无法保证原子性。
    // 而这个AtomicInteger的作用就是为了保证原子性。我们先看一个例子。

    //定义一个变量a
    private static volatile int a = 0;

    public static void main(String[] args) {
        //创建5个线程，每个线程a加10
        Thread[] threads = new Thread[5];
        for (int i = 0; i <5 ; i++) {
            threads[i] = new Thread(()->{
                try {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(a++);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        //测试证明 a并没有加到50，而且打印的结果有重复数字
        //变量a虽然用volatile修饰保证了可见性，但是没有保证原子性

        //如果在单线程中上面的例子没什么问题
        Thread thread = new Thread(()->{
            try {
                for (int j = 0; j < 50; j++) {
                    System.out.println(a++);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //对于a++的操作，其实可以分解为3个步骤。
    //（1）从主存中读取a的值
    //（2）对a进行加1操作
    //（3）把a重新刷新到主存
    //为什么在多线程就出现问题了？比如说有的线程已经把a进行了加1操作，但是还没来得及重新刷入到主存，其他的线程就重新读取了旧值。因为才造成了错误
    //如何解决？ 解决办法很多，其中一种使用AtomicInteger
    private static class AtomicIntegerDemo2{

        //使用AtomicInteger来定义a
        private static AtomicInteger a = new AtomicInteger();

        public static void main(String[] args) {
            //创建5个线程，每个线程a加10
            Thread[] threads = new Thread[5];
            for (int i = 0; i <5 ; i++) {
                threads[i] = new Thread(()->{
                    try {
                        for (int j = 0; j < 10; j++) {
                            //使用incrementAndGet函数进行自增操作
                            System.out.println(a.incrementAndGet());
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threads[i].start();
            }

            //测试结果正确,a加到了50，（可能会顺序不一样）

        }
    }

}
