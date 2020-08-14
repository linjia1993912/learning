package learning.basis.sort_algorithm;

import java.util.Arrays;

/**
 * @Description:十大经典排序算法
 * @Author LinJia
 * @Date 2020/8/10
 **/
public class SortAlgorithm {

    //冒泡排序
    //比较相邻的元素。如果第一个比第二个大，就交换他们两个。
    //对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
    //针对所有的元素重复以上的步骤，除了最后一个。
    //持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
    public static void bubbleSort(int [] array){
        for (int i = 0; i < array.length; i++) {
            //每次比较除掉最后一个
            for (int j = 0; j <array.length-1-i ; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    //交换位置
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    //倒序
    public static void bubbleSortDESC(int [] array){
        for (int i = 0; i < array.length; i++) {
            //每次比较除掉最后一个
            for (int j = 0; j <array.length-1-i ; j++) {
                if(array[j] < array[j+1]){
                    int temp = array[j];
                    //交换位置
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    //选择排序
    //算法思想：
    //在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
    //从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾
    //以此类推，直到所有元素均排序完毕
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                //找到最小的数
                if (array[j] < array[minIndex]){
                    minIndex = j;
                    //将最小数的索引保存
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        System.out.println(Arrays.toString(array));
    }

    //插入排序
    //算法思想：
    //从第一个元素开始，该元素可以认为已经被排序
    //取出下一个元素，在已经排序的元素序列中从后向前扫描
    //如果该元素（已排序）大于新元素，将该元素移到下一位置
    //重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
    //将新元素插入到该位置后
    //重复步骤2~5
    public static void insertionSort(int[] array) {
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        System.out.println(Arrays.toString(array));
    }


    public static void main(String[] args) {
        int [] num = {1,23,212,14,78,65,45,2,0};
        insertionSort(num);
    }
}
