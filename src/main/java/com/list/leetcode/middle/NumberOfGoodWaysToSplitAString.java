package com.list.leetcode.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给你一个字符串 s ，一个分割被称为 「好分割」 当它满足：将 s 分割成 2 个字符串 p 和 q ，它们连接起来等于 s 且 p 和 q 中不同字符的数目相同。
 * 请你返回 s 中好分割的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-good-ways-to-split-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfGoodWaysToSplitAString {

    public static int numSplits(String s) {
        int count = 0;
        Map<Character,Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            Character character = s.charAt(i);
            if(map.containsKey(character)){
                map.put(character, map.get(character) + 1);
            }else{
                map.put(character, 1);
            }
        }
        for(int i = 0; i < s.length(); i++){
            Character character = s.charAt(i);
            set.add(character);
            int charCount = map.get(character);
            if(charCount == 1){
                map.remove(character);
            }else{
                map.put(character, charCount - 1);
            }
            if(set.size() == map.size()){
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numSplits("aacaba"));
        System.out.println(numSplits("abcd"));
        System.out.println(numSplits("aaaaa"));
        System.out.println(numSplits("acbadbaada"));

        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println(atomicInteger.decrementAndGet());
        System.out.println(atomicInteger.get());
    }
}
