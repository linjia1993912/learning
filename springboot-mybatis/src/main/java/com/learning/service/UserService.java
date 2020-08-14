package com.learning.service;

import com.learning.entity.UserEntity;
import com.learning.mapper.CommonMapper;
import com.learning.mapper.UserMapper;
import com.learning.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:继承公共的service,实现自定义的查询
 * @Author LinJia
 * @Date 2020/7/31
 **/
@Service
public class UserService extends CommonServiceImpl<UserEntity> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommonMapper<UserEntity> getMapper() {
        return userMapper;
    }

    public List<UserEntity> getUserAll() {
        return userMapper.getUserAll();
    }
}
