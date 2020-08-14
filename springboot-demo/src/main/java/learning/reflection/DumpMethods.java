package learning.reflection;

import java.lang.reflect.Method;

/**
 * @Description:获取方法
 * DumpMethods类演示了Reflection API的基本作用，它读取命令行参数指定的类名，然后打印这个类所具有的方法信息
 * @Author LinJia
 * @Date 2020/7/2
 **/
public class DumpMethods {

    public static void main(String[] args) throws Exception {
        //获得字符串所标识的类的class对象
        Class<?> classType = Class.forName("java.lang.String");//在此处传入字符串指定类名，所以参数获取可以是一个运行期的行为，可以用args[0]
        //返回class对象所对应的类或接口中，所声明的所有方法的数组（包括私有方法）
        Method [] methods = classType.getDeclaredMethods();

        //遍历输出所有方法声明
        for(Method method : methods)
        {
            System.out.println(method);
        }
    }
}
