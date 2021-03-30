package learning.basis.java8;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author LinJia
 * @description: 方法引用通过方法的名字来指向一个方法。
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 * 方法引用使用一对冒号 ::
 * @date 2021/3/30
 */
public class Car {

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    //方法引用包括以下四种类型：
    //1.静态方法
    //2.特定对象的实例方法
    //3.特定类型的任意对象的实例方法
    //4.构造方法
    public static void main(String[] args) {
        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        final Car car = Car.create(Car::new);

        final List<Car> carList = Arrays.asList(car);

        //静态方法引用：它的语法是Class::static_method，实例如下：
        carList.forEach(Car::collide);
        //或者
        List<String> messages = Arrays.asList("hello", "baeldung", "readers!");
        //messages.forEach(a -> System.out.println(StringUtils.capitalize(a)));
        //messages.forEach(StringUtils::capitalize);
        //System.out.println(messages);
        List<String> list = messages.stream().map(StringUtils::capitalize).collect(Collectors.toList());
        System.out.println(list);

        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        carList.forEach(Car::repair);
        List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);
        //如果我们使用经典的 lambda 表达式，这两个参数都需要显式传递，而使用方法引用则要简单得多：
        List<Integer> numbers1 = numbers.stream().sorted((a,b) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println(numbers1);
        List<Integer> numbers2 = numbers.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println(numbers2);

        //特定对象的方法引用：它的语法是instance::method实例如下：
        final Car car1 = Car.create(Car::new);
        carList.forEach(car1::follow);

        //创建一个 BicycleComparator 对象来比较自行车尺寸：
        BicycleComparator bicycleComparator = new BicycleComparator();

        List<Bicycle> bicycleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bicycleList.add(new Bicycle("京"+i, i));
        }
        //使用lambda表达式按尺寸大小对自行车进行排序，但需要指定两个自行车实例进行比较
        bicycleList.stream().sorted((a,b) -> a.getFrameSize().compareTo(b.getFrameSize()));
        System.out.println(bicycleList);
        //我们可以使用方法引用让编译器把句柄参数传递给我们：
        bicycleList.stream().sorted(bicycleComparator::compare);
        System.out.println(bicycleList);

        List<String> names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);
        //实例中我们将 System.out::println 方法作为静态方法来引用。
    }

    //引用特定对象的实例方法
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

    static class BicycleComparator implements Comparator<Bicycle> {
        @Override
        public int compare(Bicycle o1, Bicycle o2) {
            return o1.getFrameSize().compareTo(o2.getFrameSize());
        }
    }
}