package com.list.leetcode.simple;

public class StringReverse2 {

    public static String reverseStr(String s, int k) {
        char[] charArr = s.toCharArray();
        int len = charArr.length;
        int num = len / k;
        int mod = len % k;
        if(num == 0){
            swap(charArr, 0, len-1);
            return new String(charArr);
        }
        for(int i = 0; i < num; i = i + 2){
            swap(charArr, i * k, (i + 1) * k - 1);
        }
        if(mod > 0){
            swap(charArr, k * num, len - 1);
        }
        return new String(charArr);
    }

    /**
     * 翻转字符串
     */
    public static void swap(char[] charArr, int start, int end){
        while(start < end){
            char tmp = charArr[start];
            charArr[start] = charArr[end];
            charArr[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        StringReverse2 stringReverse2 = new StringReverse2();
        String old = "abcdefg";
        String reverseStr = stringReverse2.reverseStr("abcdefg", 4);
        System.out.println(old);
        System.out.println(reverseStr);
    }
}
