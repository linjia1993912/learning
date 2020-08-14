package com.wsl.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @Description:JWT工具类
 * 验证常用操作
 * @Author LinJia
 * @Date 2020/5/18 14:22
 * @Param
 * @return
 **/
public class JWTUtil {

    // 过期时间30分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    //密钥，注意这里如果真实用到，应当设置到复杂点，相当于私钥的存在。如果被人拿到，想到于它可以自己制造token了
    private static final String SECRET = "LINJIA";

    /**
     * 校验token是否正确
     *
     * @param token    密钥
     * @param userName 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userName) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userName", userName)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需passWord解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     *
     * @param userName 用户名
     * @return 加密的token
     */
    public static String sign(String userName) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("userName", userName)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}
