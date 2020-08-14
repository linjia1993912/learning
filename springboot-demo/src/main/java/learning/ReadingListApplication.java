package learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:ReadingListApplication为应用程序的启动引导类(bootstrap class)，也是主要的Spring配置类
 * 在SpringBoot应用程序有两个作用，配置和启动引导
 * SpringBootApplication是组合注解，将三个注解组合在一起
 * Spring的@Configuration:标明该类使用Spring基于Java的配置，简化XML
 * Spring的@ComponentScan：启用组件扫描
 * SpringBoot的@EnableAutoConfiguration：开启SpringBoot自动配置
 *
 * 早期版本中需要将以上三个注解同时添加，SpringBoot1.2.0之后只需@SpringBootApplication
 * @Author LinJia
 * @Date 2020/6/1 14:13
 * @Param
 * @return
 **/
@SpringBootApplication  //开启组件扫描和自动配置
public class ReadingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args);//负责启动引导应用程序
	}

}
