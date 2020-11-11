package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.entity.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description: 基础CRUD，继承了ElasticsearchRepository
 * @Author: linJia
 * @Date: 2020/11/11 9:47
 */
public interface BlogRepository extends ElasticsearchRepository<BlogModel,String> {

}
