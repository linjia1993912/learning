package learning.multithreading.executor;

import com.zaxxer.hikari.util.UtilityElf;

import java.util.concurrent.*;

/**
 * @Description:Java方式创建线程池
 *
 *  ThreadPoolExecutor线程池参数详解
 *  int corePoolSize, // 线程池长期维持的线程数，即使线程处于Idle(空闲)状态，也不会回收。
    int maximumPoolSize, // 线程数的上限
    long keepAliveTime, // 超过corePoolSize的线程的idle时长，
    TimeUnit unit, // 时间单位，超过这个时间，多余的线程会被回收。
    BlockingQueue<Runnable> workQueue, // 任务的排队队列
    ThreadFactory threadFactory, // 新线程的产生方式
    RejectedExecutionHandler handler) // 拒绝策略

    Executors为线程工具类

 * @Author LinJia
 * @Date 2020/7/23
 **/
public class ThreadPoolExecutorConfig {

    //演示Executors创建线程池，以及各个创建线程池优缺点
    //不推荐
    static class ExecutorsDemo{


        public static void main(String[] args) {

            //Executors创建返回ThreadPoolExecutor对象的方法共有三种：
            //Executors#newCachedThreadPool => 创建可缓存的线程池
            //Executors#newSingleThreadExecutor => 创建单线程的线程池
            //Executors#newFixedThreadPool => 创建固定长度的线程池

            /**
             * 创建固定长度的线程池
             *
             * 核心线程数和最大线程数是一样的，所以称之为固定线程数。
             *
             * 创建2个线程来执行10个任务。
             */
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            for (int i = 0; i < 10; i++) {
                // 从结果中可以发现线程name永远都是两个。不会有第三个。
                executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
            }
            /**
             * 问题
             * 通过查看newFixedThreadPool源码可以发现，newFixedThreadPool使用了无界队列LinkedBlockingQueue
             * 高并发情况下极大的可能OOM了然后任务还在堆积
             */


            /**
             * 创建单线程的线程池
             *
             * 核心线程数和最大线程数是1，内部默认的，不可更改，所以称之为单线程数的线程池,类似于Executors.newFixedThreadPool(1);
             * 其他参数配置默认为：永不超时（0ms），无界队列（LinkedBlockingQueue）、
             * 默认线程工厂（DefaultThreadFactory）、直接拒绝策略（AbortPolicy）。
             *
             * 创建1个线程来执行10个任务。
             */
            ExecutorService executorService1 = Executors.newSingleThreadExecutor();
            for (int i = 0; i <10 ; i++) {
                // 从结果中可以发现线程name永远都是pool-1-thread-1。不会有第二个出现。
                executorService1.execute(()-> System.out.println(Thread.currentThread().getName()));
            }
            /**
             * 问题同上，同样采用无界队列
             * newSingleThreadExecutor核心线程数和最大线程数都是1，写死的，客户端不可更改。
             */


            /**
             * 创建可缓存的线程池
             *
             * 他的功能是来个任务我就开辟个线程去处理，不会进入队列，SynchronousQueue队列也不带
             * 存储元素的功能。那这意味着来一亿个请求就会开辟一亿个线程去处理，keepAliveTime为60S，
             * 意味着线程空闲时间超过60S就会被杀死；这就叫带缓存功能的线程池。
             *
             * 创建个带缓存功能的线程池来执行10个任务。
             */
            ExecutorService executorService2 = Executors.newCachedThreadPool();
            for (int i = 0; i <10 ; i++) {
                // 从结果中可以发现线程name有10个。也就是有几个任务就会开辟几个线程。
                executorService2.execute(()-> System.out.println(Thread.currentThread().getName()));
            }
            /**
             * 问题就在于他的最大线程数是int的最大值，因为他内部采取的队列是SynchronousQueue，
             * 这个队列没有容纳元素的能力，这将意味着只要来请求我就开启线程去工作，巅峰期能创建二十几亿个线程出来工作，
             * 你自己想想多么可怕！！！
             * 核心线程数是0，最大线程数是int的最大值(非常致命)，内部默认的，不可更改。
             */


            /**
             * 有调度功能的线程池
             * 核心线程数手动传进来，最大线程数是Integer.MAX_VALUE，最大线程数是内部默认的，不可更改。
             *
             * 创建个带调度功能的线程池来执行任务。
             */
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            // 五秒一次
            scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName()), 5, TimeUnit.SECONDS);
            // 首次五秒后执行，其次每隔1s执行一次
            scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()), 5, 1, TimeUnit.SECONDS);
            /**
             * 问题还是他的最大线程数是int的最大值
             * 在高并发的情况下是非常致命的
             */
        }

    }

    //演示自定义线程池，手动创建线程池
    //手动创建线程池我们能自己控制线程数大小以及队列大小，还可以指定组名称等等个性化配置。重点不会出现致命问题，风险都把控在我们手里。
    public static void main(String[] args) {
        //不要使用Executors.newXXXThreadPool()快捷方法创建线程池，因为这种方式会使用无界的任务队列，为避免OOM，
        // 我们应该使用ThreadPoolExecutor的构造方法手动指定队列的最大长度
        //new ArrayBlockingQueue<Runnable>(512)使用有界队列，避免OOM
        /*ExecutorService executorService = new ThreadPoolExecutor(5, 8, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(512), new ThreadPoolExecutor.DiscardPolicy());*/

        //推荐的线程池配置方式
        ExecutorService executorService = null;
        //availableProcessors方法返回到Java虚拟机的可用的处理器数量
        //线程池核心线程数推荐cpu核心数+1
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        //最大线程数 = 核心数*2 推荐
        int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        //创建有界队列，队列要指定大小，避免OOM
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
        //创建拒绝策略
        /**
         * @Description:
         * 拒绝策略
         * rejectedExectutionHandler参数字段用于配置绝策略，常用拒绝策略如下
         * AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
         * CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
         * DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
         * DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
         **/
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
        executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0, TimeUnit.SECONDS,
                queue, rejectedExecutionHandler);
        System.out.println("线程池初始化完成");

        //获取线程池处理的结果和异常都被包装到Future中，都被包装到Future中
        //执行过程中的异常会被包装成ExecutionException
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                // 该异常会在调用Future.get()时传递给调用者
//                throw new RuntimeException("exception in call~");
                //返回结果
                return "返回结果";
            }
        });
        try {
            //get方法还可以设置等待的超时时间 get(long timeout, TimeUnit unit)
            Object result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //这里会获取到线程池抛出的异常
            e.printStackTrace();
        }
    }
}
