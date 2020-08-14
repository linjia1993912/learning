package learning.design_mode.observer;

/**
 * @Description:观察者陷阱
 * @Author LinJia
 * @Date 2020/7/9
 **/
public class Trap implements Observer {

    @Override
    public void update() {
        if(inRange()){
            System.out.println("陷阱 困住主角！");
        }
    }

    private boolean inRange(){
        //判断主角是否在自己的影响范围内，这里忽略细节，直接返回true
        return true;
    }
}
