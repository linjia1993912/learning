package learning.basis.generics;

/**
 * @Description:泛型方法
 * @Author LinJia
 * @Date 2020/7/21
 **/
public class ArrayAlg {

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    public static void main(String[] args) {
        Integer [] num = {1,2,3,4,5};
        System.out.println(ArrayAlg.getMiddle(num));
    }
}
