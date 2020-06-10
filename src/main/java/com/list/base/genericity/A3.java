package com.list.base.genericity;

public class A3 extends Apple<String>{

    public A3() {
    }

    public A3(String info) {
        super(info);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    /*
    @Override
    public Object getInfo() {
        return super.getInfo();
    }*/

    public static void main(String[] args) {
        A3 a3 = new A3();
        a3.setInfo("hh");
        System.out.println(a3.getInfo());
        //A3本身没有指定泛型即没有定义A3<T>,A3<String>，写法错误
        //也不需要，因为继承了Apple<String>,类型已经设定
        //A3<String> a33 = new A3();
    }
}
