package com.list.base.genericity;

import java.util.ArrayList;
import java.util.List;

public class Old {

    public static void main(String[] args) {
        cast();
    }

    public static void cast(){
        List list = new ArrayList();
        list.add("string");
        list.add(1);
        for (int i = 0; i < list.size(); i++) {
            String string = (String)list.get(i);
            System.out.println(string);
        }
    }



}
