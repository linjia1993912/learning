package learning.basis.data_structure;

/**
 * @Description:基于链表实现栈
 * 单链表
 * @Author LinJia
 * @Date 2020/7/31
 **/
public class LinkStack<T> implements IStack<T> {

    //节点类
    private static class Node<T>{
        private T t;
        private Node<T> next;

        public Node(){

        }

        public Node(T t,Node<T> next){
            this.t = t;
            this.next = next;
        }
    }

    //栈顶元素
    private Node<T> top;
    //栈大小
    private int size;

    //初始化栈顶
    public LinkStack(){
        top = null;
        size = -1;
    }

    /**
     * @Description:入栈
     * 让top指向新创建的元素，新元素的next引用指向原来的栈顶元素
     * @Author LinJia
     * @Date 2020/7/31 14:23
     * @Param [data]
     * @return boolean
     **/
    @Override
    public boolean push(T data) {
        top = new Node<>(data,top);
        size++;
        return true;
    }

    /**
     * @Description:出栈
     * @Author LinJia
     * @Date 2020/7/31 14:23
     * @Param []
     * @return T
     **/
    @Override
    public T pop() {
        //得到栈顶元素
        Node<T> value = top;
        if(value != null){
            //让top引用指向原栈顶元素的下一个元素
            top = top.next;
            //释放原栈顶元素的next引用
            value.next = null;
            //栈的大小减1
            size--;
            return value.t;
        }
        return null;
    }

    /**
     * @Description:获取栈顶元素不出栈
     * 查看栈顶元素但不删除
     * @Author LinJia
     * @Date 2020/7/31 14:23
     * @Param []
     * @return T
     **/
    @Override
    public T peek() {
        if(!isEmpty()){
            return (T) top;
        }
        System.out.println("空栈");
        return null;
    }

    /**
     * @Description:判断栈是否为空
     * @Author LinJia
     * @Date 2020/7/31 14:48
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isEmpty() {
        return size == -1;
    }

    /**清空栈
     * @Description:
     * @Author LinJia
     * @Date 2020/7/31 14:48
     * @Param []
     * @return void
     **/
    @Override
    public void clear() {
        top.t = null;
        top.next = null;
        size = -1;
    }

    /**
     * @Description:当前栈的大小
     * @Author LinJia
     * @Date 2020/7/31 14:45
     * @Param []
     * @return int
     **/
    @Override
    public int length() {
        return size;
    }

    public static void main(String[] args) {
        LinkStack<Integer>linkStack = new LinkStack();
        for (int i = 0; i < 10 ; i++) {
            linkStack.push(i);
        }

        while (!linkStack.isEmpty()){
            System.out.println(linkStack.pop());
        }

        //linkStack.clear();

        System.out.println(linkStack.isEmpty());
    }
}
