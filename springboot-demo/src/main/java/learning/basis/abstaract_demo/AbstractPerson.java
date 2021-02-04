package learning.basis.abstaract_demo;

/**
 * @Description:抽象类
 * 如果我们的项目中有Women和Man，都继承Person，而且Women和Man绝大多数方法都相同，只有一个方法DoSomethingInWC（）不同（例子比较粗俗，各位见谅），
 * 那么当然定义一个AbstractPerson抽象类比较合理，因为它可以把其他所有方法都包含进去，
 * 子类只定义DoSomethingInWC（），大大减少了重复代码量。
 * @Author LinJia
 * @Date 2021/2/3
 **/
public abstract class AbstractPerson {

    //此时test1  test2相当于公共的方法，只有DoSomethingInWC抽象方法留给子类实现，减少了重复代码量
    public void test1(){
        System.out.println(1);
    }

    public void test2(){
        System.out.println(2);
    }

    //抽象方法,留给子类实现
    public abstract String DoSomethingInWC();

}