package learning.design_mode.future;


import java.util.concurrent.*;

/**
 * @Description:Future模式
    该模式的核心思想是异步调用. 有点类似于异步的ajax请求.
    当调用某个方法时, 可能该方法耗时较久, 而在主函数中也不急于立刻获取结果.
    因此可以让调用者立刻返回一个凭证, 该方法放到另外线程执行
 * @Author LinJia
 * @Date 2020/7/14
 **/
public class FutureDemo1 {

    /**
     * @Description:通过FutureTask实现
     * @Author LinJia
     * @Date 2020/7/14 9:53
     * @Param [args]
     * @return void
     **/
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return new RealData().costTime();
            }
        });

        //线程池
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(futureTask);

        System.out.println("RealData方法调用完毕");

        // 模拟主函数中其他耗时操作
        doOtherThing();

        // 获取RealData方法的结果
        System.out.println(futureTask.get());
    }


    private static void doOtherThing() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("其它方法执行完毕");
    }

    /**
     * @Description:需要异步执行的任务
     * @Author LinJia
     * @Date 2020/7/14 9:59
     * @Param
     * @return
     **/
    static class RealData {
        public String costTime(){
            try{
                Thread.sleep(3000);
                return "result";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "exception";
        }
    }
}
