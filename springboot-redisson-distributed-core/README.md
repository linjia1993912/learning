
##项目概述
redis实现分布式锁
springboot-redisson-distributed-core可以当作为一个组件，pom引入会自动装配

##技术架构
SpringBoot2.1.5 + Maven3.5.4 + Redisson3.5.4 + lombok(插件)

##加锁方式

该项目支持 `自定义注解加锁` 和 ` 常规加锁` 两种模式

**自定义注解加锁**

```java
 @DistributedLock(value="goods", leaseTime=5)
  public String lockDecreaseStock(){
    //业务逻辑
  }
```

**常规加锁**

```java
 //1、加锁
 redissonLock.lock("redisson", 10);
 //2、业务逻辑
 //3、解锁
 redissonLock.unlock("redisson");
 ```
##redis部署方式
该项目支持四种Redis部署方式
```
1、单机模式部署
2、集群模式部署
3、主从模式部署
4、哨兵模式部署
```
该项目已经实现支持上面四种模式，你要采用哪种只需要修改配置文件`application.properties`，项目代码不需要做任何修改。
 
##项目整体结构
```
springboot-redisson-distributed-core # 核心实现

---src
      |
      ---com.redisson
                           |# 自定义注解
                           ---annotation
                           |# redis配置
                           ---config
                           |# 放置常量信息
                           ---constant
                           |# redis配置实体
                           ---entity
                           |# redis常用操作
                           ---util
                           |# 四种模式实现
                           ---strategy


springboot-redisson-distributed-web-test # 针对上面实现类的测试类
      ---resources      
            | # 配置端口号 连接redis信息(如果确定部署类型，那么将连接信息放到core项目中)
            ---application.properties
```