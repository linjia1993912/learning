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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Description:数据服务
 * @Author LinJia
 * @Date 2020/11/27
 **/
@Service
@Slf4j
public class IDataServiceImpl implements DataService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Autowired
    private PhoneRepository phoneRepository;

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
        for (int i = 1; i < 100000; i++) {
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
        String result = HttpUtils.get(url);
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
            String img = imgElement.attr("src");
            phoneEntity.setImgUrl(img);
            //名称
            Elements nameElement = element.select(".p-name em");
            String name = nameElement.text();
            phoneEntity.setName(name);
            log.info("手机名称:"+name);
            //广告
            Elements adElement = element.select(".p-name i");
            String ad = adElement.text();
            phoneEntity.setAd(ad);

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
                    phoneEntity.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                } catch (Exception e) {
                    log.error(price);
                }
            }

            phoneEntity.setCreateTimeStamp(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                    .setCreateTimeString(sdf.format(localDateTime));
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