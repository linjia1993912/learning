package learning.multithreading.executor;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description:ForkJoin线程池 该线程池是jdk7之后引入的一个并行执行任务的框架, 其核心思想也是将任务分割为子任务
 * 在子任务中通过THRESHOLD设置子任务分解的阈值, 如果当前需要求和的总数大于THRESHOLD, 则子任务需要再次分解,
 * 如果子任务可以直接执行, 则进行求和操作, 返回结果. 最终等待所有的子任务执行完毕, 对所有结果求和.
 * @Author LinJia
 * @Date 2020/7/14
 **/
public class CountTask extends RecursiveTask<Long> {

    // 任务分解的阈值
    private static final int THRESHOLD = 10000;

    private long start;

    private long end;

    public CountTask(long start,long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        }else{
            // 分成100个小任务
            long step = (start + end) / 100;
            ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                CountTask subTask = new CountTask(pos, lastOne);
                pos += step + 1;
                // 将子任务推向线程池
                subTasks.add(subTask);
                subTask.fork();
            }

            for (CountTask task : subTasks) {
                // 对结果进行join
                sum += task.join();
            }
        }
        return sum;
    }

    //测试主函数
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 累加求和 0 -> 200000
        CountTask task = new CountTask(0, 200000);
        ForkJoinTask<Long> result = pool.submit(task);
        System.out.println("sum result : " + result.get());
    }
}
