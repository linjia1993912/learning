package com.wsl.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description:封装token来替换Shiro原生Token，要实现AuthenticationToken接口
 * @Author LinJia
 * @Date 2020/5/18 15:20
 * @Param
 * @return
 **/
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
