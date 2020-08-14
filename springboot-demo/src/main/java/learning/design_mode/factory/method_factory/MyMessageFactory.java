package learning.design_mode.factory.method_factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:工厂方法模式_工厂实现
 * @Author LinJia
 * @Date 2020/7/14
 **/
public class MyMessageFactory implements IMyMessageFactory {

    @Override
    public IMyMessage createMessage(String messageType) {
        // 这里的方式是：消费者知道自己想要什么产品；若生产何种产品完全由工厂决定，则这里不应该传入控制生产的参数。
        IMyMessage myMessage;
        Map<String, Object> messageParam = new HashMap<String, Object>();
        // 根据某些条件去选择究竟创建哪一个具体的实现对象，条件可以传入的，也可以从其它途径获取。
        // sms
        if ("SMS".equals(messageType)) {
            myMessage = new MyMessageSms();
            messageParam.put("PHONENUM", "123456789");
        } else if ("OA".equals(messageType)) {
            // OA待办
            myMessage = new MyMessageOaTodo();
            messageParam.put("OAUSERNAME", "testUser");
        } else if ("EMAIL".equals(messageType)) {
            // email
            myMessage = new MyMessageEmail();
            messageParam.put("EMAIL", "test@test.com");
        } else {
            // 默认生产email这个产品
            myMessage = new MyMessageEmail();
            messageParam.put("EMAIL", "test@test.com");
        }
        myMessage.setMessageParam(messageParam);
        return myMessage;
    }


    //为了减少创建类  这里使用内部类 方便快捷

    public class MyMessageEmail extends MyAbstractMessage {
        @Override
        public void sendMesage() throws Exception {
            // TODO Auto-generated method stub
            if (null == getMessageParam() || null == getMessageParam().get("EMAIL")
                    || "".equals(getMessageParam().get("EMAIL"))) {
                throw new Exception("发送短信,需要传入EMAIL参数");// 为了简单起见异常也不自定义了
            }// 另外邮件内容，以及其他各种协议参数等等都要处理

            System.out.println("我是邮件，发送通知给" + getMessageParam().get("EMAIL"));
        }
    }


    public class MyMessageOaTodo extends MyAbstractMessage {
        @Override
        public void sendMesage() throws Exception {
            // TODO Auto-generated method stub
            if (null == getMessageParam()
                    || null == getMessageParam().get("OAUSERNAME")
                    || "".equals(getMessageParam().get("OAUSERNAME"))) {
                throw new Exception("发送OA待办,需要传入OAUSERNAME参数");// 为了简单起见异常也不自定义了
            }// 这里的参数需求就比较多了不一一处理了

            System.out
                    .println("我是OA待办，发送通知给" + getMessageParam().get("OAUSERNAME"));
        }
    }


    public class MyMessageSms extends MyAbstractMessage {
        @Override
        public void sendMesage() throws Exception {
            // TODO Auto-generated method stub
            if (null == getMessageParam()
                    || null == getMessageParam().get("PHONENUM")
                    || "".equals(getMessageParam().get("PHONENUM"))) {
                throw new Exception("发送短信,需要传入PHONENUM参数");// 为了简单起见异常也不自定义了
            }// 另外短信信息，以及其他各种协议参数等等都要处理

            System.out.println("我是短信，发送通知给" + getMessageParam().get("PHONENUM"));
        }
    }


}
