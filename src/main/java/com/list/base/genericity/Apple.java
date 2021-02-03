package com.list.base.genericity;

public class Apple<T> {

    private T info;

    public Apple() {
    }

    public Apple(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public static void main(String[] args) {
        Apple<String> appleStr = new Apple<String>("appleString");
        System.out.println(appleStr.getInfo());
        Apple<Double> appleDb = new Apple<Double>(1.2);
        System.out.println(appleDb.getInfo());
        //都是Apple.class, 没有Apple<String>.class和Apple<Double>.class;因为擦除、擦拭
        System.out.println(appleStr.getClass() == appleDb.getClass());
    }
}
