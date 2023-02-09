package com.list.base.genericity;

/**
 * 泛型类中带泛型方法
 * @param <T>
 */
public class Method2<T> {

    private T t;

    public Method2(T t) {
        this.t = t;
    }

    public T next(){
        return t;
    }

    public T next2(T tt){
       return tt;
    }

    /**
     * 泛型方法的标识由自定义<D>确定
     * @param d
     * @param <D>
     * @return
     */
    public <D> D generic(D d){
        return d;
    }

    /**
     * 泛型方法的标识由自定义<T>确定，T只是一个标识符号，是一个全新的类型，非泛型类中的T，如上面可以指定为D等其它
     * @param t
     * @param <T>
     * @return
     */
    public <T> T generic2(T t){
        return t;
    }

    public static void main(String[] args) {
        Method2<String> m = new Method2<>("t");
        System.out.println(m.next());
        System.out.println(m.next2("tt"));
        //泛型方法调用时指定类型
        System.out.println(m.generic(new Integer(10)));
        //泛型方法的标识由自定义<T>确定，T只是一个标识符号，非泛型类中的T，如上面可以指定为D等其它
        System.out.println(m.generic(new Integer(11)));
    }
}
