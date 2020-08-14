package com.wsl.exceptions;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:添加全局异常处理 对Shiro抛出的异常统一处理
 * shiro无权限，不跳转到指定页面。setUnauthorizedUrl无效
 * @Author LinJia
 * @Date 2020/5/19
 **/
@ControllerAdvice
public class NoPermissionException {

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "无权限";
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "权限认证失败";
    }

}
