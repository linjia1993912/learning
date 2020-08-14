package learning.design_mode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:被观察者抽象类
 * 也可以创建一个接口  去定义这三个事件，Subject实现该接口即可
 * @Author LinJia
 * @Date 2020/7/9
 **/
public abstract class Subject {

    //存储着已注册的观察者，当事件发生时，会通知列表中的所有观察者
    //OberverList所依赖的是抽象的Observer接口，这样就避免了观察者与被观察者的紧耦合
    private List<Observer> observerList = new ArrayList<Observer>();

    /**
     * @Description:注册通知
     * @Author LinJia
     * @Date 2020/7/10 11:07
     * @Param [observer]
     * @return void
     **/
    public void attachObserver(Observer observer) {
        observerList.add(observer);
    }

    /**
     * @Description:删除通知
     * @Author LinJia
     * @Date 2020/7/10 11:07
     * @Param [observer]
     * @return void
     **/
    public void detachObserver(Observer observer){
        observerList.remove(observer);
    }

    /**
     * @Description:通知列表中已注册的观察者
     * @Author LinJia
     * @Date 2020/7/9 17:20
     * @Param []
     * @return void
     **/
    public void notifyObservers(){
        for (Observer observer: observerList){
            observer.update();
        }
    }

}
