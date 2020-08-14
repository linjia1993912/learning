package learning.basis.data_structure;



/**
 * @Description:手写HashMap
 *
 * 仿照JDK1.7实现
 * @Author LinJia
 * @Date 2020/8/4
 **/
public class MyHashMap<K,V> {

    //JDK1.7HashMap底层存储数组为Entry，单链表形式
    //1.8变为Node<K,V>[] table; 实际还是Entry
    private Entry<K,V>[] table;

    //默认初始容量
    private final int CAPACITRY = 8;

    //数组大小
    private int size =  0;

    //单链表
    class Entry<K,V>{
        public K k;
        public V v;
        public Entry<K,V> next;
        public Entry(K k,V v,Entry<K,V> next){
            this.k=k;
            this.v=v;
            this.next=next;
        }
    }

    //构造
    public MyHashMap(){
        //初始化数组，容量刚开始为8
        this.table = new Entry[CAPACITRY];
    }

    public int getSize(){
        //计算size值我们可能会想到遍历数组，但是这样性能不高
        //HashMap的这边是定义一个属性size，每次增加元素，删除元素，对size加减就行，然后返回就行
        return size;
    }

    public V get(K key){
        //获取到传进来的key的hash值
        int hash = key.hashCode();
        //用哈希值对数组的容量取模，获得数组的下标值。
        int index = hash % table.length;

        //相同key,value覆盖的操作，返回value
        //先计算key存储在数组的下标，通过下标拿到Entry
        for (Entry<K,V> entry = table[index];entry!=null;entry = entry.next){
            if(key.equals(entry.k)){
                return entry.v;
            }
        }

        return null;
    }

    public V put(K key,V value){
        //获取到传进来的key的hash值
        //HashMap哈希值的获取进行了很多右移和异或的操作（目的：让高四位参与进来运算，让元素分散得更散列，
        // 链表的缺点就是查找慢，数组更散列那么get就更方便）
        int hash = key.hashCode();
        //用哈希值对数组的容量取模，获得数组的下标值。
        //HashMap在这边是用 与 操作来获得索引值（二次方数和与操作配合使用）
        int index = hash % table.length;

        //相同key,value覆盖的操作,他返回的是老的value值
        //这一步就是Map的特性，Key相同覆盖Value
        for (Entry<K,V> entry = table[index];entry != null;entry = entry.next){
            if(key.equals(entry.k)){
                V oldValue = entry.v;
                entry.v = value;
                return oldValue;
            }
        }

        //添加元素
        addEntry(key, value, index);

        return null;
    }


    public void remove(K key){
        int hash = key.hashCode();
        int index = hash % table.length;
        for (Entry<K,V> entry = table[index];entry != null;entry = entry.next){
            if(key.equals(entry.k)){
                //判断是否是链表形式存储
                if(entry.next!=null){
                    //如果是链表，则把要删除的元素下级链表往上移
                    table[index] = new Entry<>(entry.next.k,entry.next.v,entry.next.next);
                }else if(entry.next == null){

                }
                else{
                    table[index] = null;
                }
            }
        }
        size--;
    }

    private void addEntry(K key, V value, Integer index) {
        //元素进来的时候，让他先指向原来的数组上的值，然后再把当前数组赋值给我们新的元素，这样就达到了插在头部的操作。
        //把新元素放在头部，原来的table指向next,形成一个链表
        table[index]=new Entry(key,value,table[index]);

        size++;
    }

    public static void main(String[] args) {
        MyHashMap<String,Integer> myHashMap=new MyHashMap<>();
        for(int i=0;i<10;i++){
            myHashMap.put("测试"+i,i);
        }

        //经过测试
        //HashMap设置的容量为8，当put 10个数据进去，就转成链表存储了


        System.out.println(myHashMap.get("测试9"));

        myHashMap.remove("测试1");

        System.out.println(myHashMap.getSize());

    }

}
