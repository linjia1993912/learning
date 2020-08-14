package com.learning.service.impl;

import com.learning.mapper.CommonMapper;

import java.util.List;

/**
 * @Description:封装一个公共mapper实现类
 * 可以实现以下基础的CRUD
 * @Author LinJia
 * @Date 2020/7/31
 **/
public abstract class CommonServiceImpl<T> implements CommonMapper<T> {

    public abstract CommonMapper<T> getMapper();

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(T record) {
        return 0;
    }

    @Override
    public int insertSelective(T record) {
        return 0;
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return 0;
    }

    @Override
    public List<T> findForList(T entity) {
        return null;
    }

    @Override
    public List<T> findAppForList(T entity) {
        return null;
    }
}
