package com.list.base.annotation;

import org.junit.Test;

/**
 * 注解测试类
 * @author lisongtao3
 * @className AnnotationTest
 * @date 2020/6/4
 */
public class AnnotationTest {

    @Test
    public void checkTest() {
        try {
            //Person person = new Person("list",18);
            Person person = new Person("l",18);
            //Person person = new Person("list",162);
            RangeCheck<Person> rangeCheck = new RangeCheck<>();
            rangeCheck.check(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
