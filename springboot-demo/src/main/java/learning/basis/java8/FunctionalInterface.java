package learning.basis.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * @author LinJia
 * @description: 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
 * 函数式接口可以被隐式转换为 lambda 表达式。
 * <p>
 * 如定义了一个函数式接口如下：
 * @FunctionalInterface interface GreetingService
 * {
 * void sayMessage(String message);
 * }
 * 那么就可以使用Lambda表达式来表示该接口的一个实现(注：JAVA 8 之前一般是用匿名类实现的)：
 * GreetingService greetService1 = message -> System.out.println("Hello " + message);
 * @date 2021/3/30
 */
public class FunctionalInterface {

    public static void main(String[] args) {
        //Predicate <T> 接口是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果。
        //该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）。
        //该接口用于测试对象是 true 或 false。

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true
        System.out.println("输出所有数据:");
        // 传递参数 n
        eval(list, n -> true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true
        System.out.println("输出所有偶数:");
        eval(list, n -> n % 2 == 0);

        // 如果 n%2 不为 0 test 方法返回 true
        System.out.println("输出所有基数:");
        eval(list, n -> n % 2 != 0);

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true
        System.out.println("输出大于 3 的所有数字:");
        eval(list, n -> n > 3);

        //输出京5牌子的自行车
        List<Bicycle> bicycleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bicycleList.add(new Bicycle("京"+i, i));
        }
        System.out.println("输出京5牌子的自行车:");
        eval2(bicycleList,n -> n.getBrand().equals("京5"));

    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    public static void eval2(List<Bicycle> list, Predicate<Bicycle> predicate) {
        for (Bicycle n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    static class Bicycle {
        private String brand;
        private Integer frameSize;

        public Bicycle(String brand, Integer frameSize) {
            this.brand = brand;
            this.frameSize = frameSize;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Integer getFrameSize() {
            return frameSize;
        }

        public void setFrameSize(Integer frameSize) {
            this.frameSize = frameSize;
        }

        @Override
        public String toString() {
            return "Bicycle{" +
                    "brand='" + brand + '\'' +
                    ", frameSize=" + frameSize +
                    '}';
        }
    }

}