package learning.interface_security.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import learning.vo.ServiceResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:拦截器 来验证token
 * @Author LinJia
 * @Date 2020/5/20
 **/

@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);

    /**
     * 验证token url
     */
    @Value("${author.token.url}")
    private String authorTokenUrl;

    /**
     * @return boolean
     * @Description:在请求之前调用 可以进行编码、安全控制、权限校验等处理
     * @Author LinJia
     * @Date 2020/5/20 16:00
     * @Param [request, response, handler]
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求？号后面的所有参数
//        String params = request.getQueryString();
        //获得请求头header里面的参数 token一般存在头
        String token = request.getHeader("Token");
        if (token == null) {
            logger.error("params token is null");
            this.setReturnErrorMessage("params token is null",response);
            return false;
        }
        logger.info("get token is {}", token);
        try {
            //验证token 省略

            Boolean flag = false;
            if (flag == false) {
                logger.error("token is expire");
                this.setReturnErrorMessage("token is expire",response);
                return false;
            }
        } catch (Exception e) {
            this.setReturnErrorMessage("validation token error",response);
            logger.error(e.getMessage());
        }
        return true;
    }


    public void setReturnErrorMessage(String failure, HttpServletResponse response) {
        try {
            ServiceResult tokenIsExpire = ServiceResult.failure(failure);
            String jsonStr = new ObjectMapper().writeValueAsString(tokenIsExpire);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().print(jsonStr);
            response.getWriter().flush();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Description:在业务处理器处理请求执行完成后，生成视图之前执行
     * @Author LinJia
     * @Date 2020/5/20 16:01
     * @Param [request, response, handler, modelAndView]
     **/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     * @return void
     * @Description:在DispatcherServlet完全处理完请求后被调用，可用于清理资源等
     * @Author LinJia
     * @Date 2020/5/20 16:01
     * @Param [request, response, handler, ex]
     **/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
