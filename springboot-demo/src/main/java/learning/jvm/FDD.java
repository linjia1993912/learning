package learning.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Description:演示类加载器加载过程
 * @Author LinJia
 * @Date 2020/7/8
 **/
public class FDD {

    static {
        System.out.println("静态代码块");
    }


    static class FDDloaderTest {

        public static void main(String[] args) {

            try {
                ClassLoader loader = FDD.class.getClassLoader();
                System.out.println(loader);

                //使用ClassLoader.loadClass()来加载类 不会执行初始化块
                loader.loadClass("learning.jvm.FDD");

                //使用Class.forName()来加载类，默认会执行初始化块
                Class.forName("learning.jvm.FDD");

                //使用Class.forName()来加载类，指定ClassLoader，初始化时不执行静态块
                Class.forName("learning.jvm.FDD", false, loader);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }


    //自定义类加载器
    //（1）创建一个类继承ClassLoader抽象类
    //（2）重写findClass()方法
    //（3）在findClass()方法中调用defineClass()

    static class MyClassLoader extends ClassLoader {
        private String libPath;

        public MyClassLoader(String path) {
            libPath = path;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String fileName = getFileName(name);
            File file = new File(libPath, fileName);
            try {
                FileInputStream is = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = 0;
                try {
                    while ((len = is.read()) != -1) {
                        bos.write(len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] data = bos.toByteArray();
                is.close();
                bos.close();
                return defineClass(name, data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return super.findClass(name);
        }

        //获取要加载 的class文件名
        private String getFileName(String name) {
            int index = name.lastIndexOf('.');
            if (index == -1) {
                return name + ".class";
            } else {
                return name.substring(index + 1) + ".class";
            }
        }

        //使用
        public static void main(String[] args) {
            try{
                MyClassLoader diskLoader = new MyClassLoader("E:\\class");
                //反射
                //加载class文件，注意是包名加类
                Class<?> test = diskLoader.loadClass("learning.jvm.Test");
                Method [] methods = test.getDeclaredMethods();

                System.out.println(methods);

                //实例化Test
                Test test1 = (Test) test.newInstance();
                test1.test();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
    }
}
