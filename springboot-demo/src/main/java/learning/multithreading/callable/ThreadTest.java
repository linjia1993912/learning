package learning.multithreading.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:使用Callable和Future接口创建线程
 * @Author LinJia
 * @Date 2021/2/23
 **/
public class ThreadTest {

    public static void main(String[] args) {
        //创建MyCallable对象
        Callable<Integer> callable = new MyCallable();
        ////使用FutureTask来包装MyCallable对象
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(futureTask);   //FutureTask对象作为Thread对象的target创建新的线程
                thread.start();                      //线程进入到就绪状态
            }
        }

        System.out.println("主线程for循环执行完毕..");
        try {

            int sum = futureTask.get();

            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}