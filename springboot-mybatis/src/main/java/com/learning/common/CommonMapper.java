package com.learning.common;

import java.util.List;

/**
 * @Description:公共mapper
 * 基本的CRUD操作 所有mapper均可继承
 * @Author LinJia
 * @Date 2020/5/20 17:00
 * @Param 
 * @return 
 **/
public interface CommonMapper<T> {
	
	int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> findForList(T entity);
    
    List<T> findAppForList(T entity);
}
