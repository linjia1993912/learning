package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.service.DataService;
import com.example.springbootelasticsearch.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:数据服务
 * @Author LinJia
 * @Date 2020/12/1
 **/
@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    /**
     * @Description:抓取手机数据
     * @Author LinJia
     * @Date 2020/12/1 3:20 下午
     * @Param []
     * @return com.example.springbootelasticsearch.util.ServiceResult
     **/
    @GetMapping("/grab")
    public ServiceResult grab() {
        new Thread(() -> dataService.jd()).start();
        return ServiceResult.success("数据抓取完成");
    }
}