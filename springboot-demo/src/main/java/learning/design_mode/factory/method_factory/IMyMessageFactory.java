package learning.design_mode.factory.method_factory;

/**
 * @Description:工厂方法模式_工厂接口
 * @Author LinJia
 * @Date 2020/7/14
 **/
public interface IMyMessageFactory {
    IMyMessage createMessage(String messageType);
}
