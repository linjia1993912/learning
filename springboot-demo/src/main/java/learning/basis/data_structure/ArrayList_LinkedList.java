package learning.basis.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Description: ArrayList常用API
 * @Author LinJia
 * @Date 2020/7/23
 **/
public class ArrayList_LinkedList {
    public static void main(String[] args) {
        //创建ArrayList
        ArrayList arrayList = new ArrayList();

        //添加元素
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        //将元素添加到第一个位置
        arrayList.add(0,111);

        //获取第一个元素
        System.out.println(arrayList.get(0));

        //删除元素
        arrayList.remove(3);

        //判断list中是否包含3
        System.out.println(arrayList.contains(3));

        //通过Iterator遍历
        for (Iterator iterator = arrayList.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }

        //将ArrayList转为数组
        Integer [] arr = (Integer[]) arrayList.toArray(new Integer[0]);
        for(Integer integer:arr){
            System.out.println(integer);
        }

        //清空list
        arrayList.clear();

        //判断是否为空
        System.out.println(arrayList.isEmpty());
    }


    //LinkedList相关常用操作
    static class LinkedListDemo{

        public static void main(String[] args) {
            //LinkedList <String> linkedList = new LinkedList<>();

            String[] arr = {"H", "E", "L", "L", "O"};
            LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(arr));
            System.out.println(linkedList);

            //快速随机访问
            for (int size = linkedList.size(), i = 0; i < size; i++) {
                System.out.println(linkedList.get(i));
            }

            for (String str: linkedList) {
                System.out.println(str);
            }

            //迭代器遍历
            Iterator iterator = linkedList.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }

            linkedList.forEach(l->{
                System.out.println(l);
            });

        }
    }
}
