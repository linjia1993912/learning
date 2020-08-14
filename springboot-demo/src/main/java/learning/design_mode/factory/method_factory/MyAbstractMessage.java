package learning.design_mode.factory.method_factory;

import java.util.Map;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/7/14
 **/
public abstract class MyAbstractMessage implements IMyMessage {

    // 这里可以理解为生产产品所需要的原材料库。最好是个自定义的对象，这里为了不引起误解使用Map。
    private Map<String, Object> messageParam;

    @Override
    public Map<String, Object> getMessageParam() {
        return messageParam;
    }
    @Override
    public void setMessageParam(Map<String, Object> messageParam) {
        this.messageParam = messageParam;
    }
}
