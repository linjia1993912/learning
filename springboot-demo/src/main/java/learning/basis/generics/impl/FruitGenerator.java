package learning.basis.generics.impl;

import learning.basis.generics.Generator;

/**
 * @Description:实现泛型接口
 * 可以封装公共的泛型接口
 * @Author LinJia
 * @Date 2020/7/21
 **/
public class FruitGenerator implements Generator<String> {

    @Override
    public String next() {
        return "111";
    }


    //或者也可以这样

    static class FruitGenerator1<T> implements  Generator<T>{

        private T next;

        public FruitGenerator1(T next){
            this.next = next;
        }

        @Override
        public T next() {
            return next;
        }

        public static void main(String[] args) {
            FruitGenerator1 fruitGenerator1 = new FruitGenerator1<String>("1111");
            System.out.println(fruitGenerator1.next());
        }

    }

}
