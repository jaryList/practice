package com.list.base.genericity;


import java.io.Serializable;

/**
 * 上限可以指定多个
 * @param <T>
 */
public class UpperBound<T extends Number & Serializable> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        UpperBound<Integer> ui = new UpperBound<>();
        UpperBound<Double> ud = new UpperBound<>();
        //error,不是指定范围的子类
        //UpperBound<String> us = new UpperBound<String>();
    }
}
