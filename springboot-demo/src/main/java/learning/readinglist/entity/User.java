package learning.readinglist.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description:将自定义配置文件赋值给实体类
 * 在最新版本的springboot，需要加这三个注解。
 * @Configuration
 * @PropertySource(value = “classpath:test.properties”)
 * @ConfigurationProperties(prefix = “com.forezp”);
 * 在1.4版本需要 PropertySource加上location。
 * @Author LinJia
 * @Date 2020/9/2
 **/
@Configuration
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "com.forezp")
public class User {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
