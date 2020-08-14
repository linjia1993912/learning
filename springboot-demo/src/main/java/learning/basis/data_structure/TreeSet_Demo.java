package learning.basis.data_structure;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @Description:演示TreeSet使用
 * TreeSet 是一个有序的集合，它的作用是提供有序的Set集合
 * TreeSet是基于TreeMap实现的。TreeSet中的元素支持2种排序方式：自然排序 或者 根据创建TreeSet 时提供的 Comparator 进行排序。这取决于使用的构造方法
 *
 * @Author LinJia
 * @Date 2020/8/3
 **/
public class TreeSet_Demo {

    //自定义对象
    private static class Person implements Comparable<Person>{

        private String name;
        private int age;

        public Person(String name,int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person o) {
            //当compareTo方法返回0的时候集合中只有一个元素 return 0;
            //当compareTo方法返回正数的时候集合会怎么存就怎么取 return 1;
            //当compareTo方法返回负数的时候集合会倒序存储 return -1;

            //想按照学生的年龄进行排序.
            if(this.age>o.age){
                return 1;
            }else if(this.age == o.age){
                //如果年龄相同的时候  就需要按照姓名排序
                return this.name.compareTo(o.name);
            }
            return -1;
        }
    }

    public static void main(String[] args) {

        //TreeSet保存自定义对象
        TreeSet <Person> treeSet = new TreeSet<>();
        treeSet.add(new Person("张三", 11));
        treeSet.add(new Person("李四", 12));
        treeSet.add(new Person("王五", 15));
        treeSet.add(new Person("赵六", 21));

        for (Person person:treeSet){
            System.out.println(person.age+" "+person.name);
        }

        //执行抛出异常:java.lang.ClassCastException: learning.basis.data_structure.TreeSet_Demo$Person cannot be cast to java.lang.Comparable
        //显然是出现了类型转换异常。原因在于我们需要告诉TreeSet如何来进行比较元素，如果不指定，就会抛出这个异常

        //如何解决：如何指定比较的规则，需要在自定义类(Person)中实现Comparable接口，并重写接口中的compareTo方法


        //保存普通对象
        TreeSet ts= new TreeSet();
        ts.add("cba");
        ts.add("abcd");
        ts.add("aaa");
        ts.add("bca");

        //遍历
        Iterator iterator = ts.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        ts.forEach(t->{
            System.out.println(t);
        });
    }
}
