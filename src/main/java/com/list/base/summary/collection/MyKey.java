package com.list.base.summary.collection;

import java.util.HashMap;
import java.util.Map;

public class MyKey {

    @Override
    public int hashCode() {
        return 0;
    }

    public static void main(String[] args) {
        Map<MyKey, Integer> map = new HashMap(2);
        map.put(new MyKey(), 1);
        map.put(new MyKey(), 2);
        map.put(new MyKey(), 3);
        map.put(new MyKey(), 4);
        map.put(new MyKey(), 5);
        map.put(new MyKey(), 6);
        map.put(new MyKey(), 7);
        map.put(new MyKey(), 8);
        map.put(new MyKey(), 9);
        map.put(new MyKey(), 10);
        map.put(new MyKey(), 11);
        System.out.println(map.size());
    }
}
