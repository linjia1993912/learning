package learning.basis.proxy.dynamicProxy;

/**
 * @Description:被代理对象
 * 目标对象必须要实现接口
 * @Author LinJia
 * @Date 2020/8/26
 **/
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("模拟：保存用户！");
    }

    @Override
    public void find() {
        System.out.println("查询");
    }
}
