package learning.basis.aop_log.impl;

import learning.basis.aop_log.IAspectAPI;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @Description:被装饰者 基本实现
 * 公共处理 可扩展
 * @Author LinJia
 * @Date 2020/5/21 10:56
 * @Param
 * @return
 **/
public class AspectApiImpl implements IAspectAPI {

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return null;
    }
}
