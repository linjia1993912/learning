package learning.basis.proxy;

/**
 * @Description: 学生类
 * 被代理类
 * @Author LinJia
 * @Date 2020/7/9
 **/
public class Student implements Person{

    private String name;

    public Student(String name){
        this.name = name;
    }

    //交班费
    @Override
    public void giveMoney() {
        System.out.println(name + "上交班费50元");
    }
}
