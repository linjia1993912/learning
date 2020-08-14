package learning.basis.data_structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Description:HashSet是Java集合Set的一个实现类，Set是一个接口，
 * 其实现类除HashSet之外，还有TreeSet，并继承了Collection，HashSet集合很常用
 * HashSet是无序的
 * 线程不安全
 * @Author LinJia
 * @Date 2020/7/24
 **/
public class HashSet_LinkedHashSet {

    public static void main(String[] args) {
        Set<String> hashSet = new HashSet();
        //添加元素 方法声明只有当元素尚未存在于集合中时才会添加元素。如果成功添加了元素，则该方法返回true，否则返回false
        System.out.println(hashSet.add("test"));
        System.out.println(hashSet.add("linjia"));

        //HashSet遍历出的元素也并非按照插入的顺序

        //迭代器遍历HashSet
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //普通for循环遍历
        for (String set:hashSet){
            System.out.println(set);
        }

        //forEach遍历
        hashSet.forEach(h ->{
            System.out.println(h.toString());
        });

        //通过迭代器删除元素
        /*Iterator iterator1 = hashSet.iterator();
        while (iterator1.hasNext()){
            iterator1.remove();
        }*/

        //contains方法的目的是检查给定HashSet中是否存在元素。如果找到该元素，则返回true，否则返回false。
        System.out.println(hashSet.contains("linjia"));

        //该方法将从集合中删除指定的元素。如果集合包含指定的元素，则此方法返回true
//        System.out.println(hashSet.remove("linjia"));

        //打算从集合中删除所有项目时，我们使用此方法。底层实现只是清除底层HashMap中的所有元素。
        hashSet.clear();

        //这是API中的基本方法之一。它被大量使用，因为它有助于识别HashSet中存在的元素数量。底层实现只是将计算委托给HashMap的size（）方法
        System.out.println(hashSet.size());

        //使用此方法来确定HashSet的给定实例是否为空。如果集合不包含任何元素，则此方法返回true
        System.out.println(hashSet.isEmpty());

        //继承了HashSet，实现了Set接口,底层还是使用了LinkedHashMap
        LinkedHashSet<String>linkedHashSet = new LinkedHashSet();

        //因为是HashSet的子类,所以也是保证元素唯一的,与HashSet的原理一样
        //遍历和插入的顺序是一致的
        linkedHashSet.add("1");
        linkedHashSet.add("2");
        linkedHashSet.add("3");

        //遍历
        Iterator iterator2 = linkedHashSet.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

    }
}
