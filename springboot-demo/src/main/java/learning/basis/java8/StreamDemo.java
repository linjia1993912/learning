package learning.basis.java8;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static java.util.Comparator.comparingLong;
/**
 * @author LinJia
 * @description: Java新特性 stream流
 * @date 2021/3/30
 */
public class StreamDemo {
    public static void main(String[] args) {

        //Java8 快速实现List转map 、分组、过滤等操作

        //添加一些测试数据
        //存放apple对象集合
        List<Apple> appleList = new ArrayList<>();

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

        //分组
        //List里面的对象元素，以某个属性来分组，例如，以id分组，将id相同的放在一起：
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println("groupBy:"+groupBy);

        //List转Map
        //id为key，apple对象为value
        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1,k2)->k1));
        System.out.println("appleMap:"+appleMap);

        //过滤Filter
        //从集合中过滤出来符合条件的元素
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
        System.out.println("filterList:"+filterList);

        //求和
        //将集合中的数据按照某个属性求和
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println("totalMoney:"+totalMoney);

        //查找流中最大 最小值
        //Collectors.maxBy 和 Collectors.minBy 来计算流中的最大或最小值
        Optional<Apple> maxApple = appleList.stream().collect(Collectors.maxBy(Comparator.comparing(Apple::getMoney)));
        maxApple.ifPresent(System.out::println);
        //最小值
        Optional<Apple> minApple = appleList.stream().collect(Collectors.minBy(Comparator.comparing(Apple::getMoney)));
        minApple.ifPresent(System.out::println);

        //去重
        //根据ID去重
        List<Apple> unique = appleList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparingLong(Apple::getId))), ArrayList::new)
        );
        System.out.println(unique);

        //根据num排序
        //正序
        appleList.sort((a,b) -> a.getNum() - b.getNum());
        System.out.println(appleList);
        //倒序
        appleList.sort(Comparator.comparingInt(Apple::getNum).reversed());
        System.out.println(appleList);


    }
}