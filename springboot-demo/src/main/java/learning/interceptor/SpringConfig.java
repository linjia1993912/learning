package learning.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: WebMvcConfigurer配置类
 * 根据配置注入拦截器
 * WebMvcConfigurerAdapter在SpringBoot2.0已废弃
 * 替代的有2种方式
 * implements WebMvcConfigurer（官方推荐）
 * extends WebMvcConfigurationSupport
 * @Author: linjia
 * @Date: 2018/8/7 11:52
 */
@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizeInterceptor interceptor;

    @Value("${isAuthorToken}")
    private Boolean isAuthorToken;

    /**
     * @return void
     * @Description:添加拦截器 可添加多个
     * @Author LinJia
     * @Date 2020/5/20 15:53
     * @Param [registry]
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (isAuthorToken == true) {
            registry.addInterceptor(interceptor).addPathPatterns();
        }
    }

}
