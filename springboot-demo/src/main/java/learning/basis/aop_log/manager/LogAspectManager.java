package learning.basis.aop_log.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import learning.basis.aop_log.IAspectAPI;
import learning.basis.aop_log.RequestInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description: 继承AbstractAspectManager进行扩展
 * 记录日志
 *
 * 在高并发请求下确实会出现请求之间打印日志串行的问题
 *  解决日志串行的问题只要将多行打印信息合并为一行就可以了，因此构造一个对象RequestInfo
 *
 * @Author LinJia
 * @Date 2020/5/21
 **/
public class LogAspectManager extends AbstractAspectManager {

    private Logger logger = LoggerFactory.getLogger(LogAspectManager.class);

    public LogAspectManager(IAspectAPI iAspectAPI) {
        super(iAspectAPI);
    }

    /**
     * @return java.lang.Object
     * @Description:此处需要注意doHandlerAspect方法 在转发请求之后记录访问日志
     * @Author LinJia
     * @Date 2020/5/21 15:23
     * @Param [pjp, method]
     **/
    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp, method);
        return execute(pjp, method);
    }
    
    /**
     * @Description:
     * @Author LinJia
     * @Date 2021/4/1 13:27 
     * @Param [pjp, method]
     * @return java.lang.Object
     **/
    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        //String params = getReqParameter(request);
        Map<String, Object> params = getReqParameter(pjp);
        //高并发下会有日志串行的问题
        //logger.info("请求IP:{}", request.getRemoteAddr());
        //logger.info("请求地址:{}", url);
        //logger.info("请求方法:{}", method);
        //logger.info("请求参数:{}", params);
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        //logger.info("请求返回值:{}", JSON.toJSONString(result));

        //解决高并发下日志串行问题
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setUrl(url);
        requestInfo.setClassMethod(method.toString());
        requestInfo.setRequestParams(params);
        requestInfo.setResult(result);

        logger.info("Request Info : {}", JSON.toJSONString(requestInfo));
        return result;
    }

    /**
     * @return java.lang.String
     * @Description:获取请求参数
     * @RequestParam能够成功获取参数
     * @PathVariable不能获取参数
     * @Author LinJia
     * @Date 2020/5/21 14:45
     * @Param [request]
     **/
    public String getReqParameter(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Enumeration<String> enumeration = request.getParameterNames();
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
    /**
     * @return java.lang.String
     * @Description: 通过 @PathVariable 以及 @RequestParam 注解传递的参数无法打印出参数名，
     * 因此需要手动拼接一下参数名，同时对文件对象进行了特殊处理，只需获取文件名即可
     * @Author LinJia
     * @Date 2020/5/21 14:45
     * @Param [request]
     **/
    private Map<String, Object> getReqParameter(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

}
