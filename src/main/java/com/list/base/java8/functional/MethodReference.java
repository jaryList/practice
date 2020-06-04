package com.list.base.java8.functional;

import java.util.Arrays;

/**
 * 方法引用
 * MethodReference
 * @author lisongtao3
 * @date 2020/6/4
 */
public class MethodReference {
	/**
	 * 静态方法
	 * @author lisongtao3
	 * @date 2020/6/4
	 **/
	static int cmp(String s1, String s2) {
		return s1.toLowerCase().compareTo(s2.toLowerCase());
	}

	/**
	 * 实例方法
	 * @author lisongtao3
	 * @date 2020/6/4
	 **/
	public int compareTo(String s1, String s2) {
		return s1.toLowerCase().compareTo(s2.toLowerCase());
	}

	public static void main(String[] args) {
		String[] array = new String[] { "apple", "Orange", "banana", "Lemon" };
		// 请使用忽略大小写排序，并改写为方法引用:
		Arrays.sort(array, MethodReference::cmp);//静态方法引用
		//Arrays.sort(array, new MethodReference()::compareTo);//实例方法引用
		//Arrays.sort(array, MethodReference::compareTo);//错误，第一个参数this并不是String实例，而是MethodReference实例
		//Arrays.sort(array, String::compareToIgnoreCase);//类:实例方法引用，第一次参数为类本身实例，写法隐藏了this
		System.out.println(String.join(", ", array));
	}
}