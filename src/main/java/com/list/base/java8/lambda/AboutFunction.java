package com.list.base.java8.lambda;

import java.util.function.Function;

public class AboutFunction {

    public static final Function<String, Integer> f = s -> Integer.parseInt(s);
    public static final Function<Integer, String> f1 = i -> String.valueOf(i);
    public static final Function<String, Person> f2 = Person::new;
    //等同于f2
    public static Person makePerson(String name){
        return new Person(name);
    }

    public static void test(){
        //andThen先f后f1
        String s = f.andThen(f1).apply("10");
        System.out.println("s = " + s);
        //注意先执行哪个function,需要的入参也可以看出
        //compose先f1后f,看源码了然，需要注意的是泛型的类型
        Integer i = f.compose(f1).apply(20);
        System.out.println("i = " + i);
        Person p = f.andThen(f1).andThen(f2).apply("10");
        //Person p = f.andThen(f1).andThen(AboutFunction::makePerson).apply("10");
        System.out.println("p's name is " + p.getName());
    }

    public static void main(String[] args) {
        test();
    }
}
