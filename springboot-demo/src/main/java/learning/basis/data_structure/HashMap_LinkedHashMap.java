package learning.basis.data_structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:遍历HashMap的方式
 * hashmap是一种基于Key-Value的数据结构
 * 允许Key和Value都允许为null
 * 非同步；HashMap是非同步的，想要线程同步的HashMap可以用HashTable或ConcurrentHashMap。
 * @Author LinJia
 * @Date 2020/7/14
 **/
public class HashMap_LinkedHashMap {


    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        //使用 For-each 循环遍历 HashMap
        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // 2. 通过keySet或values来实现遍历,性能略低于第一种方式
        // 遍历map中的键
        for (String key : map.keySet()) {
            System.out.println("key = " + key);
        }
        // 遍历map中的值
        for (Object object:map.values()){
            System.out.println("value = " + object);
        }

        // 3. 使用 Iterator 遍历 HashMap EntrySet
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry = iterator.next();
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        //4. 使用 Iterator 遍历 HashMap KeySet
        Iterator<String> iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()){
            String key = iterator1.next();
            System.out.println(map.get(key));
        }

        // 5. java8 Lambda
        // java8提供了Lambda表达式支持，语法看起来更简洁，可以同时拿到key和value，
        // 不过，经测试，性能低于entrySet,所以更推荐用entrySet的方式
        /*map.forEach((key,value) - >{
            System.out.println(key + ":" + value);
        });*/

        //6.使用 Stream API 遍历 HashMap
        map.entrySet().stream().forEach((stringObjectEntry -> {
            System.out.println(stringObjectEntry.getKey());
            System.out.println(stringObjectEntry.getValue());
        }));
    }


    //LinkedHashMap演示
    //LinkedHashMap继承于HashMap
    static class LinkedHashMapDemo{

        public static void main(String[] args) {

            //创建LinkedHashMap
            // 第三个参数用于指定accessOrder值
            Map<String,Object> map1 = new LinkedHashMap<>(16, 0.75f, true);

            //并提供了put和get方法来进行数据存取
            map1.put("1","1");
            map1.put("2","2");
            map1.put("3","3");

            System.out.println("开始时顺序：");
            //遍历LinkedHashMap，跟HashMap相似
            for (Map.Entry<String,Object> m :map1.entrySet()){
                System.out.println("key:" + m.getKey() + ",value:" + m.getValue());
            }

            System.out.println("通过get方法，导致key为1对应的Entry到表尾");
            map1.get("1");

            //因为调用了get("name1")导致了name1对应的Entry移动到了最后，这里只要知道LinkedHashMap有插入顺序和访问顺序两种就可以
            for (Map.Entry<String,Object> m :map1.entrySet()){
                System.out.println("key:" + m.getKey() + ",value:" + m.getValue());
            }

        }

    }
}
