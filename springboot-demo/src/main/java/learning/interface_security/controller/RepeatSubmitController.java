package learning.interface_security.controller;

import learning.annotation.InterfaceSecurity;
import org.apache.commons.collections4.map.LRUMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Description:幂等性验证
 * 防止重复提交数据
 *
 * 参考文章：https://mp.weixin.qq.com/s/buaGzCV2kOg2pZrG1wWfCg
 *
 * @Author LinJia
 * @Date 2020/10/21
 **/
@RestController
public class RepeatSubmitController {

    //扩展版——双重检测锁(DCL)
    //此版本解决了 HashMap 无限增长的问题，它使用数组加下标计数器（reqCacheCounter）的方式，实现了固定数组的循环存储。
    //当数组存储到最后一位时，将数组的存储下标设置 0，再从头开始存储数据
    private static String[] reqCache = new String[100]; // 请求 ID 存储集合
    private static Integer reqCacheCounter = 0; // 请求计数器（指示 ID 存储的位置）

    /**
     * @Description:提交数据接口
     * 扩展版——双重检测锁(DCL)
     *
     * 注意：DCL 适用于重复提交频繁比较高的业务场景，对于相反的业务场景下 DCL 并不适用
     *
     * @Author LinJia
     * @Date 2020/10/21 10:14
     * @Param [id]
     * @return java.lang.String
     **/
    @RequestMapping("/add1")
    public String addUser(String id) {
        // 非空判断(忽略)...
        // 重复请求判断
        if (Arrays.asList(reqCache).contains(id)) {
            // 重复请求
            System.out.println("请勿重复提交！！！" + id);
            return "执行失败";
        }
        synchronized (this.getClass()) {
            // 双重检查锁（DCL,double checked locking）提高程序的执行效率
            if (Arrays.asList(reqCache).contains(id)) {
                // 重复请求
                System.out.println("请勿重复提交！！！" + id);
                return "执行失败";
            }
            // 记录请求 ID
            if (reqCacheCounter >= reqCache.length){
                reqCacheCounter = 0; // 重置计数器
            }
            reqCache[reqCacheCounter] = id; // 将 ID 保存到缓存
            reqCacheCounter++; // 下标往后移一位
        }
        // 业务代码...
        System.out.println("添加用户ID:" + id);
        return "执行成功！";
    }


    //完善版——LRUMap
    //上面的代码基本已经实现了重复数据的拦截，但显然不够简洁和优雅，比如下标计数器的声明和业务处理等，
    // 但值得庆幸的是 Apache 为我们提供了一个 commons-collections 的框架，里面有一个非常好用的数据结构 LRUMap 可以保存指定数量的固定的数据，
    // 并且它会按照 LRU 算法，帮你清除最不常用的数据。

    //LRU 是 Least Recently Used 的缩写，即最近最少使用，是一种常用的数据淘汰算法，选择最近最久未使用的数据予以淘汰。

    // 最大容量 100 个，根据 LRU 算法淘汰数据的 Map 集合
    private LRUMap<String, Integer> reqCacheMap = new LRUMap<>(100);

    @RequestMapping("/add2")
    public String addUser2(String id) {
        // 非空判断(忽略)...
        synchronized (this.getClass()) {
            // 重复请求判断
            if (reqCacheMap.containsKey(id)) {
                // 重复请求
                System.out.println("请勿重复提交！！！" + id);
                return "执行失败";
            }
            // 存储请求 ID
            reqCacheMap.put(id, 1);
        }
        // 业务代码...
        System.out.println("添加用户ID:" + id);
        return "执行成功！";
    }


    //最终版——封装
    @RequestMapping("/add3")
    public String addUser3(String id) {
        // 非空判断(忽略)...
        // -------------- 幂等性调用（开始） --------------
        if (!IdempotentUtils.judge(id, this.getClass())) {
            return "执行失败";
        }
        // -------------- 幂等性调用（结束） --------------
        // 业务代码...
        System.out.println("添加用户ID:" + id);
        return "执行成功！";
    }


    //自定义注解形式
    @InterfaceSecurity
    @RequestMapping("/add4")
    public String addUser4(String id) {
        return "添加成功";
    }


    static class IdempotentUtils {

        // 根据 LRU(Least Recently Used，最近最少使用)算法淘汰数据的 Map 集合，最大容量 100 个
        private static LRUMap<String, Integer> reqCache = new LRUMap<>(100);

        /**
         * 幂等性判断
         * 验证重复提交
         * @return
         */
        public static boolean judge(String id, Object lockClass) {
            synchronized (lockClass) {
                // 重复请求判断
                if (reqCache.containsKey(id)) {
                    // 重复请求
                    System.out.println("请勿重复提交！！！" + id);
                    return false;
                }

                // 非重复请求，存储请求 ID
                reqCache.put(id, 1);
            }
            return true;
        }
    }

}
