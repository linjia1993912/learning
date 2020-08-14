package learning.basis.generics;

/**
 * @Description:
 * 泛型类
 * Java库中 E表示集合的元素类型，K 和 V分别表示表的关键字与值的类型
 * T（需要时还可以用临近的字母 U 和 S）表示“任意类型”
 * @Author LinJia
 * @Date 2020/7/21
 **/
public class Pair<T> {

    private T name;
    private T price;

    public Pair(){

    }

    public Pair(T name,T price){
        this.name = name;
        this.price = price;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T getPrice() {
        return price;
    }

    public void setPrice(T price) {
        this.price = price;
    }


    //Pair类引入了一个类型变量T，用尖括号（<>）括起来，并放在类名的后面。泛型类可以有多个类型变量。
    // 例如，可以定义Pair类，其中第一个域和第二个域使用不同的类型
    public static class Pair1<T,U> {

        private T name;
        private U price;

        public Pair1(T name,U price){
            this.name = name;
            this.price = price;
        }

        public T getName() {
            return name;
        }

        public void setName(T name) {
            this.name = name;
        }

        public U getPrice() {
            return price;
        }

        public void setPrice(U price) {
            this.price = price;
        }

        public static void main(String[] args) {

            //有构造方法
            Pair1 pair1 = new Pair1<String,Integer>("1",2);

            System.out.println(pair1.getPrice());
        }

    }
}
