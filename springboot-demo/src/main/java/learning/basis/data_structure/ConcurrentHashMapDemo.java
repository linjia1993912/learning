package learning.basis.data_structure;

import java.util.Iterator;
import java.util.Map;


/**
 * @Description:ConcurrentHashMap使用
 * HashMap 线程不安全
 * HashTable 线程安全，但是效率慢
 * @Author LinJia
 * @Date 2020/7/27
 **/
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        java.util.concurrent.ConcurrentHashMap<String,Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap();

        concurrentHashMap.put("1","1");
        concurrentHashMap.put("2","2");
        concurrentHashMap.put("3","3");

        //遍历
        for (Map.Entry<String,Object> con : concurrentHashMap.entrySet()){
            System.out.println(con.getKey() +" " + con.getValue());
        }

        //迭代器遍历
        Iterator<Map.Entry<String,Object>> iterator = concurrentHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> con = iterator.next();
            System.out.println(con.getKey() + " " + con.getValue());
        }

        //遍历key
        for (String key:concurrentHashMap.keySet()){
            System.out.println("key="+ key);
        }

        //遍历value
        for (Object value : concurrentHashMap.values()){
            System.out.println("value="+value);
        }

        //java8 Lambda
        //遍历
        concurrentHashMap.forEach((key,value)->{
            System.out.println(key + " " + value);
        });

    }
}
