package com.wsl.config;

import com.wsl.util.JWTToken;
import com.wsl.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义的Realm继承AuthorizingRealm。
 * 并且重写父类中的doGetAuthorizationInfo（权限相关）
 * doGetAuthenticationInfo（身份认证）这两个方法。
 * @Author LinJia
 * @Date 2020/5/13
 **/
public class CustomRealm extends AuthorizingRealm {

    /**
     * @return boolean
     * @Description: 集成JWT的时候，这里有坑，必须重写此方法，不然Shiro报错
     * @Author LinJia
     * @Date 2020/5/18 14:43
     * @Param [token]
     **/
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @Description: 设置权限认证，即登录过后，每个身份不一定，对应的所能看的页面也不一样
     * @Author LinJia
     * @Date 2020/5/13 13:54
     * @Param [principals]
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置允许访问的权限
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }

    /**
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @Description: 身份认证。即登录通过账号和密码验证登陆人的身份信息。
     * 这里可以注入userService,为了方便，写死了帐号密码
     * @Author LinJia
     * @Date 2020/5/13 13:54
     * @Param [token]
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");

        //只集成Shiro用该方法
        /*String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //加密处理
        //根据用户名从数据库获取密码
        String password = "f53f899bcdb339b6d4e94dcd3711cbb1";
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(userName, password,
                ByteSource.Util.bytes(userName + "salt"), getName());*/


        //集成了JWT
        // 校验token有效性
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtil.getUserName(token);
        //模拟 查询用户信息 验证用户是否存在
        String password = "f53f899bcdb339b6d4e94dcd3711cbb1";
        if (StringUtils.isEmpty(username) || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token非法无效!");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }

}
