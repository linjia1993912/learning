package learning.multithreading.threadlocal;

/**
 * @Description:同一个ThreadLocal变量在父线程中被设置值后，在子线程中是获取不到的。
 * （threadLocals中为当前调用线程对应的本地变量，所以二者自然是不能共享的）
 * @Author LinJia
 * @Date 2020/7/22
 **/
public class ThreadLocalTest2 {

    //(1)创建ThreadLocal变量
    private static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {

        //在main线程中添加main线程的本地变量
        threadLocal.set("main");
        //新创建一个子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //ThreadLocal不支持继承性,子线程threadLocal为null
                System.out.println(threadLocal.get());
            }
        });

        thread.start();

        //输出main线程中的本地变量值
        System.out.println(threadLocal.get());

        threadLocal.remove();
    }
}
