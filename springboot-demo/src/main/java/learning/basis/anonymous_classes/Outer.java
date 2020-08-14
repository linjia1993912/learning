package learning.basis.anonymous_classes;

/**
 * @Description:演示内部类
 * @Author LinJia
 * @Date 2020/7/2
 **/
public class Outer {

    private String str ="外部类中的字符串";

    //定义一个内部类
    class Inner{
        private String inStr= "内部类中的字符串";

        //定义一个普通方法
        public void print(){
            //调用外部类的str属性
            System.out.println(str);
        }
    }

    public void fun(){
        //内部类对象
        Inner in = new Inner();
        //内部类对象提供的print
        in.print();
    }


    //匿名内部类
    private int num = 5;

    public void dispaly(int temp)
    {
        //匿名内部类，匿名的实现了MyInterface接口
        //匿名内部类没有类名 因此没构造方法
        //隐藏的class声明
        new MyInterface()
        {
            public void test()
            {
                System.out.println("匿名实现MyInterface接口");
                System.out.println(temp);
            }
        }.test();
    }

    public static void main(String[] args) {
        //创建外部类对象
        Outer out = new Outer();
        //外部类方法
        out.fun();

        out.dispaly(3);

    }
}
