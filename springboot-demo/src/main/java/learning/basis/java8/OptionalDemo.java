package learning.basis.java8;

import java.util.Optional;

/**
 * @author LinJia
 * @description: Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 * Optional 类的引入很好的解决空指针异常。
 * @date 2021/3/31
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        //ofNullable 如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional。
        Optional<Integer> a = Optional.ofNullable(value1);
        //判断非空
        if(!a.isPresent()){
            System.out.println("value1为null");
        }

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        //of 返回一个指定非null值的Optional。
        Optional<Integer> b = Optional.of(value2);

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value3 = a.orElse(new Integer(100));
        System.out.println("value3= "+value3);

        //Optional.get - 获取值，值需要存在
        Integer value4 = b.get();
        System.out.println("value4= "+value4);

        System.out.println("value1 + value2= "+(value3 + value4));
    }
}