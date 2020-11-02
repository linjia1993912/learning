package learning.aspect;

import org.apache.commons.collections4.map.LRUMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Description:幂等性验证 切面
 * 验证数据重复提交
 * @Author LinJia
 * @Date 2020/11/2
 **/
public class InterfaceSecurityAspect extends AbstractAspectManager {

    private Logger logger = LoggerFactory.getLogger(InterfaceSecurityAspect.class);

    public InterfaceSecurityAspect(IAspectAPI iAspectAPI){
        super(iAspectAPI);
    }

    /**
     * @Description:此处需要注意doHandlerAspect方法
     * 跟日志记录不同，这里先验证，验证通过转发请求
     * @Author LinJia
     * @Date 2020/11/2 9:34
     * @Param [pjp, method]
     * @return java.lang.Object
     **/
    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        Object object = execute(pjp, method);
        if(object.equals(true)){
            //验证通过转发原请求
            super.doHandlerAspect(pjp, method);
            // result的值就是被拦截方法的返回值
            Object result = pjp.proceed();
            return result;
        }
         return object;
    }


    // 根据 LRU(Least Recently Used，最近最少使用)算法淘汰数据的 Map 集合，最大容量 100 个
    private static LRUMap<String, Integer> reqCache = new LRUMap<>(100);

    /**
     * @Description:验证数据重复提交
     * 验证逻辑
     * @Author LinJia
     * @Date 2020/11/2 9:37
     * @Param [pjp, method]
     * @return java.lang.Object
     **/
    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String id = request.getParameter("id");
        synchronized (this.getClass()) {
            // 重复请求判断
            if (reqCache.containsKey(id)) {
                // 重复请求
               logger.info("请勿重复提交！！！" + id);
               return "请勿重复提交！！！" + id;
            }
            // 非重复请求，存储请求 ID
            reqCache.put(id, 1);
        }
        logger.info("添加成功！！！" + id);
        return true;
    }
}
