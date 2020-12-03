package com.example.springbootelasticsearch.service.impl;

import com.example.springbootelasticsearch.entity.PhoneEntity;
import com.example.springbootelasticsearch.service.DataService;
import com.example.springbootelasticsearch.service.PhoneRepository;
import com.example.springbootelasticsearch.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Description:数据服务
 * @Author LinJia
 * @Date 2020/11/27
 **/
@Service
@Slf4j
public class IDataServiceImpl implements DataService {

    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * @Description:删除所有文档
     * @Author LinJia
     * @Date 2020/12/3 2:40 下午
     * @Param []
     * @return void
     **/
    @Override
    public void deleteAllPhoneData(){
        phoneRepository.deleteAll();
    }

    /**
     * @Description:抓取京东手机数据
     * i=页数
     * @Author LinJia
     * @Date 2020/11/27 4:48 下午
     * @Param []
     * @return void
     **/
    @Override
    public void jd() {
        //100页
        for (int i = 1; i < 100; i++) {
            String url = "https://list.jd.com/list.html?cat=9987%2C653%2C655&page=" + i + "&s=1&click=0";
            grabJdPhone(url);
        }
    }

    @Override
    public void export() {

    }

    /**
     * @Description:抓取京东手机数据
     * @Author LinJia
     * @Date 2020/11/27 4:55 下午
     * @Param [url]
     * @return void
     **/
    private void grabJdPhone(String url){
        String result = null;
        try {
            result = HttpUtils.getData(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("【抓取数据】抓取JD手机信息：{}", result);
        //解析DOM
        Document document = Jsoup.parse(result);
        Elements elementsByClass = document.getElementsByClass("gl-item");
        for (Element element : elementsByClass) {
            PhoneEntity phoneEntity = new PhoneEntity();
            LocalDateTime localDateTime = LocalDateTime.now();

            log.info("--------------------------------------------------------------");
            //手机图片
            Elements imgElement = element.select(".p-img img");
            String img = imgElement.attr("data-lazy-img");
            phoneEntity.setImgUrl(img);
            log.info("图片:"+img);
            //名称
            Elements nameElement = element.select(".p-name em");
            String name = nameElement.text();
            phoneEntity.setName(name);
            log.info("手机名称:"+name);
            //广告
            Elements adElement = element.select(".p-name i");
            String ad = adElement.text();
            phoneEntity.setAd(ad);
            log.info("广告语:"+ad);

            //手机配置
            Elements attrElements = element.select(".p-name .attr b");
            for (Element attrElement : attrElements) {
                String attrElementValue = attrElement.text();
                if (attrElementValue.contains("运存")) {
                    log.info("内存：" + attrElementValue);
                    phoneEntity.setMemory(attrElementValue);
                }
                //容量
                if (attrElementValue.contains("GB") && !attrElementValue.contains("运存")){
                    phoneEntity.setStorage(attrElementValue);
                    log.info("存储：" + attrElementValue);
                }
                if (attrElementValue.contains("英寸")) {
                    log.info("屏幕：" + attrElementValue);
                    phoneEntity.setScreen(attrElementValue);
                }
            }
            //获取售价
            Elements priceElement = element.select(".p-price i");
            String price = priceElement.text();
            if (StringUtils.isNotEmpty(price)) {
                try {
                    log.info("价格:"+price);
                    phoneEntity.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //localDateTime转Long
            phoneEntity.setCreateTimeStamp(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            //localDateTime转String
            phoneEntity.setCreateTimeString(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            //es
            this.esSave(phoneEntity);
        }
    }

    /**
     * @Description:es插入数据
     * @Author LinJia
     * @Date 2020/12/1 3:23 下午
     * @Param [phoneEntity]
     * @return void
     **/
    private void esSave(PhoneEntity phoneEntity){
        phoneRepository.save(phoneEntity);
    }
}