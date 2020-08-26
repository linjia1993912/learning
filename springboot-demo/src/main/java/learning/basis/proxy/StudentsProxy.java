package learning.basis.proxy;

/**
 * @Description:班长类  静态代理
 * 代理类
 * 代理学生交班费
 *
 * 静态代理的例子中，代理类(studentProxy)是自己定义好的
 * 在程序运行之前就已经编译完成。然而动态代理，代理类并不是在Java代码中定义的，
 * 而是在运行时根据我们在Java代码中的“指示”动态生成的。相比于静态代理，
 * 动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类中的方法
 * 比如说，想要在每个代理的方法前都加上一个处理方法
 * @Author LinJia
 * @Date 2020/7/9
 **/
public class StudentsProxy implements Person{

    //被代理的学生
    Student student;

    public StudentsProxy(Person person){
        //只代理学生对象
        if(person.getClass() == Student.class){
            this.student = (Student) person;
        }
    }

    //代理上交班费，调用被代理学生的上交班费行为
    @Override
    public void giveMoney() {

        //假如如果想在此处增加处理操作，则需要在所有代理类都修改方法
        //这里只有一个giveMoney方法，就写一次beforeMethod方法，但是如果出了giveMonney还有很多其他的方法，那就需要写很多次beforeMethod方法
        //beforeMethod();

        student.giveMoney();
    }


    //测试代理模式
    public static void main(String[] args) {

        //被代理的学生张三，他的班费上交有代理对象monitor（班长）完成
        Person person = new Student("张三");
        //生成代理对象，并将张三传给代理对象
        Person monitor = new StudentsProxy(person);
        //班长代理上交班费
        monitor.giveMoney();
    }

}
