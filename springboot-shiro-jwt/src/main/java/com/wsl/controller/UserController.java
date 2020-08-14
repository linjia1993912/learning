package com.wsl.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:测试权限
 * @Author LinJia
 * @Date 2020/5/13
 **/
@RestController
public class UserController {

    /**
     * @Description:拥有show权限的可以访问
     * @Author LinJia
     * @Date 2020/5/18 14:09
     * @Param []
     * @return java.lang.String
     **/
    @RequiresPermissions("user:show")
    @GetMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }

    /**
     * @Description:登录的用户可以访问
     * @Author LinJia
     * @Date 2020/5/19 10:54
     * @Param []
     * @return java.lang.String
     **/
    @RequiresAuthentication
    @GetMapping("/requireAuth")
    public String requireAuth() {
        return "登录了看见了";
    }

    /**
     * @Description:所有人可以访问 测试token
     * @Author LinJia
     * @Date 2020/5/18 15:07
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/getMessage")
    public String getMessage() {
        return "获取信息";
    }

}

