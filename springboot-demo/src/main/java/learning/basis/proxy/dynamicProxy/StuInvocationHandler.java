package learning.basis.proxy.dynamicProxy;

import learning.basis.proxy.MonitorUtil;
import learning.basis.proxy.Person;
import learning.basis.proxy.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:动态代理 实现方式一
 * 创建StuInvocationHandler类，实现InvocationHandler接口，这个类中持有一个被代理对象的实例target。
 * InvocationHandler中有一个invoke方法，所有执行代理对象的方法都会被替换成执行invoke方法。
 * 再再invoke方法中执行被代理对象target的相应方法。
 * 当然，在代理过程中，我们在真正执行被代理对象的方法前加入自己其他处理。这也是Spring中的AOP实现的主要原理，
 * 这里还涉及到一个很重要的关于java反射方面的基础知识
 * @Author LinJia
 * @Date 2020/7/9
 **/
public class StuInvocationHandler<T> implements InvocationHandler {

    T target;

    public StuInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * proxy:代表动态代理对象
     * method：代表正在执行的方法
     * args：代表调用目标方法时传入的实参
     *
     *
     * 动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类中的方法。
     * 是因为所有被代理执行的方法，都是通过在InvocationHandler中的invoke方法调用的，所以我们只要在invoke方法中统一处理，
     * 就可以对所有被代理的方法进行相同的操作了
     *
     * 例如，这里的方法计时，所有的被代理对象执行的方法都会被计时，然而我只做了很少的代码量
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" + method.getName() + "方法");
        //代理过程中插入监测方法,计算该方法耗时
        MonitorUtil.start();
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }


    //测试
    //创建动态代理对象
    public static void main(String[] args) {
        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("张三");

        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StuInvocationHandler<>(zhangsan);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

        //代理执行上交班费的方法
        stuProxy.giveMoney();
    }
}
