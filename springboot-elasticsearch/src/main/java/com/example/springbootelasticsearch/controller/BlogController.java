package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.entity.BlogModel;
import com.example.springbootelasticsearch.service.BlogRepository;
import com.example.springbootelasticsearch.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description: ElasticsearchRepository操作，基础CRUD
 * @Author LinJia
 * @Date 2020/11/11
 **/
@RestController
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * @Description:添加一条数据，标题是：Elasticsearch实战篇：Spring Boot整合ElasticSearch，时间是：2019-03-06。
     * @Author LinJia
     * @Date 2020/11/11 11:08
     * @Param [blogModel]
     * @return com.example.springbootelasticsearch.util.ServiceResult
     **/
    @PostMapping("/add")
    public ServiceResult add(@RequestBody BlogModel blogModel) {
        blogRepository.save(blogModel);
        return ServiceResult.success("添加成功");
    }

    /**
     * @Description:根据ID查询
     *
     * 客户端查询blog索引下的所有类型 http://localhost:9200/blog/_search
     *
     * @Author LinJia
     * @Date 2020/11/11 13:53
     * @Param [id]
     * @return com.example.springbootelasticsearch.util.ServiceResult
     **/
    @GetMapping("/get/{id}")
    public ServiceResult getById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)){
            return ServiceResult.failure("params id not is null");
        }
        Optional<BlogModel> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            BlogModel blogModel = blogModelOptional.get();
            return ServiceResult.success("",blogModel);
        }
        return ServiceResult.success("无数据");
    }

    /**
     * @Description:查询所有
     *
     * 客户端查询blog索引下的所有类型 http://localhost:9200/blog/_search
     *
     * @Author LinJia
     * @Date 2020/11/11 14:02
     * @Param []
     * @return Result
     **/
    @GetMapping("/get")
    public ServiceResult getAll() {
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return ServiceResult.success("",list);
    }

    /**
     * @Description:根据id修改
     * @Author LinJia
     * @Date 2020/11/11 14:09
     * @Param [blogModel]
     * @return com.example.springbootelasticsearch.util.ServiceResult
     **/
    @PostMapping("/update")
    public ServiceResult updateById(@RequestBody BlogModel blogModel) {
        String id = blogModel.getId();
        if (StringUtils.isEmpty(id)){
            return ServiceResult.failure("params id not is null");
        }
        blogRepository.save(blogModel);
        return ServiceResult.success("修改成功");
    }

    /**
     * @Description:根据ID删除
     * @Author LinJia
     * @Date 2020/11/11 14:17
     * @Param [id]
     * @return com.example.springbootelasticsearch.util.ServiceResult
     **/
    @DeleteMapping("/delete/{id}")
    public ServiceResult deleteById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResult.failure("params id not is null");
        }
        blogRepository.deleteById(id);
        return ServiceResult.success("删除成功");
    }

    /**
     * @Description:删除所有
     * @Author LinJia
     * @Date 2020/11/11 14:18
     * @Param []
     * @return com.example.springbootelasticsearch.util.ServiceResult
     **/
    @DeleteMapping("/deleteAll")
    public ServiceResult deleteAll() {
        blogRepository.deleteAll();
        return ServiceResult.success("删除成功");
    }

}
