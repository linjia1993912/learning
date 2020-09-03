package learning.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程计数器
 * countDownLatch是在java1.5被引入
 * 存在于java.util.cucurrent包下
 * countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 *
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，
 * 表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
 * @Author LinJia
 * @Date 2020/9/3
 **/
public class CountDownLatch_Demo {


    //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
    //public void await() throws InterruptedException { };
    //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
    //public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
    //将count值减1
    //public void countDown() { };

    //示例
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行…… ……");
        //第一个子线程执行
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                    System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //计数器-1
                latch.countDown();
            }
        });

        es1.shutdown();

        //第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                    System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        es2.shutdown();

        System.out.println("等待两个线程执行完毕…… ……");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }


    //模拟并发示例
    static class Parallellimit {
        public static void main(String[] args) {
            ExecutorService pool = Executors.newCachedThreadPool();
            CountDownLatch cdl = new CountDownLatch(100);
            for (int i = 0; i < 100; i++) {
                CountRunnable runnable = new CountRunnable(cdl);
                pool.execute(runnable);
            }
        }
    }

    static class CountRunnable implements Runnable {

        private CountDownLatch countDownLatch;

        public CountRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                synchronized (countDownLatch){
                    /*** 每次减少一个容量*/
                    countDownLatch.countDown();
                    System.out.println("thread counts = " + (countDownLatch.getCount()));
                }
                countDownLatch.await();
                System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
