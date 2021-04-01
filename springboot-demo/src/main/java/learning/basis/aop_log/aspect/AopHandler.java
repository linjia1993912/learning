package learning.basis.aop_log.aspect;

import learning.basis.aop_log.IAspectAPI;
import learning.basis.aop_log.impl.AspectApiImpl;
import learning.basis.aop_log.manager.InterfaceAspectManager;
import learning.basis.aop_log.manager.LogAspectManager;
import learning.basis.aop_log.annotation.Log;
import learning.basis.aop_log.annotation.InterfaceSecurity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @Description:Spring @Aspect => 声明该类为一个注解类
 * 记录接口请求参数日志等等操作
 *
 * @Before => 在切点之前执行代码
 * @After => 在切点之后执行代码
 * @AfterReturning => 切点返回内容后执行代码，可以对切点的返回值进行封装
 * @AfterThrowing => 切点抛出异常后执行
 * @Around => 环绕，在切点前后执行代码
 *
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
        IAspectAPI aspectApi = new AspectApiImpl();
        //此处可以判断多个指定注解操作

        //是否需要记录日志
        if(method.isAnnotationPresent(Log.class)){
            return new LogAspectManager(aspectApi).doHandlerAspect(pjp,method);
        }
        //幂等性验证，防止数据重复提交
        if(method.isAnnotationPresent(InterfaceSecurity.class)){
             return new InterfaceAspectManager(aspectApi).doHandlerAspect(pjp,method);
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
