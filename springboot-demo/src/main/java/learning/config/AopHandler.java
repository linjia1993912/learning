package learning.config;

import learning.annotation.InterfaceSecurity;
import learning.annotation.Log;
import learning.aspect.AspectApiImpl;
import learning.aspect.InterfaceSecurityAspect;
import learning.aspect.LogAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @Description:Spring AOP切面
 * 记录接口请求参数日志等等操作
 * @Author LinJia
 * @Date 2020/5/21
 **/
@Aspect
@Configuration
public class AopHandler {

    private Logger logger = LoggerFactory.getLogger(AopHandler.class);


    // 定义切点Pointcut
    @Pointcut("execution(* learning..*.*(..))")
    public void excudeService() {
    }

    /**
     * @Description:环绕通知，相当于MethodInterceptor
     * @Author LinJia
     * @Date 2020/5/21 10:49
     * @Param [pjp]
     * @return java.lang.Object
     **/
    @Around(value = "excudeService()")
    public Object validationPoint(ProceedingJoinPoint pjp)throws Throwable{
        Method method = currentMethod(pjp,pjp.getSignature().getName());
        //创建被装饰者
        AspectApiImpl aspectApi = new AspectApiImpl();
        //此处可以判断多个指定注解操作

        //是否需要记录日志
        if(method.isAnnotationPresent(Log.class)){
            return new LogAspect(aspectApi).doHandlerAspect(pjp,method);
        }
        //幂等性验证，防止数据重复提交
        if(method.isAnnotationPresent(InterfaceSecurity.class)){
             return new InterfaceSecurityAspect(aspectApi).doHandlerAspect(pjp,method);
        }

        return  pjp.proceed(pjp.getArgs());
    }

    /**
     * 获取目标类的所有方法，找到当前要执行的方法
     */
    private Method currentMethod ( ProceedingJoinPoint joinPoint , String methodName ) {
        Method[] methods      = joinPoint.getTarget().getClass().getMethods();
        Method   resultMethod = null;
        for ( Method method : methods ) {
            if ( method.getName().equals( methodName ) ) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }
}
