package learning.design_mode.factory;

import learning.design_mode.factory.Shape;

/**
 * @Description:工厂接口实现类
 * @Author LinJia
 * @Date 2019/9/27
 **/
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("111");
    }
}
