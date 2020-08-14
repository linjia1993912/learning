package com.wsl.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description: shiro提供了SimpleHash类帮助我们快速加密
 * 用户注册的时候，程序将明文通过加密方式加密，存到数据库的是密文，登录时将密文取出来，再通过shiro将用户输入的密码进行加密对比，
 * 一样则成功，不一样则失败。
 * 唯一需要注意的是：你注册的加密方式和设置的加密方式还有Realm中身份认证的方式都是要一模一样的。
 * @Author LinJia
 * @Date 2020/5/13
 **/
public class ShiroUtils {
    /**
     * @return java.lang.String
     * @Description:注册的时候可以使用该方法快速加密,得到密文之后再存入数据库
     * @Author LinJia
     * @Date 2020/5/13 15:29
     * @Param [username, pwd]
     **/
    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt 盐值 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }
}
