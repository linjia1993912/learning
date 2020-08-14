package learning.design_mode.observer;

/**
 * @Description:被观察者
 * 主角类
 * @Author LinJia
 * @Date 2020/7/9
 **/
public class Hero extends Subject {

    void move(){
        System.out.println("主角向前移动");
        notifyObservers();
    }
}
