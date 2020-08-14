package learning.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @Description: 装饰器父类
 * 你需要动态地为对象增加额外职责时 均可继承AbstractAspectManager
 * 装饰器模式 动态的为对象增加扩展功能
 * 可复用 灵活
 * @Author LinJia
 * @Date 2020/5/21
 **/
public abstract class AbstractAspectManager implements IAspectAPI {

    private IAspectAPI iAspectAPI;

    /**
     * @Description:在构造方法中，初始化IAspectAPI对象的引用
     * @Author LinJia
     * @Date 2020/5/21 15:25
     * @Param [iAspectAPI]
     * @return
     **/
    public AbstractAspectManager(IAspectAPI iAspectAPI){
        this.iAspectAPI = iAspectAPI;
    }

    /**
     * @Description:装饰器父类中直接转发"请求"至引用对象
     * @Author LinJia
     * @Date 2020/5/21 15:24
     * @Param [pjp, method]
     * @return java.lang.Object
     **/
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return iAspectAPI.doHandlerAspect(pjp,method);
    }

    protected abstract Object execute(ProceedingJoinPoint pjp, Method method)throws Throwable;
}
