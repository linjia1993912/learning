package learning.design_mode.singleton;

/**
 * @Description: 单例模式
 * 示例代码
 * 几种单例模式的写法
 * 参考文章：https://mp.weixin.qq.com/s/NAsx8lXjH4Ny3zkYeX-iog
 *          https://mp.weixin.qq.com/s/y-PErVpJRN7_u97C8kIjzQ
 * @Author LinJia
 * @Date 2019/9/26
 **/
public class SingleObject {

    //如果单例对象一开始就被new Singleton()主动构建，则不再需要判空操作，这种写法属于饿汉模式
    //饿汉模式
    private static class Singleton {

        //单例类必须自己创建自己的对象
        //单例对象
        private static Singleton singleton = new Singleton();

        //让构造方法为private私有的 这样就不会被实例化
        private Singleton() {

        }
        //提供唯一访问途径 单例静态工厂
        public static Singleton getInstance() {
            return singleton;
        }

        public void message() {
            System.out.println("饿汉单例模式");
        }

    }

    //如果单例初始值是null，还未构建，则构建单例对象并返回。这个写法属于单例模式当中的懒汉模式
    //懒汉模式
    private static class Singleton1 {
        //单例类必须自己创建自己的对象
        //单例对象
        private static Singleton1 singleton1 = null;

        //让构造方法为private私有的 这样就不会被实例化
        private Singleton1() {

        }

        //提供唯一访问途径  单例静态工厂
        public static Singleton1 getInstance() {
            if (singleton1 == null) {
                singleton1 = new Singleton1();
            }
            return singleton1;
        }

        public void message() {
            System.out.println("懒汉单例模式");
        }
    }

    //上述两种都不是线程安全的 如果在多线程情况下单例对象有可能被new两次


    //双重检查锁模式  有影藏漏洞
    private static class Singleton2 {

        //私有构造函数
        private Singleton2() {
        }

        //单例对象
        private static Singleton2 singleton2 = null;

        //单例静态工厂
        public static Singleton2 getInstance() {
            //双重检索机制
            if (singleton2 == null) {

                //为了防止new Singleton被执行多次，因此在new操作之前加上Synchronized 同步锁，锁住整个类（注意，这里不能使用对象锁）
                //同步锁
                synchronized (Singleton2.class){

                    //双重检索机制
                    if(singleton2 == null){
                        singleton2 = new Singleton2();
                    }
                }
            }
            return singleton2;
        }

        public void message() {
            System.out.println("双重检查锁模式");
        }
    }


    //上面的双重检查锁模式有一个隐藏的漏洞
    /**
    * 比如java中简单的一句 instance = new Singleton，会被编译器编译成如下JVM指令
    * memory =allocate();    //1：分配对象的内存空间
    ctorInstance(memory);  //2：初始化对象
    instance =memory;     //3：设置instance指向刚分配的内存地址

    但是这些指令顺序并非一成不变，有可能会经过JVM和CPU的优化，指令重排成下面的顺序：

    memory =allocate();    //1：分配对象的内存空间
    instance =memory;     //3：设置instance指向刚分配的内存地址
    ctorInstance(memory);  //2：初始化对象

    当线程A执行完1,3,时，instance对象还未完成初始化，但已经不再指向null。此时如果线程B抢占到CPU资源，
    执行  if（instance == null）的结果会是false，从而返回一个没有初始化完成的instance对象

    如何避免这一情况呢？我们需要在instance对象前面增加一个修饰符volatile

    **/

    //线程安全双重检查锁模式
    private static class Singleton3 {

        //私有构造函数
        private Singleton3() {
        }

        //单例对象
        //在单例对象前加volatile修饰符
        //volatile修饰符阻止了变量访问前后的指令重排，保证了指令执行的顺序,至于为什么要volatile关键字, 主要涉及到jdk指令重排
        private static volatile Singleton3 singleton3 = null;

        //单例静态工厂
        //本方式是对直接在方法上加锁的一个优化, 好处在于只有第一次初始化获取了锁
        //后续调用getInstance已经是无锁状态. 只是写法上稍微繁琐点
        public static Singleton3 getInstance() {
            //双重检索机制
            if (singleton3 == null) {

                //为了防止new Singleton被执行多次，因此在new操作之前加上Synchronized 同步锁，锁住整个类（注意，这里不能使用对象锁）
                //同步锁
                synchronized (Singleton3.class){

                    //双重检索机制
                    if(singleton3 == null){
                        singleton3 = new Singleton3();
                    }
                }
            }
            return singleton3;
        }

        public void message() {
            System.out.println("线程安全双重检查锁模式");
        }
    }


    //静态内部类单例

    private static int count = 0;

    private SingleObject(){

    }
    //静态内部类
    //该方式既解决了同步问题, 也解决了写法繁琐问题. 推荐使用改写法
    //缺点在于无法响应事件来重新初始化singleObject
    private static class SingletonHolder{
        public static final SingleObject singleObject = new SingleObject();
    }

    public static SingleObject getInstance() {
        return SingletonHolder.singleObject;
    }


    /**
     * @return void
     * @Description:测试
     * @Author LinJia
     * @Date 2020/7/3 9:56
     * @Param [args]
     **/
    public static void main(String[] args) {


        //测试静态内部类单例线程安全性
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(SingleObject.getInstance());
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(SingleObject.getInstance());
            }
        });

        thread.start();
        thread1.start();


        //单例类不能被new
//      SingleObject singleObject = new SingleObject();
        //调用单例静态工厂
        /*SingleObject.Singleton singleton = SingleObject.Singleton.getInstance();
        singleton.message();

        SingleObject.Singleton1 singleton1 = SingleObject.Singleton1.getInstance();
        singleton1.message();

        SingleObject.Singleton2 singleton2 = SingleObject.Singleton2.getInstance();
        singleton2.message();

        SingleObject.Singleton3 singleton3 = SingleObject.Singleton3.getInstance();
        singleton3.message();*/
    }


}
