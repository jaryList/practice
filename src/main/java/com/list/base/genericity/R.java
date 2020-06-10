package com.list.base.genericity;

public class R<T> {

    //static T info; //error
    T age;

    public void foo(T msg){}

    //error 普通方法， 使用类时就需指定实体类型参数
    /*public static void bar(T msg){

    }*/
    //泛型方法, 使用方法时才定义、使用实体类型参数
    public static <E> E s_m(E e){
        return e;
    }
}
