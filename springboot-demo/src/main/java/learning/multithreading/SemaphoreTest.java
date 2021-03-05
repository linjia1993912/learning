package learning.multithreading;

import java.util.concurrent.Semaphore;

/**
 * @Description:Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 * acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
 * release()用来释放许可。注意，在释放许可之前，必须先获获得许可。
 * @Author LinJia
 * @Date 2021/3/5
 **/
public class SemaphoreTest {

    public static void main(String[] args) {
        //参数permits表示许可数目，即同时可以允许多少线程进行访问
        //Semaphore还有一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i <10 ; i++) {
            new Thread(new Worker(semaphore,i)).start();
        }
    }

    static class Worker implements Runnable{

        Semaphore semaphore;
        int num;

        public Worker(Semaphore semaphore,int i){
            this.semaphore = semaphore;
            this.num = i;
        }

        @Override
        public void run() {
            try {
                //获取许可
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");

                Thread.sleep(2000);

                //释放许可
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}