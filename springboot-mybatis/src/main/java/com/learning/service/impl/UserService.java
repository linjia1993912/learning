package com.learning.service.impl;

import com.learning.entity.UserEntity;
import com.learning.mapper.UserEntityMapper;
import com.learning.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:service层
 * @Author LinJia
 * @Date 2020/7/31
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private UserEntityMapper userMapper;

    @Override
    public List<UserEntity> getUserAll() {
        return userMapper.getUserAll();
    }
}
