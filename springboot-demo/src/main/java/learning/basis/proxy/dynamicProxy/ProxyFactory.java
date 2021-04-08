package learning.basis.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:动态代理 实现方式二
 * 代理工厂，给多个目标对象生成代理对象！
 * @Author LinJia
 * @Date 2020/8/26
 **/
public class ProxyFactory {

    // 接收一个目标对象
    private Object target;

    //传入需要被代理的对象
    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * @return java.lang.Object
     * @Description: 返回对目标对象(target)代理后的对象(proxy)
     * 动态生成代理对象
     * @Author LinJia
     * @Date 2020/8/26 10:23
     * @Param []
     **/
    public Object getProxyInstance() {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),// 目标对象使用的类加载器
                target.getClass().getInterfaces(),// 目标对象实现的所有接口
                new InvocationHandler() {
                    // 执行代理对象方法时候触发
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 获取当前执行的方法的方法名
                        String methodName = method.getName();
                        // 方法返回值
                        Object result = null;
                        if ("find".equals(methodName)) {
                            // 直接调用目标对象方法
                            System.out.println("执行查询方法...");
                            result = method.invoke(target, args);
                        } else {
                            System.out.println("开启事务...");
                            // 执行目标对象方法
                            result = method.invoke(target, args);
                            System.out.println("提交事务...");
                        }
                        return result;
                    }
                });
        return proxy;
    }


    public static void main(String[] args) {
        //创建目标对象
        IUserDao iUserDao = new UserDao();
        System.out.println("目标对象：" + iUserDao.getClass());

        //代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(iUserDao).getProxyInstance();
        System.out.println("代理对象：" + proxy.getClass());

        proxy.save();
        proxy.find();
    }

}
