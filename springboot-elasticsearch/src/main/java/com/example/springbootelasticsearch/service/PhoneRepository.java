package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.entity.PhoneEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/11/27
 **/
public interface PhoneRepository extends ElasticsearchRepository<PhoneEntity,String> {
}
