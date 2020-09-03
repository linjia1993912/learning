package com.learning.mapper;

import com.learning.common.CommonMapper;
import com.learning.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:继承公共mapper
 * 可以自定义查询
 * @Author LinJia
 * @Date 2020/9/3 10:23
 * @Param
 * @return
 **/
public interface UserEntityMapper extends CommonMapper<UserEntity>{
    //@Select("select id,userName,address from user")
    List<UserEntity> getUserAll();

}