package learning.basis.linkedlist;


import java.util.Stack;

/**
 * @Description:Java实现链表
 * 单链表
 *
 * 建一个节点类，其中节点类包含两个部分，第一个是数据域（你到时候要往节点里面储存的信息），
 * 第二个是引用域（相当于指针，单向链表有一个指针，指向下一个节点；双向链表有两个指针，分别指向下一个和上一个节点）
 * @Author LinJia
 * @Date 2020/7/28
 **/
public class LinkedListSingle {

    //节点类
    private class ListNode{
        private Object data;
        private ListNode next = null;

        ListNode(){
            data = null;
        }

        ListNode(Object data){
            this.data = data;
        }
    }

    //头节点
    private ListNode head;

    //临时节点
    private ListNode temp;

    //初始化链表，生成一个无数据的头节点
    LinkedListSingle(){
        head = new ListNode();
    }

    //给链表添加节点
    public void addNode(Object data){
        ListNode listNode = new ListNode(data);
        temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = listNode;
    }

    //返回链表的长度
    public int getLength(){
        temp = head;
        int length = 0;
        while (temp.next != null){
            length ++;
            temp = temp.next;
        }

        return length;
    }

    //增加节点到链表指定的位置
    public void addNodeByIndex(int index, Object data) {
        if (index < 1 || index > getLength() + 1) {
            System.out.println("插入的位置不合法。");
            return;
        }
        int count = 1; //记录遍历的位置
        ListNode node = new ListNode(data);
        temp = head;
        while (temp.next != null) {
            if (index == count++) {
                node.next = temp.next;
                temp.next = node;
                return;
            }
            temp = temp.next;
        }
    }

    //删除链表指定位置的节点
    public void deleteByIndex(int index) {
        if (index < 1 || index > getLength()) {
            System.out.println("插入的位置不合法。");
            return;
        }
        int count = 1;//记录位置
        temp = head;
        while (temp.next != null) {
            if (index == count++) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    //从头到尾打印链表的数据
    public void printListHeadFrom() {
        temp = head;
        while (temp.next != null) {
            System.out.println("{" + temp.next.data + "}");
            temp = temp.next;
        }
    }

    //从尾到头打印
    public void printListFromTailToHead(){
        temp = head;
        //利用栈 后进先出
        Stack stack = new Stack<>();
        while (temp.next != null) {
            stack.push(temp.next.data);
            temp = temp.next;
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        LinkedListSingle linkedList_single = new LinkedListSingle();
        linkedList_single.addNode("测试1");
        linkedList_single.addNode("测试2");

        System.out.println(linkedList_single.getLength());

        linkedList_single.printListHeadFrom();

        linkedList_single.printListFromTailToHead();

    }

}
