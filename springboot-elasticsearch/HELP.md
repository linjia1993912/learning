Spring Boot 2.x 集成 Elasticsearch

###版本
Elasticsearch-6.8.5

###步骤一
1.启动Elasticsearch

2.测试本地访问Elasticsearch
```
http://localhost:9200/
```

###步骤二:集成 Elasticsearch
1.pom.xml 引入依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```

2.application.properties 配置
```
spring.data.elasticsearch.cluster-nodes=localhost:9300
spring.data.elasticsearch.cluster-name=docker-cluster
```

###参考文章
https://segmentfault.com/a/1190000018625101

### 注意点
1. 添加记录时，如果 blogModel 实体参数中没有 id 字段，即数据没有自然的 ID， Elasticsearch 可以帮我们
自动生成 ID；此时，blogModel 中的 id 为 null，_id 为自动生成的 ID，查询、更新、删除时，可以使用此自动生成的 ID。
参考：https://www.elastic.co/guide/cn/elasticsearch/guide/cn/index-doc.html#index-doc
