package learning.basis;

/**
 * @Description:演示静态代码块、非静态代码块、构造函数执行顺序
 * @Author LinJia
 * @Date 2020/7/7
 **/
public class StaticBeanTest {

    //静态代码块
    static {
        System.out.println("静态代码块");
    }

    //构造方法
    StaticBeanTest(){
        System.out.println("构造函数");
    }

    //非静态代码块
    {
        System.out.println("非静态代码块");
    }

    public static void test(){
        System.out.println("测试");
    }


    public static void main(String[] args) {
        //StaticBeanTest.test();

        //不管new几次，静态代码块只执行一次

        new StaticBeanTest();

        new StaticBeanTest();
    }
}
