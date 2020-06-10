package com.list.base.genericity;

class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    //error 不可以直接new泛型类型
    /*public Pair() {
        this.first = new T();
        this.last = new T();
    }*/

    //通过反射实例化泛型类型对象
    public Pair(Class<T> t) throws IllegalAccessException, InstantiationException {
        this.first = t.newInstance();
        this.last = t.newInstance();
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setLast(T last) {
        this.last = last;
    }
}