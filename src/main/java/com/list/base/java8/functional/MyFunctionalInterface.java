package com.list.base.java8.functional;

/**
 * 自定义函数式接口
 * MyFunctionalInterface
 * @author lisongtao3
 * @date 2020/6/4
 */
@FunctionalInterface
public interface MyFunctionalInterface {
    /**
     * 抽象方法，有且仅有一个
     */
    void method();


    //void method2(); //error, multiple

    /**
     * 默认方法
     **/
    default void defaultMethod(){

    }

    //default void defaultMethod2(); //error, no method body

    /**
     * 静态方法
     */
    static void staticMethod(){

    }

    /**
     * 来自Object的equals方法
     */
    @Override
    boolean equals(Object obj);


    /**
     * 来自Object的hashCode方法,不可以有方法体
     */
    /*@Override
    int hashCode(){

    }*/
}
