package learning.basis.data_structure;

import java.util.*;

/**
 * @Description:Hashtable使用方式
 * HashTable是一个Entry<>的数组，源码：private transient Entry<?,?>[] table;
 * Entry是HashTable.class的一个内部类
 * 线程安全，put方法被synchronized修饰
 * 如果有put相同的值，原有的值会被覆盖
 *
 * Hashtable底层是一个哈希表，它是一个线程安全的集合，单线程集合，速度慢，Hashtable集合不能存储null值，null键
 *
 *
 * @Author LinJia
 * @Date 2020/7/23
 **/
public class HashtableDemo {

    public static void main(String[] args) {

        java.util.Hashtable<String,Object> hashtable = new java.util.Hashtable<>();

        hashtable.put("name","zhangsan");
        hashtable.put("sex","男");

        //通过Enumeration遍历Hashtable的key
        //效率高
        Enumeration enumeration = hashtable.keys();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }

        //通过Enumeration遍历Hashtable的value
        //效率高
        Enumeration enumeration1 = hashtable.elements();
        while (enumeration1.hasMoreElements()){
            System.out.println(enumeration1.nextElement());
        }

        //通过entrySet遍历Hashtable
        //效率高
        Iterator iterator = hashtable.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }

        //通过keyset来遍历Hashtable
        //效率低
        Iterator iterator1 = hashtable.keySet().iterator();
        while (iterator1.hasNext()){
            //先获取key
            String key = (String) iterator1.next();
            System.out.println("key:"+key +" value:"+hashtable.get(key));
        }

        //遍历Hashtable的values
        Collection collection = hashtable.values();
        Iterator iterator2 = collection.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

    }
}
