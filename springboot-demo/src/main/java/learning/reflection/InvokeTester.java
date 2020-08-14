package learning.reflection;

import java.lang.reflect.Method;

/**
 * @Description:通过反射调用方法
 * @Author LinJia
 * @Date 2020/7/2
 **/
public class InvokeTester {

    public int add(int param1, int param2)
    {
        return param1 + param2;

    }

    public String echo(String message)
    {
        return "Hello: " + message;
    }

    public static void main(String[] args) throws Exception {

        // 以前的常规执行手段
        InvokeTester tester = new InvokeTester();
        System.out.println(tester.add(1, 2));
        System.out.println(tester.echo("Tom"));
        System.out.println("---------------------------");


        // 通过反射的方式

        // 第一步，获取Class对象
        // 前面用的方法是：Class.forName()方法获取
        // 这里用第二种方法，类名.class
        Class<?> classType = InvokeTester.class;

        // 生成新的对象：用newInstance()方法
        Object invokeTester = classType.newInstance();
        System.out.println(invokeTester instanceof InvokeTester); // 输出true


        // 通过反射调用方法
        // 首先需要获得与该方法对应的Method对象
        //getMethod 获得该类某个公有的方法
        Method addMethod = classType.getMethod("add", new Class[] { int.class,
                int.class });
        // 第一个参数是方法名，第二个参数是这个方法所需要的参数的Class对象的数组

        // 调用目标方法
        Object result = addMethod.invoke(invokeTester, new Object[] { 1, 2 });
        System.out.println(result); // 此时result是Integer类型

        //调用第二个方法
        //getDeclaredMethod 获得该类某个方法
        Method echoMethod = classType.getDeclaredMethod("echo", new Class[]{String.class});
        Object result2 = echoMethod.invoke(invokeTester, new Object[]{"Tom"});
        System.out.println(result2);

    }
}
