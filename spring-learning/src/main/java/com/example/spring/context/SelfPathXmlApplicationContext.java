package com.example.spring.context;

import com.example.annotation.SelfAutowired;
import com.example.annotation.SelfService;
import com.example.util.ClassParseUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:自定义bean管理器
 * 模拟spring的bean容器类
 * 这段代码的作用其实就是模拟在spring启动加载的时候,通过这个类去初始化一个bean 的容器管理类，所有的bean的信息解析和保存都会在这个类里面进行
 * 相当于ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
 * @Author LinJia
 * @Date 2020/8/18
 **/
public class SelfPathXmlApplicationContext {

    private String packageName;

    //封装所有的bean容器
    private ConcurrentHashMap<String,Object> beans = null;

    /**该类被创建出来的时候加载
     * @Description:
     * @Author LinJia
     * @Date 2020/8/18 15:13
     * @Param [packageName]
     * @return
     **/
    public SelfPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        //初始化IOC容器
        beans = new ConcurrentHashMap<>();
        //装载bean、创建对象
        initBeans();
        initEntryField();
    }

    /**
     * @Description:初始化bean的实例对象的所有属性
     * @Author LinJia
     * @Date 2020/8/18 15:16
     * @Param []
     * @return void
     **/
    private void initEntryField() throws Exception {
        // 1.遍历所有的bean容器对象
        for (Map.Entry<String,Object> map :beans.entrySet()){
            // 2.判断属性上面是否有加注解
            Object object = map.getValue();
            attriAssign(object);
        }
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
        ConcurrentHashMap<String, Object> classExisAnnotation = findClassExisAnnotation(classes);
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
    public ConcurrentHashMap<String, Object> findClassExisAnnotation(List<Class<?>> classes) throws Exception {
        for (Class<?> classInfo : classes) {
            // 判断类上是否有注解 [获取自定义的service注解]
            //相当于把包含@Service注解的类都装载到IOC容器里，使用的时候直接通过getBean()获取类的实例化对象
            SelfService annotation = classInfo.getAnnotation(SelfService.class);
            if (annotation != null) {
                // 获取当前类的类名
                String className = classInfo.getSimpleName();
                String beanId = toLowerCaseFirstOne(className);
                Object newInstance = newInstance(classInfo);
                beans.put(beanId, newInstance);
            }

        }
        return beans;
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))){
            return s;
        }
        else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * @Description:通过class名称获取类的实例化对象
     * @Author LinJia
     * @Date 2020/8/18 15:43
     * @Param [classInfo]
     * @return java.lang.Object
     **/
    public Object newInstance(Class<?> classInfo)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return classInfo.newInstance();
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
        Class<? extends Object> classInfo = object.getClass();
        Field[] declaredFields = classInfo.getDeclaredFields();

        // 2.判断当前类属性是否存在注解
        for (Field field : declaredFields){
            //获取注解
            SelfAutowired annotation = field.getAnnotation(SelfAutowired.class);
            if(annotation != null){
                // 获取属性名称
                String beanId = field.getName();
                Object bean = getBean(beanId);
                if(bean != null){
                    // 3.默认使用属性名称，查找bean容器对象 1参数 当前对象 2参数给属性赋值
                    field.setAccessible(true);// 允许访问私有属性
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
        Object object = beans.get(beanId);
        return object;
    }

}
