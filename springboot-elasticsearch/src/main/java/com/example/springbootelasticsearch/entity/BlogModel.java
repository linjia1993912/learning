package com.example.springbootelasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:数据存储实体
 * @Author LinJia
 * @Date 2020/11/11
 **/
@Data
@Accessors(chain = true)
@Document(indexName = "blog", type = "java")  //blog索引名称  type类型
public class BlogModel implements Serializable {
    private static final long serialVersionUID = 6320548148250372657L;

    @Id
    private String id;

    private String title;

    //@Field(type = FieldType.Date, format = DateFormat.basic_date)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;

}
