package learning.readinglist.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: restTemplate去消费一个服务
 * @Author LinJia
 * @Date 2020/9/3
 **/
@Component
public class RestTemplateTest implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    //测试消费服务
    @Override
    public void run(String... args) throws Exception {
        /*String quote = restTemplate.getForObject(
                "http://gturnquist-quoters.cfapps.io/api/random", String.class);
        System.out.println("RestTemplate测试消费服务----"+quote);*/
    }
}
