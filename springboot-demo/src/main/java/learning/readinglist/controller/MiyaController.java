package learning.readinglist.controller;

import learning.readinglist.entity.ConfigBean;
import learning.readinglist.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:读取yml配置文件的值
 * @Author LinJia
 * @Date 2020/9/2
 **/
@RestController
//@EnableConfigurationProperties({ConfigBean.class}) //使ConfigurationProperties生效
//如果ConfigBean加了@Component 则controller不需要加@EnableConfigurationProperties
public class MiyaController {

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private int age;

    @Autowired
    ConfigBean configBean;

    @RequestMapping(value = "/miya")
    public String miya(){
        return name+":"+age;
    }

    /**
     * @Description:将配置文件的属性赋给实体类
     * @Author LinJia
     * @Date 2020/9/2 14:45
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/lucy")
    public String lucy(){
        return configBean.getGreeting()+" >>>>"+configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();
    }

    @Autowired
    private User user;

    @RequestMapping(value = "/user")
    public String user(){
        return user.getName()+user.getAge();
    }
}
