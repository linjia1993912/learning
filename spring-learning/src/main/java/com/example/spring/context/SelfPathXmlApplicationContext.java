package com.example.spring.context;

import com.example.annotation.SelfAutowired;
import com.example.annotation.SelfService;
import com.example.util.ClassParseUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:模拟spring ioc 依赖注入，并支持循环依赖
 * 模拟spring的bean容器类
 * 这段代码的作用其实就是模拟在spring启动加载的时候,通过这个类去初始化一个bean的容器管理类，所有的bean的信息解析和保存都会在这个类里面
 * 相当于ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
 * @Author LinJia
 * @Date 2020/8/18
 **/
public class SelfPathXmlApplicationContext {

    private String packageName;

    //放置创建好的bean容器
    private Map<String,Object> beans = null;

    /**
     * @Description: 构造
     * @Author LinJia
     * @Date 2020/8/18 15:13
     * @Param [packageName]
     * @return
     **/
    public SelfPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        //初始化IOC容器
        //Spring源码里容量是256
        beans = new ConcurrentHashMap<>(256);
        //装载bean、实例化对象
        initBeans();
        //注入
        initEntryField();
    }

    /**
     * @Description:获取扫描包下面的所有bean
     * @Author LinJia
     * @Date 2020/8/18 15:27
     * @Param []
     * @return void
     **/
    private void initBeans() throws Exception {
        // 1.使用java的反射机制扫包,[获取当前包下所有的类]
        ClassParseUtil classParseUtil = new ClassParseUtil();
        List<Class<?>> classes = classParseUtil.getClasses(packageName);
        // 2、判断类上面是否存在注入的bean的注解
        Map<String, Object> classExisAnnotation = findClassExisAnnotation(classes);
        if (classExisAnnotation == null || classExisAnnotation.isEmpty()) {
            throw new Exception("该包下没有任何类加上注解");
        }
    }

    /**
     * @Description:判断类上是否存在注入自定义的bean的注解
     * @Author LinJia
     * @Date 2020/8/18 15:42
     * @Param [classes]
     * @return java.util.concurrent.ConcurrentHashMap<java.lang.String,java.lang.Object>
     **/
    public Map<String, Object> findClassExisAnnotation(List<Class<?>> classes) throws Exception {
        for (Class<?> classInfo : classes) {
            //判断类上是否有注解 [获取自定义的service注解]
            //相当于把包含@Service注解的类都装载到IOC容器里，使用的时候直接通过getBean()获取类的实例化对象
            SelfService annotation = classInfo.getAnnotation(SelfService.class);
            if (annotation != null) {
                // 获取当前类的类名
                String className = classInfo.getSimpleName();
                //转小写，遵循类命名规范
                String beanId = toLowerCaseFirstOne(className);
                Object newInstance = newInstance(classInfo);
                beans.put(beanId, newInstance);
            }

        }
        return beans;
    }

    /**
     * @Description:初始化bean的实例对象的所有属性
     * @Author LinJia
     * @Date 2020/8/18 15:16
     * @Param []
     * @return void
     **/
    private void initEntryField() throws Exception {
        // 1.遍历容器中所有的bean
        for (Map.Entry<String,Object> map :beans.entrySet()){
            // 2.判断属性上面是否有加注解
            Object object = map.getValue();
            attriAssign(object);
        }
    }

    /**
     * @Description:实例化对象
     * @Author LinJia
     * @Date 2020/8/18 15:43
     * @Param [classInfo]
     * @return java.lang.Object
     **/
    public Object newInstance(Class<?> classInfo)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return classInfo.getDeclaredConstructor().newInstance();
    }

    /**
     * @Description:依赖注入注解原理
     * @Author LinJia
     * @Date 2020/8/18 15:19
     * @Param [object]
     * @return void
     **/
    public void attriAssign(Object object) throws Exception {
        // 1.使用反射机制,获取当前类的所有属性
        Class<?> classInfo = object.getClass();
        Field[] declaredFields = classInfo.getDeclaredFields();

        // 2.判断当前类属性是否存在注解
        for (Field field : declaredFields){
            //获取注解
            SelfAutowired annotation = field.getAnnotation(SelfAutowired.class);
            if(annotation != null){
                //获取属性名称 获取需要注入的bean
                String beanId = field.getName();
                //从beans容器中获取对象
                Object bean = getBean(beanId);
                if(bean != null){
                    field.setAccessible(true);// 允许访问私有属性
                    //容器中存在bean则直接注入
                    field.set(object,bean);
                }
            }
        }
    }

    /**
     * @Description:根据beanId获取bean名称
     * @Author LinJia
     * @Date 2020/8/18 15:24
     * @Param [beanId]
     * @return java.lang.Object
     **/
    public Object getBean(String beanId) throws Exception {
        if(StringUtils.isEmpty(beanId)){
            throw new Exception("beanId参数不能为空");
        }
        // 1.从spring容器获取bean
        return beans.get(beanId);
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))){
            return s;
        }
        else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
