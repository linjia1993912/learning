package com.learning.mapper;

import com.learning.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:可以自定义查询，继承公共mapper
 * @Author: linJia
 * @Date: 2020/5/20 17:15
 */
public interface UserMapper extends CommonMapper<UserEntity>{

    @Select("select id,userName,address from user")
    List<UserEntity> getUserAll();
}
