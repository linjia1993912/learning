package learning.design_mode.factory.method_factory;

import java.util.Map;

/**
 * @Description:工厂方法模式_产品接口
 * @Author: linJia
 * @Date: 2020/7/14 10:36
 */
public interface IMyMessage {

    Map<String, Object> getMessageParam();

    void setMessageParam(Map<String, Object> messageParam);

    // 发送通知/消息
    void sendMesage() throws Exception;
}
