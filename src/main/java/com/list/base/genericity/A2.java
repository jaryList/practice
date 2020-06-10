package com.list.base.genericity;

/**
 * 如果子类也设置了泛型标识，就是个标识符为String的泛型，而不是java.lang.String
 * @see A3
 */
public class A2<String> extends Apple<String>{

    public A2() {
    }

    public A2(String info) {
        super(info);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    //参考比对A，和A不同，返回类型也被指定限制
    /*@Override
    public Object getInfo() {
        return super.getInfo();
        //return new Object();
    }*/

    /*public static void main(String[] args) {
        A2 a2 = new A2();
        a2.setInfo("hh");
        System.out.println(a2.getInfo());
        A2 a2s = new A2("string");
        System.out.println(a2s.getInfo());
    }*/

}
