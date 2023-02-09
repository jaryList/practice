package com.list.base.genericity.wirldCard;


/**
 * <? extends T>允许调用读方法T get()获取T的引用，但不允许调用写方法set(T)传入T的引用（传入null除外）；
 * <? super T>允许调用写方法set(T)传入T的引用，但不允许调用读方法T get()获取T的引用（获取Object除外）。
 * 一个是允许读不允许写，另一个是允许写不允许读。 PECS原则 何时使用extends，何时使用super？为了便于记忆，
 * 我们可以用PECS原则：Producer Extends Consumer Super。 即：如果需要返回T，它是生产者（Producer），要使用extends通配符；如果需要写入T，它是消费者（Consumer），要使用super通配符。
 *
 * 因为<?>通配符既没有extends，也没有super，因此：不允许调用set(T)方法并传入引用（null除外）；不允许调用T get()方法并获取T引用（只能获取Object引用）。
 *
 * 换句话说，既不能读，也不能写，那只能做一些null判断：无限定通配符<?>很少使用，可以用<T>替换，同时它是所有<T>类型的超类。
 */

import com.list.base.genericity.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 通配符
 */
public class WildCard {


    /**
     * 加和
     */
    public static int add(Pair<Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }

    /**
     * 通配符上限，获取
     */
    public static int wildCard_extend_get(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }

    /**
     * 通配符上限，设值
     */
    public static int wildCard_extend_set(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        /**
         * error
         * p.first本身不一定是Number，而是个? extend Number
         * get的时候向上转型了
         * set时候可能需要向下转型，所有不可以设值操作
         */
        //p.setFirst(first);
        //p.setLast(last);
        return p.getFirst().intValue() + p.getLast().intValue();
    }

    /**
     * 通配符下限，设值
     */
    public static void wildCard_super_set(Pair<? super Integer> p, Integer n) {
        p.setFirst(n);
        p.setLast(n);
        /**
         * 不是不能get，是不能赋给确定类型
         * error 赋值即涉及向上转型
         */
        p.getFirst();
        /*Integer i = p.getFirst();
        Number number = p.getFirst();*/
    }

    /**
     * 通配符下限，设值
     */
    public static void wildCard_super_set(List<? super A> list){
        //下限是可以设置，也是收边界的限制，A的父类即超出A的范围是不可以的
        //list.add(new SA());
        list.add(new A());
        list.add(new B());
        list.add(new C());
    }

    /**
     * 通配符下限，获取
     */
    public static void wildCard_super_get(Pair<? super Integer> p){
        /**
         * error,get的时候? super Integer，不能赋给确定类型
         * @see #wildCard_super_set(Pair, Integer)
         */
        //Integer i = p.getFirst();
        //Number n = p.getLast();
        //不是不能get，是不能赋给确定类型
        System.out.println(p.getLast());
    }

    public static void main(String[] args) {
        Pair<Integer> p = new Pair<>(123, 456);
        //error
        //int n = add(p);

        int extend_get = wildCard_extend_get(p);
        System.out.println("extend_get = " + extend_get);

        int extend_set = wildCard_extend_set(p);
        System.out.println("extend_set = " + extend_set);

        wildCard_super_get(p);

        List<SA> listSA = new ArrayList<>();
        //及时传的是List<SA>，内部也不可以list.add(new SA())，因为如下面可能传入的是List<A>,list.add(new SA())就会向上转型
        wildCard_super_set(listSA);
        List<A> listA = new ArrayList<>();
        wildCard_super_set(listA);

    }

}
