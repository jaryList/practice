package com.list.jike.sort;

/**
 * 插入排序
 * 排序区 | 未排序区
 * 遍历未排序区和排序区一一对比，看是否进行移动
 */
public class InsertSort {
    public static void sort(int[] array){
        if(array == null || array.length <= 1){
            return;
        }
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int value = array[i];
            int j = i - 1;
            //查找位置
            for (; j >= 0; j--) {
                if(array[j] > value){
                    //数据移动
                    array[j + 1] = array[j];
                }else{
                    break;
                }
            }
            //插入数据
            array[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 1, 5, 3};
//      int[] array = {1, 2, 3, 4, 5};
        sort(array);
    }
}
