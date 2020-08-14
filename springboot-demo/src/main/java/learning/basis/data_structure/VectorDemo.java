package learning.basis.data_structure;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * @Description:Vector他和ArrayList有一些相似,其内部都是通过一个容量能够动态增长的数组来实现的。
 * 不同点是Vector是线程安全的。因为其内部有很多同步代码快来保证线程安全
 * @Author LinJia
 * @Date 2020/7/30
 **/
public class VectorDemo {

    public static void main(String[] args) {
        Vector <Integer> vector = new Vector<>();

        for (int i = 0; i <5 ; i++) {
            vector.add(i);
        }

        //遍历
        for (Integer integer:vector){
            System.out.println(integer);
        }

        vector.forEach(integer -> {
            System.out.println(integer);
        });

        for (int i = 0; i < vector.size() ; i++) {
            System.out.println(vector.get(i));
        }

        Iterator iterator = vector.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
    }

}
