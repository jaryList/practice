package com.list.base.genericity;

import java.util.List;

public class Rectangle extends Shape {
    @Override
    public void draw(Canvas canvas) {
        System.out.println("draw rectangle");
    }


    public void addRectangle(List<? extends Shape> list){
        /**
         * error,不知到通配的具体类型
         * 或者说反了:要先知道通配类型是什么才能操作
         * 而不是操作的时候是什么类型都不知道
         */
        //list.add(new Rectangle());
    }

    public void addRectangle2(List<? super Rectangle> list){

        list.add(new Rectangle());
    }

    public void addRectangle3(List<? super Shape> list){

        list.add(new Rectangle());
    }





}
