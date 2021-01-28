package learning.basis;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:List去除重复数据的五种方式
 * @Author LinJia
 * @Date 2021/1/28
 **/
public class ArrayListExample {

    public static void main(String[] args) {
        //使用LinkedHashSet删除arraylist中的重复数据
        //LinkedHashSet是在一个ArrayList删除重复数据的最佳方法。LinkedHashSet在内部完成两件事：
        //1.删除重复数据
        //2.保持添加到其中的数据的顺序
       /* ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList);

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(numbersList);

        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        System.out.println(listWithoutDuplicates);*/


        //使用java8新特性stream进行List去重
        //要从arraylist中删除重复项，我们也可以使用java 8 stream api。使用steam的distinct()方法返回一个由不同数据组成的流，通过对象的equals（）方法进行比较。
        //收集所有区域数据List使用Collectors.toList()。
        /*ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList);

        List<Integer> listWithoutDuplicates = numbersList.stream().distinct().collect(Collectors.toList());
        System.out.println(listWithoutDuplicates);*/


        ArrayList<String> numbersList = new ArrayList<>(Arrays.asList("1", "1", "2", "2", "3", "5", "5", "7", "6"));
        //利用HashSet不能添加重复数据的特性 由于HashSet不能保证添加顺序，所以只能作为判断条件保证顺序：
        //removeDuplicate(numbersList);

        //利用List的contains方法循环遍历,重新排序,只添加一次数据,避免重复：
        //removeDuplicate(numbersList);

        //双重for循环去重
        removeDuplicate(numbersList);
        System.out.println(numbersList);

    }

    /*private static void removeDuplicate(List<String> list) {
        HashSet<String> set = new HashSet<String>(list.size());
        List<String> result = new ArrayList<String>(list.size());
        for (String str : list) {
            if (set.add(str)) {
                result.add(str);
            }
        }
        list.clear();
        list.addAll(result);
    }*/

   /* private static void removeDuplicate(List<String> list) {
        List<String> result = new ArrayList<String>(list.size());
        for (String str : list) {
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        list.clear();
        list.addAll(result);
        Collections.sort(list);
    }*/

    private static void removeDuplicate(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j && list.get(i) == list.get(j)) {
                    list.remove(list.get(j));
                }
            }
        }
        Collections.sort(list);
    }
}