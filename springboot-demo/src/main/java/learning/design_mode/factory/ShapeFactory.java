package learning.design_mode.factory;


/**
 * @Description:工厂类 工厂模式创建对象
 * @Author LinJia
 * @Date 2019/9/27
 **/
public class ShapeFactory {

    /**
     * @return learning.design_mode.factory.Shape
     * @Description:最简单工厂 扩展性差，如果要增加一个对象还要修改工厂方法
     * @Author LinJia
     * @Date 2020/7/14 10:28
     * @Param [shapType]
     **/
    public static Shape getShap(String shapType) {
        if (shapType.equalsIgnoreCase("Circle")) {
            return new Circle();
        }
        if (shapType.equalsIgnoreCase("Square")) {
            return new Square();
        }
        if (shapType.equalsIgnoreCase("Rectangle")) {
            return new Rectangle();
        }
        return null;
    }


    /**
     * @return T
     * @Description:通过反射来创建对象
     * @Author LinJia
     * @Date 2020/7/14 10:57
     * @Param [clazz]
     **/
    public static <T> T getInstance(Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * @return java.lang.Object
     * @Description:意义同上
     * @Author LinJia
     * @Date 2020/7/14 10:58
     * @Param [clazz]
     **/
    public static Object getInstance2(Class<? extends Shape> clazz) {
        Object obj = null;
        try {
            obj = Class.forName(clazz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * @return void
     * @Description:使用工厂类
     * @Author LinJia
     * @Date 2019/9/27 9:54
     * @Param [args]
     **/
    public static void main(String[] args) {
        /*Shape shape = ShapeFactory.getShap("Rectangle");
        shape.draw();

        Shape shape1 = ShapeFactory.getShap("Square");
        shape1.draw();

        Shape shape2 = ShapeFactory.getShap("Circle");
        shape2.draw();*/


        Shape shape = ShapeFactory.getInstance(Circle.class);
        shape.draw();

        Shape shape1 = ShapeFactory.getInstance(Square.class);
        shape1.draw();

        Shape shape2 = ShapeFactory.getInstance(Circle.class);
        shape2.draw();
    }
}
