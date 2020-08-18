package com.example.dao;

/**
 * @Description: 依赖注入的方式
 * 构造方法、setter、注解
 *
 * @Author LinJia
 * @Date 2020/8/18
 **/
public class UserDaoImpl {

    private UserDao userDao;

    //基于XML通过构造方法依赖注入
    /*public UserDaoImpl(UserDao userDao){
        this.userDao = userDao;
        System.out.println("UserDao注入成功 "+userDao);
    }*/

    //通过setter方法注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("UserDao注入成功 "+userDao);
    }
}
