package com.wsl.controller;

import com.wsl.util.JWTUtil;
import com.wsl.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:测试拦截
 * @Author LinJia
 * @Date 2020/5/13
 **/
@RestController
public class HomeIndexController {

    /**
     * @return java.lang.String
     * @Description: 注册用户得到加密密码
     * @Author LinJia
     * @Date 2020/5/13 15:42
     * @Param [username, password]
     **/
    @PostMapping(value = "/registered")
    public String registered(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ShiroUtils.MD5Pwd(username, password);
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized() {
        return "无权限";
    }

    /**
     * @return java.lang.String
     * @Description:登录
     * @Author LinJia
     * @Date 2020/5/19 9:30
     * @Param [username, password]
     **/
    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

        //只集成Shiro用改登录方法

        /*Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }*/


        //集成了JWT登录
        //数据库查询出来的加密密码
        String dataBasePassWord = "f53f899bcdb339b6d4e94dcd3711cbb1";
        if (!dataBasePassWord.equals(ShiroUtils.MD5Pwd(username, password))) {
            return "账号密码不正确";
        }
        String token = JWTUtil.sign(username);
        return "登录成功：token=" + token;
    }

    /**
     * @Description:登出
     * @Author LinJia
     * @Date 2020/5/19 14:42
     * @Param []
     * @return java.lang.String
     **/
    @PostMapping(value = "/logout")
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "已安全退出";
    }
}
