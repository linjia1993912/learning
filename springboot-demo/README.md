
##项目概述
学习Springboot相关
包括验证token、注解方式记录访问日志、根据SpringBoot实战书籍编码

**自定义注解记录日志**

```java
     @Log
     @PostMapping("/authToken")
     public ServiceResult authToken() {
         
     }
```

##项目整体结构
```
springboot-demo 

---src
      |
      ---learning
                           |# 自定义注解
                           ---annotation
                           |# 配置类
                           ---config
                           |# AOP解析注解
                           ---aspect
                           |# 测试controller
                           ---controller
                           |# 学习《SpringBoot》实战代码
                           ---readinglist
                           |# 学习反射代码
                           ---reflection
                           |# 学习设计模式代码
                           ---design_mode
                                |# 工程模式
                                ---factory
                                |# 单例模式
                                ---singleton
                                |# 观察者模式
                                 ---observer
                                |# 适配器模式
                                ---adapter_pattern
                                |# 并发，异步执行任务
                                ---future
                           |# 基础随写代码
                           ---basis
                                |# 代理模式示例代码
                                ---proxy
                                |# 泛型示例代码
                                ---generics
                                |# 数据结构集合类
                                ---data_structure
                                |# 链表示例代码
                                ---linkedlist
                                |# java基础-继承
                                ---extends_demo
                                |# 匿名内部类、内部类示例代码
                                ---anonymous_classes
                                |# 序列化示例代码
                                ---serializable_demo
                                |# 枚举使用
                                ---enum_demo
                                |# IO相关操作
                                ---io_demo
                                |# 常用排序算法
                                ---sort_algorithm
                           |# 并发、多线程示例代码
                           ---multithreading
                                  |# 线程池
                                  ---executor
                                  |# 分而治之，并行任务
                                  ---master_worker
                                  |# threadlocal用法
                                  ---threadlocal
                                  |# CAS(乐观锁)知识
                                  ---cas
                           |# JVM相关示例代码
                           ---jvm
                           |# 接口安全性
                           ---interface_security
                                   |# 验证token拦截器
                                   ---interceptor
                         
```