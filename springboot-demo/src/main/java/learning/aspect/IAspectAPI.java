package learning.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @Description:切面过程 装饰器接口
 * @Author: linJia
 * @Date: 2020/5/21 10:40
 */
public interface IAspectAPI {

    Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable;
}
