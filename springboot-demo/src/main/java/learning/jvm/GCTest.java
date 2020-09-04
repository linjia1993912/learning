package learning.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:测试JVM GC回收
 * java jvisualvm工具
 * @Author LinJia
 * @Date 2020/9/4
 **/
public class GCTest {

    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        while (true){
            list.add(new Test());
        }
    }
}
