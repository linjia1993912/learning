package learning.basis.data_structure;

import java.util.*;

/**
 * @Description:TreeMap使用示例
 * 在Map集合框架中，除了HashMap以外，TreeMap也是常用到的集合对象之一。
 * 与HashMap相比，TreeMap是一个能比较元素大小的Map集合，会对传入的key进行了大小排序。其中，可以使用元素的自然顺序，
 * 也可以使用集合中自定义的比较器来进行排序；
 * 不同于HashMap的哈希映射，TreeMap实现了红黑树的结构，形成了一颗二叉树
 * @Author LinJia
 * @Date 2020/8/3
 **/
public class TreeMap_Demo {

    //TreeMap排序

    //在使用自然顺序排序时候，需要区分两种情况：一种是Jdk定义的对象，一种是自己定义的对象；

    //使用元素自然排序
    //自定义对象需要实现Comparable接口
    private static class SortedTest implements Comparable<SortedTest>{

        private int age;

        public SortedTest(int age){
            this.age = age;
        }

        @Override
        public int compareTo(SortedTest sortedTest) {
            int num = this.age - sortedTest.age;
            if(num==0) {
                //为0时候，两者相同：
                return 0;
            }else if(num>0){
                //大于0时，传入的参数小：
                return 1;
            }else{
                //小于0时，传入的参数大：
                return -1;
            }
        }
    }

    //使用自定义比较器排序,被排序对象不需要实现Comparable接口
    private static class SortedTest1{

        private int age;

        public SortedTest1(int age){
            this.age = age;
        }

    }

    //使用自定义比较器排序
    //自定义比较器对象，需要实现Comparator接口，并实现比较方法compare(To1,To2)；
    //使用自定义比较器排序的话，被比较的对象无需再实现Comparable接口了,SortedTest1未实现Comparable接口
    public static class SortedTestComparator implements Comparator<SortedTest1> {
        @Override
        public int compare(SortedTest1 o1, SortedTest1 o2) {
            int num = o1.age - o2.age;
            if(num==0) {
                //为0时候，两者相同：
                return 0;
            }else if(num>0){
                //大于0时，后面的参数小：
                return 1;
            }else{
                //小于0时，前面的参数小：
                return -1;
            }
        }
    }


    public static void main(String[] args) {

        /*TreeMap<String,Object> treeMap = new TreeMap<>();
        System.out.println("初始化后,TreeMap元素个数为：" + treeMap.size());

        treeMap.put("hello",1);
        treeMap.put("world",2);
        treeMap.put("my",3);
        treeMap.put("name",4);
        treeMap.put("is",5);
        treeMap.put("huangqiuping",6);
        treeMap.put("i",6);
        treeMap.put("am",6);
        treeMap.put("a",6);
        treeMap.put("developer",6);
        System.out.println("添加元素后,TreeMap元素个数为：" + treeMap.size());

        //遍历
        for (Map.Entry<String,Object> entry:treeMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        //获取所有的key
        Set<String> keySet = treeMap.keySet();
        for (String strKey : keySet){
            System.out.println("TreeMap集合中的key:"+strKey);
        }

        //获取所有的value
        Collection<Object> collection = treeMap.values();
        for (Object object:collection){
            System.out.println("TreeMap集合中的value:" + object);
        }

        //获取集合内第一个元素
        System.out.println(treeMap.firstKey());

        System.out.println(treeMap.isEmpty());*/


        //自然排序
        //第一种情况：Integer对象
        TreeMap<Integer,String> treeMapFirst = new TreeMap<Integer, String>();
        treeMapFirst.put(1,"huangqiuping");
        treeMapFirst.put(6,"huangqiuping");
        treeMapFirst.put(3,"huangqiuping");
        treeMapFirst.put(10,"huangqiuping");
        treeMapFirst.put(7,"huangqiuping");
        treeMapFirst.put(13,"huangqiuping");
        System.out.println(treeMapFirst.toString());
        //第二种情况:SortedTest对象
        TreeMap<SortedTest,String> treeMapSecond = new TreeMap<SortedTest, String>();
        treeMapSecond.put(new SortedTest(10),"huangqiuping");
        treeMapSecond.put(new SortedTest(1),"huangqiuping");
        treeMapSecond.put(new SortedTest(13),"huangqiuping");
        treeMapSecond.put(new SortedTest(4),"huangqiuping");
        treeMapSecond.put(new SortedTest(0),"huangqiuping");
        treeMapSecond.put(new SortedTest(9),"huangqiuping");
        System.out.println(treeMapSecond.toString());
        //测试排序
        Set<SortedTest> keySet = treeMapSecond.keySet();
        for(SortedTest sortedTest :keySet){
            System.out.println(sortedTest.age);
        }
        //注意：调用put()方法时，会将传入的元素转化成Comparable类型对象，所以当你传入的元素没有实现Comparable接口时，就无法转换，遍会报错


        //自定义排序顺序
        //使用自定义比较器排序，需要在创建TreeMap对象时，将自定义比较器对象传入到TreeMap构造方法中；
        TreeMap<SortedTest1,String> treeMap = new TreeMap<>(new SortedTestComparator());
        treeMap.put(new SortedTest1(10),"hello");
        treeMap.put(new SortedTest1(21),"my");
        treeMap.put(new SortedTest1(15),"name");
        treeMap.put(new SortedTest1(2),"is");
        treeMap.put(new SortedTest1(1),"huangqiuping");
        treeMap.put(new SortedTest1(7),"world");
        //测试排序
        Set<SortedTest1> keySet1 = treeMap.keySet();
        for(SortedTest1 sortedTest :keySet1){
            System.out.println(sortedTest.age);
        }

    }
}
