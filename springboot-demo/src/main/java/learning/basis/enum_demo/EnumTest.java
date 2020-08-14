package learning.basis.enum_demo;


/**
 * @Description:枚举定义和使用
 * 在JDK1.5 之前，在Java中定义常量都是public static final TYPE a; 这样的形式。
 * 有了枚举，你可以将有关联关系的常量组织起来，使代码更加易读、安全，并且还可以使用枚举提供的方法
 * @Author LinJia
 * @Date 2020/8/3
 **/
public enum EnumTest {

    //枚举的应用场景
    //管理常量、状态机等

    NONE(1001, "未处理"),// 未处理
    PROCESSING(1002,"处理中"), //处理中
    SUCCESS(1003,"成功"), // 成功
    FAIL(1004,"失败"); // 失败
    // 如果要为enum定义方法，那么必须在enum的最后一个实例尾部添加一个分号
    //此外，在enum中，必须先定义实例，不能将字段或方法定义在实例前面。否则，编译器会报错

    private int code;
    private String value;


    //构造方法：enum的构造方法只能被声明为private权限或不声明权限
    EnumTest(Integer code, String value) {
        this.code = code;
        this.value = value;
    }


    //switch 状态机
    //我们经常使用switch语句来写状态机。JDK7以后，switch已经支持 int、char、String、enum 类型的参数。
    // 这几种类型的参数比较起来，使用枚举的switch代码更具有可读性。
    public static String getTrafficInstruct(EnumTest enumTest) {
        String instruct = "";
        switch (enumTest) {
            case NONE:
                instruct = "未处理";
                break;
            case PROCESSING:
                instruct = "处理中";
                break;
            case SUCCESS:
                instruct = "成功";
                break;
            case FAIL:
                instruct = "失败";
                break;
        }
        return instruct;
    }


    //枚举可以添加普通方法、静态方法、抽象方法、构造方法

    /**
     * @Description:根据code获取成员标识
     * @Author LinJia
     * @Date 2020/8/3 14:16
     * @Param [code]
     * @return learning.basis.enum_demo.EnumTest
     **/
    public static EnumTest getStatusEnum(int code) {
        EnumTest statusEnum = NONE;
        //以数组形式返回枚举类型的所有成员
        EnumTest[] values = EnumTest.values();
        for (EnumTest value : values) {
            if (value.code == code) {
                statusEnum = value;
                break;
            }
        }
        return statusEnum;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    //静态方法
    public static void main(String[] args) {
        System.out.println(EnumTest.NONE.getCode());

        System.out.println(EnumTest.NONE.getValue());

        System.out.println(EnumTest.getStatusEnum(1004));

        System.out.println(EnumTest.getTrafficInstruct(EnumTest.PROCESSING));
    }

}
