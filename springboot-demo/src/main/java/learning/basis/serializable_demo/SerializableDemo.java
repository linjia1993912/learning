package learning.basis.serializable_demo;

import java.io.*;

/**
 * @Description:序列化示例代码
 * Serializable标记接口 不需要实现任何方法
 * 说明该类是可序列化的
 * @Author LinJia
 * @Date 2020/7/7
 **/
public class SerializableDemo implements Serializable {

    private static final long serialVersionUID = -7761003238702613748L;

    private String name;
    private int age;

    public SerializableDemo(String name,int age){
        this.name = name;
        this.age = age;
    }


    public static void main(String[] args) {

        //序列化
        /*try (//创建一个ObjectOutputStream输出流
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("e://object.txt"))) {
            //将对象序列化到文件s
            SerializableDemo person = new SerializableDemo("测试", 23);
            oos.writeObject(person);
            System.out.println("序列化完成");
        } catch (Exception e) {
            e.printStackTrace();
        }*/



        //反序列化
        try{
            //创建一个ObjectInputStream输入流
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("e://object.txt")) ;
            //调用ObjectInputStream对象的readObject()得到序列化的对象
            SerializableDemo serializableDemo = (SerializableDemo) objectInputStream.readObject();
            System.out.println("反序列化完成:"+ serializableDemo.name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
