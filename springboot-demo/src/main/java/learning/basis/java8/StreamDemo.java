package learning.basis.java8;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static java.util.Comparator.comparingLong;

/**
 * @author LinJia
 * @description: Java8 stream流
 * 在 Java 8 中, 集合接口有两个方法来生成流：
 * stream() − 为集合创建串行流。
 * parallelStream() − 为集合创建并行流。
 * @date 2021/3/30
 */
public class StreamDemo {
    //Demo1
    static class Demo1 {

        public static void main(String[] args) {

            //filter 方法用于通过设置的条件过滤出元素。filter去掉空字符串
            List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
            List<String> filterList = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
            System.out.println(filterList);
            //以下代码片段使用 filter 方法过滤出空字符串：
            long count = strings.stream().filter(s -> s.isEmpty()).count();
            System.out.println(count);

            //Stream 提供了新的方法 'forEach' 来迭代流中的每个数据
            //使用 forEach 输出了10个随机数
            Random random = new Random();
            //limit 方法用于获取指定数量的流
            random.ints().limit(10).forEach(System.out::println);

            //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
            List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
            //distinct去重
            List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
            System.out.println(squaresList);

            //sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
            random.ints().limit(10).sorted().forEach(System.out::println);
            //正序
            System.out.println("正序：");
            numbers.stream().sorted((x, y) -> x - y).forEach(System.out::println);
            //倒序排序
            System.out.println("倒序：");
            numbers.stream().map(integer -> integer * integer).sorted((x, y) -> y - x).collect(Collectors.toList()).forEach(System.out::println);

            //并行（parallel）程序
            //parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
            // 获取空字符串的数量
            long count2 = strings.parallelStream().filter(s -> s.isEmpty()).count();
            System.out.println(count2);

            //Collectors
            //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
            //List<String> filterList = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
            String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
            System.out.println("合并字符串: " + mergedString);

            //统计
            //另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
            IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
            System.out.println("列表中最大的数 : " + stats.getMax());
            System.out.println("列表中最小的数 : " + stats.getMin());
            System.out.println("所有数之和 : " + stats.getSum());
            System.out.println("平均数 : " + stats.getAverage());
        }
    }

    //Demo2
    public static void main(String[] args) {
        //Java8 快速实现List转map 、分组、过滤等操作

        //添加一些测试数据
        //存放apple对象集合
        List<Apple> appleList = new ArrayList<>();

        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

        //分组
        //List里面的对象元素，以某个属性来分组，例如，以id分组，将id相同的放在一起：
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println("groupBy:" + groupBy);

        //List转Map
        //id为key，apple对象为value
        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
        System.out.println("appleMap:" + appleMap);

        //字符串转 map 输出。
        List<String> strList = Arrays.asList("a", "ba", "bb", "abc", "cbb", "bba", "cab");
        Map<Integer, String> strMap = new HashMap<>();
        strMap = strList.stream().collect(Collectors.toMap(s -> strList.indexOf(s), str -> str));
        System.out.println("遍历map");
        strMap.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });
        /*Iterator<Map.Entry<Integer, String>> iterable = strMap.entrySet().iterator();
        while (iterable.hasNext()) {
            Map.Entry<Integer, String> entry = iterable.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/

        //过滤Filter
        //从集合中过滤出来符合条件的元素
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
        System.out.println("filterList:" + filterList);

        //求和
        //将集合中的数据按照某个属性求和
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("totalMoney:" + totalMoney);

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

        //Java7排序
        Collections.sort(appleList, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getNum().compareTo(o2.getNum());
            }
        });
        System.out.println("Java7排序=" + appleList);

        //Java8排序
        //根据num排序
        //正序
        Collections.sort(appleList, (s1, s2) -> s1.getNum().compareTo(s2.getNum()));
        //上下方法等同
        List<Apple> appleList2 = appleList.stream().sorted((s1, s2) -> s1.getNum().compareTo(s2.getNum())).collect(Collectors.toList());
        System.out.println("Java8排序=" + appleList);

        //倒序
        appleList.sort(Comparator.comparingInt(Apple::getNum).reversed());
        System.out.println(appleList);


    }
}