package learning.readinglist.uploadfile.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description:初始化文件服务
 * SpringBoot项目启动后执行
 * @Author LinJia
 * @Date 2020/9/3
 **/
@Component
public class Config implements CommandLineRunner {

    @Autowired
    private StorageService storageService;

    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
