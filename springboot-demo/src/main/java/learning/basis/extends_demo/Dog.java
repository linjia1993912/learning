package learning.basis.extends_demo;

/**
 * @Description: Java基础-继承
 * 代码示例
 * 继承的特点
 * @Author LinJia
 * @Date 2020/7/6
 **/
public class Dog extends Animal {

    public void move(){
        super.move(); // 应用super类的方法
        System.out.println("狗可以跑和走");
    }


    public static void main(String[] args) {
        Animal b = new Dog(); // Dog 对象
        b.move(); //执行 Dog类的方法
    }

}


