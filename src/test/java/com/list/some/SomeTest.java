package com.list.some;

import org.junit.Test;

public class SomeTest {

    @Test
    public void test1(){
        int[] nums = {1, 2, 3};
        int[] nums2 = {3, 1, 2};
        nums = nums2;
        for (int num : nums) {
            System.out.println(num);
        }
    }

    @Test
    public void test2(){
        int a = 10;
        int b = 13;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(a ^ a);
        System.out.println(0 ^ a);
        System.out.println(a ^ 0);
        System.out.println(a ^ b ^ a);
        System.out.println(a ^ a ^ b);
    }

}
