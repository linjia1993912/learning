package com.wsl.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description: shiro提供了SimpleHash类帮助我们快速加密
 * 用户注册的时候，程序将明文通过加密方式加密，存到数据库的是密文，登录时将密文取出来，再通过shiro将用户输入的密码进行加密对比，
 * 一样则成功，不一样则失败。
 * 唯一需要注意的是：你注册的加密方式和设置的加密方式还有Realm中身份认证的方式都是要一模一样的。
 *
 * 散列算法 生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，
 *
 * @Author LinJia
 * @Date 2020/5/13
 **/
public class ShiroUtils {
    //干扰数据 盐值 防破解
    private static final String SALT = "mar%#$@";
    //散列算法类型为MD5
    private static final String ALGORITH_NAME = "MD5";
    //hash的次数
    private static final int HASH_ITERATIONS = 2;

    /**
     * @return java.lang.String
     * @Description:注册的时候可以使用该方法快速加密,得到密文之后再存入数据库
     * @Author LinJia
     * @Date 2020/5/13 15:29
     * @Param [username, pwd]
     **/
    public static String MD5Pwd(String username, String pwd) {
        // salt 盐值,需要加紧一起加密的数据 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash(ALGORITH_NAME, pwd,
                ByteSource.Util.bytes(username + SALT), HASH_ITERATIONS).toHex();
        return md5Pwd;
    }

    //测试加密
    public static void main(String[] args) {
        //org.apache.shiro
        System.out.println(MD5Pwd("linjia","123456"));
        System.out.println(MD5Pwd("linjia","123456"));
        System.out.println(MD5Pwd("linjia","1234567"));

        //另外一种生成sig签名
        //org.apache.commons
        System.out.println(DigestUtils.md5Hex("linjia"+"123456"+"salt"));
        System.out.println(DigestUtils.md5Hex("linjia"+"123456"+"salt"));
    }
}
