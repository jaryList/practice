package com.list.jike.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void sort(int[] array){
        if(array == null || array.length <= 1){
            return;
        }
        int n = array.length;
        for (int i = 0; i < n; i++){
            //一次遍历中有无数据交换，如果没有数据交换说明已经是顺序的
            //可以减少遍历，例如:已经是顺序的情况很容易看出作用，遍历一次就知道已经是顺序的O(n)
            //最坏O(n^2)
            boolean exchanged = false;
            for(int j = 0; j < n - i - 1; j++){
                int tmp = array[j];
                if(array[j] > array[j + 1]){
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有数据交换
                    exchanged = true;
                }
            }
            //已经顺序的就可以提前退出循环
            if(!exchanged){
                break;
            }
        }
    }

    public static void main(String[] args) {
//       int[] array = {2, 4, 1, 5, 3};
        int[] array = {1, 2, 3, 4, 5};
        sort(array);
    }
}
