package learning.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示lock锁的使用方式
 * ReentrantLock是Lock接口的实现
 * @Author LinJia
 * @Date 2020/7/3
 **/
public class LockDemo {

    private Lock lock = new ReentrantLock();

    // lock()加锁
    private void method(Thread thread){
        lock.lock();
        try {
            System.out.println("线程名"+thread.getName() + "获得了锁");
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("线程名"+thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    //tryLock()加锁
    private void method1(Thread thread){
        if(lock.tryLock()){
            try {
                System.out.println("线程名"+thread.getName() + "获得了锁");
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                System.out.println("线程名"+thread.getName() + "释放了锁");
                lock.unlock();
            }
        }else{
            System.out.println("我是"+Thread.currentThread().getName()+"有人占着锁，我就不要啦");
        }
    }


    public static void main(String[] args) {
        LockDemo lockTest = new LockDemo();
        //线程1
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t1");
        //线程2
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
