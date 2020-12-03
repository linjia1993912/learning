package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.service.DataService;
import com.example.springbootelasticsearch.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @GetMapping("/grabData")
    public ServiceResult grabData() {
        //new Thread(() -> dataService.jd()).start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dataService.jd();
            }
        });
        thread.start();

        return ServiceResult.success("开始数据抓取");
    }

    /**
     * @Description:删除所有数据
     * @Author LinJia
     * @Date 2020/12/3 2:41 下午
     * @Param []
     * @return com.example.springbootelasticsearch.util.ServiceResult
     **/
    @DeleteMapping("/deleteAllPhoneData")
    public ServiceResult deleteAllPhoneData() {
        dataService.deleteAllPhoneData();
        return ServiceResult.success("删除成功");
    }
}