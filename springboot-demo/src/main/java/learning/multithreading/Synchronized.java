package learning.multithreading;

/**
 * @Description: synchronized用法
 *  修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象；
    修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象；
    修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象；
    修改一个类，其作用的范围是synchronized后面括号括起来的部分，作用主的对象是这个类的所有对象
 * @Author LinJia
 * @Date 2020/7/3
 **/
public class Synchronized implements Runnable {

    //特殊的instance变量
    private byte[] lock = new byte[0];

    //创建一个计数器
    private static int count = 0;

    @Override
    public void run() {

        //修饰一个代码块
        //一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞
        synchronized (this){
            for (int i = 0; i < 5 ; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //当没有明确的对象作为锁，只是想让一段代码同步时，可以创建一个特殊的对象来充当锁 lock
        /*synchronized (lock){
            for (int i = 0; i < 5 ; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    public int getCount(){
        return count;
    }


    //修饰一个静态方法
    //静态方法属于类不属于对象,就算创建多个SynchronizedDemo对象也持有同一把锁
    private static class SynchronizedDemo implements Runnable{

        public synchronized static void method(){
            for (int i = 0; i < 5 ; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //访问静态方法同时访问非静态方法 看看是否是互斥的
        public void method1(){
            for (int i = 0; i < 5 ; i++) {
                try {
                    System.out.println("method1"+Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            method();
        }
    }


    //修饰一个类
    //synchronized作用于一个类时，是给这个类加锁，类的所有对象用的是同一把锁
    private static class SynchronizedDemo2 implements Runnable{

        private static int count = 0;

        public void method(){
            //修饰类
            synchronized(SynchronizedDemo2.class){
                for (int i = 0; i < 5 ; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + (count++));
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void run() {
            method();
        }
    }


    public static void main(String[] args) {
        //2个线程并发访问 两个线程互斥
        Synchronized syncThread = new Synchronized();
        /*Thread thread1 = new Thread(syncThread,"sync-thread-1");
        Thread thread2 = new Thread(syncThread,"sync-thread-2");
        thread1.start();
        thread2.start();*/

        //修改代码
        //两个线程锁不是互斥的  线程同时执行  this锁的当前对象
        /*Thread thread1 = new Thread(new Synchronized(),"sync-thread-1");
        Thread thread2 = new Thread(new Synchronized(),"sync-thread-2");
        thread1.start();
        thread2.start();*/

        //synchronized修饰静态方法
        Thread thread1 = new Thread(new SynchronizedDemo(),"sync-thread-1");
        Thread thread2 = new Thread(new SynchronizedDemo(),"sync-thread-2");
        thread1.start();
        thread2.start();
        //同时访问非静态方法
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.method1();

        //synchronized修饰类
        /*Thread thread1 = new Thread(new SyncThread.SynchronizedDemo2(),"sync-thread-1");
        Thread thread2 = new Thread(new SyncThread.SynchronizedDemo2(),"sync-thread-2");
        thread1.start();
        thread2.start();*/

    }
}
