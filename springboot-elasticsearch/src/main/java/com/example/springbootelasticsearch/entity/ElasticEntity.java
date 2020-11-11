package com.example.springbootelasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Description:数据存储对象
 * @Author LinJia
 * @Date 2020/11/11 9:18
 * @Param
 * @return
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity<T> {

    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private Map data;
}
