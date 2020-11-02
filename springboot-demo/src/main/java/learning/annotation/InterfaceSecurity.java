package learning.annotation;

import java.lang.annotation.*;

/**
 * @Description:幂等性验证，注解方式
 * 只需在controller加上该注解
 * 防止数据重复提交
 * @Author LinJia
 * @Date 2020/11/2
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterfaceSecurity {
}
