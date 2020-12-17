package learning.basis;

/**
 * @Description:运算符
 * @Author LinJia
 * @Date 2020/12/17
 **/
public class Operator {

    public static String printinary(int num){
        String str ="";
        for(int i=0;i<32;i++){
            int t=(num & 0x80000000>>>i)>>>(31-i);
            str+=t;
        }
        return str;
    }

    public static void main(String[] args) {

        //左移
        //左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，
        // 并且在低位补零。其实，向左移n 位，就相当于乘上2 的n 次方，例如下面的例子。
        int a = 2,b=2,c;
        System.out.println("左移前二进制:"+printinary(a));

        c = a<<b; //相当于2<<2

        //输出二进制数字
        System.out.println("左移后二进制:"+printinary(c));

        System.out.println("a 移位的结果是:"+c);
        //分析上面代码，2 的二进制是00000010，它向左移动2 位，就变成了00001000，即8。
        // 如果从另一个角度来分析，它向左移动2 位，其实就是乘上2 的2 次方，结果还是8

        //右移
        //带符号右移运算符用“>>”表示，是将运算符左边的运算对象，向右移动运算符右边指定的位数。
        // 如果是正数，在高位补零，如果是负数，则在高位补1
        int aa = 16;
        System.out.println("aa移位前二进制:"+printinary(aa));
        int cc = -16;
        System.out.println("cc移位前二进制:"+printinary(cc));
        int bb = 2;
        int dd = 2;
        System.out.println("aa 的移位结果："+(aa>>bb));
        System.out.println("cc 的移位结果："+(cc>>dd));

        //分析上面代码：
        // aa 的值是16转换成二进制是00010000，让它右移两位成00000100 即4，
        // cc 的值是-16转换成二进制是11101111，让它右移两位成11111011 即-4

        //无符号右移运算符
        //右移运算符无符号用“>>>”表示，是将运算符左边的对象向右移动运算符右边指定的位数，
        // 并且在高位补0，其实右移n 位，就相当于除上2 的n 次方
        int a1=16;
        int b2=2;
        System.out.println("a1 移位的结果是"+(a1>>>b2));
        //分析上面代码：16 的二进制是00010000，它向右移动2 位，就变成了00000100，即4。
        // 如果从另一个角度来分析，它向右移动2 位，其实就是除以2 的2 次方，结果还是4

    }
}