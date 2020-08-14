package learning.basis.proxy;

/**
 * @Description: 代理模式公共接口
 * 这个接口就是学生（被代理类），和班长（代理类）的公共接口，他们都有上交班费的行为。这样，学生上交班费就可以让班长来代理执行
 * @Author LinJia
 * @Date 2020/7/9
 **/
public interface Person {
    //上交班费
    void giveMoney();
}
