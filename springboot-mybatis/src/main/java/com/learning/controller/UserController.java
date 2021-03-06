package com.learning.controller;

import com.learning.service.IUserService;
import com.learning.service.impl.UserService;
import com.learning.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/5/20
 **/
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUserAll")
    public ServiceResult getUserAll() {
        return ServiceResult.success("",userService.getUserAll());
    }
}
