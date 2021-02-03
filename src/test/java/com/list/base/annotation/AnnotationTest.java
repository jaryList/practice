package com.list.base.annotation;

import com.list.base.annotation.inherited.Son;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

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

    @Test
    public void inheritedTest(){
        Son son = new Son();
        Annotation[] annotations =  son.getClass().getAnnotations();
        Arrays.stream(annotations).forEach(annotation -> System.out.println(annotation));
    }

}
