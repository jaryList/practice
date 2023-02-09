package com.list.base.genericity.wirldCard;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface MyFunction<T, R> {

    R apply(T t);

    /**
     * Function真正定义的方式
     * @see java.util.function.Function#andThen(Function)
     * @param after
     * @param <V>
     * @return
     */
    default <V> MyFunction<T, V> andThenReal(MyFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * 不使用通配符方式
     * @param after
     * @param <V>
     * @return
     */
    default <V> MyFunction<T, V> andThen(MyFunction<R, V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * 第一个参数使用边界下限
     * @param after
     * @param <V>
     * @return
     */
    default <V> MyFunction<T, V> andThen2(MyFunction<? super R, V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * 第二参数使用边界上限
     * @param after
     * @param <V>
     * @return
     */
    default <V> MyFunction<T, V> andThen3(MyFunction<R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }



    /**
     * 说明为什么函数接口Function的方法入参使用泛型通配符
     */
    default void test() {
        MyFunction<String, Integer> f = s -> Integer.parseInt(s);
        //不使用通配符方式一样可以，只是不通用
        MyFunction<Integer, String> f1 = i -> String.valueOf(i);
        f.andThen(f1).apply("1");
        //R边界下限，f转换后的R为Integer，Integer是下限；可以使用更通用的函数入参为Number的方式
        MyFunction<Number, String> f2 = i -> String.valueOf(i);
        f.andThen2(f2);
        //V边界上限，定义转换的V是个上限，可以转换接收多类型函数，这个上线有没有缩小范围，其实并没有，你可以将V调大控制范围
        MyFunction<Integer, Object> f3 = i -> "1";
        Object o = f.andThen3(f3).apply("11");
    }
}
