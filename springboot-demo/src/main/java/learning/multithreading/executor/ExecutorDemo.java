package learning.multithreading.executor;

import learning.design_mode.factory.Circle;
import learning.design_mode.factory.Shape;
import learning.design_mode.factory.ShapeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:模拟线程池执行任务
 * @Author LinJia
 * @Date 2020/7/14
 **/
@RestController
public class ExecutorDemo {

    @Autowired
    private ThreadPoolTaskExecutor executor;


    @GetMapping("test")
    public void test(){
        Shape shape = (Shape) ShapeFactory.getInstance2(Circle.class);
        System.out.println("线程池执行任务");
        //执行任务
        executor.execute(()-> shape.draw());
    }
}
