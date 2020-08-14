package learning.multithreading.master_worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description:负责处理任务
 * @Author LinJia
 * @Date 2020/7/14
 **/
public class WorkerDemo implements Runnable {

    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    @Override
    public void run() {
        while (true) {
            Task input = this.workQueue.poll();
            // 所有任务已经执行完毕
            if (input == null) {
                break;
            }
            // 模拟对task进行处理, 返回结果
            int result = input.getPrice();
            this.resultMap.put(input.getId() + "", result);
            System.out.println("任务执行完毕, 当前线程: " + Thread.currentThread().getName());
        }
    }

    public ConcurrentLinkedQueue<Task> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public ConcurrentHashMap<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
