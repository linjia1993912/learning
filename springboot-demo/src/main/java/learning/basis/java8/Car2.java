package learning.basis.java8;

/**
 * @author LinJia
 * @description: 一个接口有默认方法，考虑这样的情况，一个类实现了多个接口，且这些接口有相同的默认方法，以下实例说明了这种情况的解决方法：
 * @date 2021/3/30
 */
public class Car2 implements Vehicle, FourWheeler {

    //第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法：
    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        //静态默认方法
        Vehicle.blowHorn();
        System.out.println("我是一辆四轮汽车!");
    }

    //第二种解决方案可以使用 super 来调用指定接口的默认方法：
    /*@Override
    public void print(){
        Vehicle.super.print();
    }*/

    public static void main(String[] args) {
        Vehicle vehicle = new Car2();
        vehicle.print();
    }
}