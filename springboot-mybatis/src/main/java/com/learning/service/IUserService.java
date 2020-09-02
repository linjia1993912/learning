package com.learning.service;

import com.learning.entity.UserEntity;

import java.util.List;

/**
 * @Description:自定义接口
 * @Author LinJia
 * @Date 2020/9/2
 **/
public interface IUserService {
    List<UserEntity> getUserAll();
}
