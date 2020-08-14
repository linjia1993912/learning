package com.wsl.filter;

import com.alibaba.fastjson.JSONObject;
import com.wsl.util.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:我们使用的是 shiro 默认的权限拦截 Filter，而因为 JWT 的整合，我们需要自定义自己的过滤器 JWTFilter，
 * JWTFilter 继承了 BasicHttpAuthenticationFilter，并部分原方法进行了重写
 * 自定义一个Filter，用来拦截所有的请求判断是否携带Token
 * isAccessAllowed()判断是否携带了有效的JwtToken
 * onAccessDenied()是没有携带JwtToken的时候进行账号密码登录，登录成功允许访问，登录失败拒绝访问
 * @Author LinJia
 * @Date 2020/5/18
 **/
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private static Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        logger.info("JwtFilter-->>>isAccessAllowed-Method:init()");
        //判断请求的请求头是否带上 "Token"
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try{
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Token");
        return authorization != null;
    }

    /**
     * @Description:执行登录操作
     * @Author LinJia
     * @Date 2020/5/19 14:52
     * @Param [request, response]
     * @return boolean
     **/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * @Description:未授权访问返回 无Token
     * @Author LinJia
     * @Date 2020/5/19 11:10
     * @Param [request, response]
     * @return boolean
     **/
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String contentType = "application/json;charset=utf-8";
        httpResponse.setStatus(401);
        httpResponse.setContentType(contentType);
        try {
            JSONObject res = new JSONObject();
            res.put("success","false");
            res.put("msg","无权访问");
            PrintWriter printWriter = httpResponse.getWriter();
            printWriter.append(res.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


}
