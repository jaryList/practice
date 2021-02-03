package com.list.base.genericity;

import java.util.ArrayList;
import java.util.List;

//没有指定类型，默认是Object
public class A extends Apple {

    public A() {
    }

    public A(Object info) {
        super(info);
    }

    @Override
    public Object getInfo() {
        return super.getInfo();
    }

    //不根据返回类型的重写, 如果不是重写冲突，可行，无异常
    /*@Override
    public String getInfo() {
        return super.getInfo().toString();
    }*/

    public static void main(String[] args) {
        A a = new A("string");
        //a.setInfo(1.2); //可以设置不同类型,参考比对A1
        System.out.println(a.getInfo());

        A ao = new A(1.2);
        System.out.println(ao.getInfo());

        List<?> list = new ArrayList<A>();
        List<? extends A> list1 = new ArrayList<A>();
        List<? super A> list2 = new ArrayList<A>();
    }
}
