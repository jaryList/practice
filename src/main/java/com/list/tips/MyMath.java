package com.list.tips;

/**
 * 数学计算相关小技巧
 */
public final class MyMath {

    /**
     * 推荐，简单易于理解
     * @param a
     * @param b
     * @return
     */
    public static int deviceAndCeil(int a, int b){
        return a / b + a % b == 0 ? 0 : 1;
    }

    /**
     * 个人不推荐，涉及转换，方法封装多
     * @param a
     * @param b
     * @return
     */
    public static int deviceAndCeil2(int a, int b){
        return (int)Math.ceil((double) a / b);
    }

    /**
     * 推荐，不太容易理解，很巧妙
     * a / b 分为两种情况，一是除尽，二是有余数
     * 除尽时: b - 1 / b 为0舍弃
     * 余数时: 余数范围为1到b-1; 即与b - 1相加范围为b到b + (b - 2), 即b/b =1,（b + (b - 2)）/b = 1
     * @param a
     * @param b
     * @return
     */
    public static int deviceAndCeil3(int a, int b){
        return (a + b - 1) / b;
    }

    /**
     * 天然向下取整
     * @param a
     * @param b
     * @return
     */
    public static int deviceAndFloor(int a, int b){
        return a / b;
    }

}
