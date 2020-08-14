package learning.basis.data_structure;

/**
 * @Description:基于数组实现栈
 * @Author: linJia
 * @Date: 2020/7/31 9:56
 */
public interface IStack<T> {
    /**
     * 判断栈是否为空
     */
    boolean isEmpty();
    /**
     * 清空栈
     */
    void clear();
    /**
     * 栈的长度
     */
    int length();
    /**
     * 数据入栈
     */
    boolean push(T data);
    /**
     * 数据出栈
     */
    T pop();
    /**
     * 获取栈顶元素 不出栈
     */
    T peek();
}
