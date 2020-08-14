package com.redisson.annotation;

import java.lang.annotation.*;

/**
 * @Description:基于注解的分布式锁
 * 只需在方法上面加入该注解即可
 * 颗粒度大 不推荐
 * @Author: linJia
 * @Date: 2020/6/22 13:59
 */
@Documented //将注解包含在Javadoc中
//在注解上使用@Inherited 表示该注解会被子类继承，注意，仅针对类，成员属性、方法并不受此注释的影响
//对于类来说，子类要继承父类的注解需要该注解被 @Inherited 标识
@Inherited //允许子类继承父类
@Retention(RetentionPolicy.RUNTIME) //表示需要在什么级别保存该注解信息
@Target(ElementType.METHOD) // 解用于什么地方
public @interface DistributedLock {
    /**
     * 锁的名称
     */
    String value() default "redisson";

    /**
     * 锁的有效时间
     */
    int leaseTime() default 10;

}
