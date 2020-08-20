package com.example.singletonbeanregistry;

import com.example.util.ClassParseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 模拟处理循环依赖
 *
 *  将指定的一些类实例为单例
    类中的字段也都实例为单例
    支持循环依赖

 * @Author LinJia
 * @Date 2020/8/19
 **/
public class IocTest {

    //放置创建好的bean容器
    private static ConcurrentHashMap<String,Object> cachMap = null;

    public IocTest(){
        //初始化容器
        //Spring源码里一级缓存里容量是256
        cachMap = new ConcurrentHashMap<>(256);

        //也可以在这里初始化所有的bean,并且注入依赖对象
        //iocBean();
    }

    //测试
    public static void main(String[] args) {
        IocTest testMain = new IocTest();

        testMain.iocBean();

        //判断循环依赖注入的对象是否相同
        System.out.println(getBean(B.class).getA() == getBean(A.class));
        System.out.println(getBean(A.class).getB() == getBean(B.class));
    }

    public void iocBean(){
        try {
            //扫描出A和B对象
            ClassParseUtil classParseUtil = new ClassParseUtil();
            List<Class<?>> classes = classParseUtil.getClasses("com.example.singletonbeanregistry");
            //初始化所有的bean,并且注入依赖对象
            for (Class aClass:classes){
                getBean(aClass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static <T> T getBean(Class<T> tClass){
        try{
            //小写类名,遵循类名命名规范
            String beanName = tClass.getSimpleName().toLowerCase();

            // 如果容器已经创建了bean，则直接返回
            if(cachMap.containsKey(beanName)){
                return (T) cachMap.get(beanName);
            }

            //如果没创建，则将对象实例化
            Object object = tClass.getDeclaredConstructor().newInstance();
            //放入容器
            cachMap.put(beanName,object);

            //获取所有类字段属性，创建并注入到当前bean中
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field:fields){
                //获取每个字段注解，判断是否需要注入 @Autowired
                Autowired autowired = field.getAnnotation(Autowired.class);

                if(autowired != null){
                    field.setAccessible(true);
                    //获取需要注入的bean名称
                    String fieldBeanName = field.getName();

                    // 如果需要注入的bean，已经在缓存Map中，那么把缓存Map中的值注入到该field即可
                    field.set(object, cachMap.containsKey(fieldBeanName)
                            ? cachMap.get(fieldBeanName) : getBean(field.getType()));
                }
            }
            //注入完成 返回
            return (T) object;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
