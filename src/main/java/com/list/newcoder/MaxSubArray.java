package com.list.newcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxSubArray {

    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 5, -2, 6, -1};
        maxSubArray(arr);
    }

    //找一个
    private static int[] maxSubArray(int[] arr){
        if(arr == null || arr.length == 0){
            return arr;
        }
        int sum = 0;
        int max = 0;
        int start = 0;
        Map<String, Pair> maxMap = new HashMap<String, Pair>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum <= 0){
                sum = 0;
                start = i + 1;
            }else{
                if(sum > max){
                    max = sum;
                    maxMap.put("max", new Pair(start, i));
                }
            }
        }
        if(maxMap.size() == 1){
            Pair pair = maxMap.get("max");
            return Arrays.copyOfRange(arr, pair.start, pair.end);
        }else{
            Arrays.sort(arr);
            int[] one = new int[1];
            one[0] = arr[0];
            return one;
        }
    }

    static class Pair{
        private int start;
        private int end;
        Pair(int start, int end){
          this.start = start;
          this.end = end;
        }

    }
}
