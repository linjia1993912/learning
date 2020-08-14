package learning.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description: 记录日志
 * @Author LinJia
 * @Date 2020/5/21
 **/
public class LogAspect extends AbstractAspectManager {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    public LogAspect(IAspectAPI iAspectAPI) {
        super(iAspectAPI);
    }

    /**
     * @Description:此处需要注意doHandlerAspect方法
     * 在转发请求之后记录访问日志
     * @Author LinJia
     * @Date 2020/5/21 15:23
     * @Param [pjp, method]
     * @return java.lang.Object
     **/
    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp, method);
        return execute(pjp, method);
    }

    @Override
    @Async
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String params = getReqParameter(request);
        logger.info("请求地址:"+url);
        logger.info("请求方法:"+method);
        logger.info("请求参数:"+params);
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        logger.info("请求返回值:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @Description:获取请求参数
     * @Author LinJia
     * @Date 2020/5/21 14:45
     * @Param [request]
     * @return java.lang.String
     **/
    public String getReqParameter(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Enumeration<String> enumeration = request.getParameterNames();
        //StringBuilder stringBuilder = new StringBuilder();
        JSONArray jsonArray = new JSONArray();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            JSONObject json = new JSONObject();
            json.put(key, value);
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }

}
