package learning.design_mode.observer;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/7/9
 **/
public class Client {

    //测试
    public static void main(String[] args) {
        //初始化对象
        Hero hero = new Hero();
        Monster monster = new Monster();
        Trap trap = new Trap();
        Treasure treasure = new Treasure();
        //注册观察者
        hero.attachObserver(monster);
        hero.attachObserver(trap);
        hero.attachObserver(treasure);
        //移动事件
        hero.move();
    }

}
