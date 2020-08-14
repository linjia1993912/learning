package learning.design_mode.future;

import java.util.concurrent.*;

/**
 * @Description:通过Future实现
 * 与FutureTask不同的是, RealData需要实现Callable接口
 * @Author LinJia
 * @Date 2020/7/14
 **/
public class FutureDemo2 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new RealData2());
        System.out.println("RealData2方法调用完毕");

        // 模拟主函数中其他耗时操作
        doOtherThing();

        // 获取RealData方法的结果
        System.out.println(future.get());
    }

    private static void doOtherThing() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("其它方法执行完毕");
    }


    static class RealData2 implements Callable<String> {

        public String costTime(){
            try{
                Thread.sleep(3000);
                return "result";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "exception";
        }

        @Override
        public String call() throws Exception {
            return costTime();
        }
    }
}
