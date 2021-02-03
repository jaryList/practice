package com.list.leetcode.simple;

import java.util.*;


public class BracketSequence {
    /**
     * 
     * @param s string字符串 
     * @return bool布尔型
     */
    public static boolean isValid (String s) {
        // write code here
        char[] charArray =  s.toCharArray();
        Map<Character,Character> map = new HashMap<>(4);
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < charArray.length; i++){
            if(map.containsKey(charArray[i])){
                if(stack.isEmpty()){
                   return false;
                }
                if (!map.get(charArray[i]).equals(stack.pop())) {
                    return false;
                }
            }else{
                stack.add(charArray[i]);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{]";
        System.out.println(isValid(s));
    }
}