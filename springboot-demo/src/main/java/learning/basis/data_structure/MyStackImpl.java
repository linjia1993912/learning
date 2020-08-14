package learning.basis.data_structure;

import java.util.Arrays;

/**
 * @Description:栈的具体实现
 * 采用数组实现栈,可以动态扩容
 * 栈：LIFO（后进先出），自己实现一个栈，
 * 要求这个栈具有push()、pop()（返回栈顶元素并出栈）、peek() （返回栈顶元素不出栈）、isEmpty()这些基本的方法。
 * @Author LinJia
 * @Date 2020/7/31
 **/
public class MyStackImpl<T> implements IStack<T> {

    //默认初始容量
    private static final int DEFAULT_CAPACITY = 10;

    //实现栈的数组
    private Object[] stackData;

    //栈下标,每次入栈出栈都记录
    private int top;

    //构造
    public MyStackImpl(int maxSize){
        stackData = new Object[maxSize];
    }

    //无参构造，栈大小默认DEFAULT_CAPACITY
    public MyStackImpl(){
        //初始化栈的时候由于栈内没有元素，栈顶下标设为-1
        top = -1;
        stackData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * @Description:入栈
     * @Author LinJia
     * @Date 2020/7/31 10:07
     * @Param [data]
     * @return boolean
     **/
    @Override
    public boolean push(T data) {
        //每次入栈之前先判断栈的容量是否够用，如果不够用就用Arrays.copyOf()进行扩容
        //top+1 = 新元素需要存储的下标
        top++;
        expandCapacity(top);
        //下标+1,如果是第一个元素下标从0开始保存
        stackData[top] = data;
        return true;
    }

    /**
     * @Description:返回栈顶元素 不出栈
     * @Author LinJia
     * @Date 2020/7/31 10:25
     * @Param []
     * @return T
     **/
    @Override
    public T peek() {
        T t = null;
        if (top > -1) {
            //只获取值，下标不减
            t = (T) stackData[top];
        }
        return t;
    }

    /**
     * @Description:出栈
     * @Author LinJia
     * @Date 2020/7/31 10:26
     * @Param []
     * @return T
     **/
    @Override
    public T pop() {
        T t = peek();
        if (top > -1) {
            stackData[top--] = null;
        }
        return t;
    }

    /**
     * @Description:栈扩容
     * @Author LinJia
     * @Date 2020/7/31 10:07
     * @Param [size]
     * @return void
     **/
    private void expandCapacity(int size){
        //判断新元素入栈需要的长度接近数组的长度就扩容
        int len = length();
        if (size >= len){
            //需要扩容 每次扩大50%;
            size = size * 3 / 2;
            System.out.println("扩大容量:"+size);
            stackData = Arrays.copyOf(stackData,size);
        }
    }

    /**
     * @Description:判断是否为空
     * @Author LinJia
     * @Date 2020/7/31 10:27
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * @Description:清空栈
     * @Author LinJia
     * @Date 2020/7/31 11:12
     * @Param []
     * @return void
     **/
    @Override
    public void clear() {
        stackData = null;
    }

    /**
     * @Description:获取栈的长度
     * @Author LinJia
     * @Date 2020/7/31 11:12
     * @Param []
     * @return int
     **/
    @Override
    public int length() {
        return stackData.length;
    }


    public static void main(String[] args) {

        IStack myArrayStack = new MyStackImpl();

        for (int i = 1; i <= 30; i++) {
            myArrayStack.push(i);
        }

        while (!myArrayStack.isEmpty()){
            System.out.println(myArrayStack.pop());
        }

    }

}
