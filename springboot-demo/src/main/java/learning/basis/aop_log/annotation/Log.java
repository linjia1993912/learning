package learning.basis.aop_log.annotation;


import java.lang.annotation.*;

/**
 * @Description:在Controller方法上加入该注解会自动记录日志
 * @Author LinJia
 * @Date 2020/5/21 10:55
 * @Param
 * @return
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

}
