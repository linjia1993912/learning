/*
package com.wsl.config;

import com.wsl.util.JWTToken;
import com.wsl.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

*/
/**
 * @Description:
 * @Author LinJia
 * @Date 2020/5/18
 **//*

public class JWTRealm extends AuthorizingRealm {
    */
/**
     * @Description: 集成JWT的时候，这里有坑，必须重写此方法，不然Shiro报错
     * @Author LinJia
     * @Date 2020/5/18 14:43
     * @Param [token]
     * @return boolean
     **//*

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String jwtUserName = JWTUtil.getUserName(token);
        if (jwtUserName == null || !JWTUtil.verify(token, jwtUserName,password)) {
            throw new AuthenticationException("token认证失败！");
        }
        return null;
    }
}
*/
