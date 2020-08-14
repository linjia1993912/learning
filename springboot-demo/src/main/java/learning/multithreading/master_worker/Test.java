package learning.multithreading.master_worker;

import java.util.Random;

/**
 * @Description:主函数测试
 * @Author LinJia
 * @Date 2020/7/14
 **/
public class Test {

    public static void main(String[] args) {
        //Master持有10个子进程
        MasterDemo master = new MasterDemo(new WorkerDemo(), 10);
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setId(i);
            task.setName("任务" + i);
            task.setPrice(new Random().nextInt(10000));
            master.submit(task);
        }

        //启动所有任务
        master.execute();

        while (true) {
            if (master.isComplete()) {
                System.out.println("执行的结果为: " + master.getResult());
                break;
            }
        }

    }
}
