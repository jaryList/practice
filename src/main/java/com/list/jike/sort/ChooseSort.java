package com.list.jike.sort;

/**
 * 选择排序
 */
public class ChooseSort {

    public static void sort(int[] array){
        if (array == null || array.length <= 1) {
            return;
        }
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            //记录交互位置
            int k = i;
            int min = array[i];
            for (int j = i + 1; j < n ; j++) {
                if(array[j] < min){
                    //记录最小值
                    min = array[j];
                    //记录最小值位置
                    k = j;
                }
            }
            //最小值交换
            array[k] = array[i];
            array[i] = min;
        }

    }

    public static void main(String[] args) {
        int[] array = {2, 4, 1, 5, 3};
        sort(array);
    }
}
