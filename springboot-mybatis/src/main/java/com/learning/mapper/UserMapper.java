package com.learning.mapper;

import com.learning.common.CommonMapper;
import com.learning.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:继承公共mapper
 * 可以自定义查询，也可以直接使用CommonMapper里基础查询
 * @Author: linJia
 * @Date: 2020/5/20 17:15
 */
public interface UserMapper extends CommonMapper<UserEntity> {

    @Select("select id,userName,address from user")
    List<UserEntity> getUserAll();
}
