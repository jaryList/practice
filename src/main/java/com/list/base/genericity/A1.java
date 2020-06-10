package com.list.base.genericity;


public class A1<T> extends Apple<T> {

    public A1() {
    }

    public A1(T info) {
        super(info);
    }

    @Override
    public T getInfo() {
        return super.getInfo();
    }

    public static void main(String[] args) {
        A1<String> a = new A1("string");
        //a.setInfo(1.2); //可以设置不同类型, 参考比对A
        System.out.println(a.getInfo());

        A1<Double> ao = new A1(1.2);
        System.out.println(ao.getInfo());

    }
}
