package learning.basis.linkedlist;

/**
 * @Description:单链表实现方式
 * @Author LinJia
 * @Date 2020/7/31
 **/
public class LinkedListSingle2<T> {

    private class Node<T>{
        private T t;
        private Node<T> next;

        public Node(T t){
            this.t = t;
        }

        public Node(){
        }
    }

    //链表头节点
    private Node<T> head;

    public LinkedListSingle2(){
        this.head = new Node<>();
    }

    /**
     * @Description:添加元素
     * @Author LinJia
     * @Date 2020/7/31 15:27
     * @Param [t]
     * @return void
     **/
    public void addLinkedList(T t){
        Node<T> node = new Node<>(t);
        //头结点不存在，新结点成为头结点
        if(head == null){
            head = node;
            return;
        }
        //新结点next直接指向当前头结点
        node.next = this.head;
        //新结点成为新的头结点
        this.head = node;
    }

    /**
     * 添加结点至链表尾部
     *
     * @param value
     */
    public void addTailNode(T value) {
        Node newNode = new Node(value);
        //头结点不存在，新结点成为头结点
        if (head == null) {
            head = newNode;
            return;
        }
        //找到最后一个结点
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        //新结点插入到链表尾部
        last.next = newNode;
    }

    /**
     * @Description:从头到尾打印链表
     * @Author LinJia
     * @Date 2020/7/31 15:29
     * @Param []
     * @return void
     **/
    public void printListHeadFrom() {
        while (head.next != null) {
            System.out.println("{" + head.next.t + "}");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        LinkedListSingle2 linkedListSingle2 = new LinkedListSingle2();

        for (int i = 0; i < 10; i++) {
            linkedListSingle2.addTailNode(i);
        }

        linkedListSingle2.printListHeadFrom();

    }
}
