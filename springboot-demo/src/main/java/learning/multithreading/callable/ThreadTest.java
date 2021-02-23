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

            int sum = futureTask.get(); //FutureTask.get()会阻塞,直到MyCallable执行完毕才能获取到返回值

            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         执行下此程序，我们发现sum = 4950永远都是最后输出的。而“主线程for循环执行完毕..”则很可能是在子线程循环中间输出。由CPU的线程调度机制，我们知道，“主线程for循环执行完毕..”的输出时机是没有任何问题的，那么为什么sum =4950会永远最后输出呢？
         原因在于通过ft.get()方法获取子线程call()方法的返回值时，当子线程此方法还未执行完毕，ft.get()方法会一直阻塞，直到call()方法执行完毕才能取到返回值。
         **/

    }
}