package learning.basis.java8;

/**
 * @author LinJia
 * @description: Java 8 新增了接口的默认方法。
 * 多个默认方法
 * @date 2021/3/30
 */
public interface DefaultFourWheeler {
    //default
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}
