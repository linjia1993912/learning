package learning.multithreading.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LinJia
 * @description: 测试异步任务
 * @date 2021/4/1
 */
@RestController
public class TestAsyncController {

    @Autowired
    private TestCRMService testCRMService;

    @PostMapping("/testAsync")
    public void testAsync(){
        testCRMService.sendMessage();

        testCRMService.sendMail();

        System.out.println("主线程执行完毕");
    }
}