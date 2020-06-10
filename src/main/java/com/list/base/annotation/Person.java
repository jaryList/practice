package com.list.base.annotation;

/**
 * 自定义POJO,个人
 * @author lisongtao3
 * @className Person
 * @date 2020/6/4
 */
public class Person {
    /**
     * 姓名
     */
    @Range(min = 2,max = 40)
    private String name;

    /**
     * 年龄
     */
    @Range(min = 0, max = 150)
    private int age;

    /**
     * 空构造器
     */
    public Person() {
    }

    /**
     * 有参构造器
     * @param name
     * @param age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
