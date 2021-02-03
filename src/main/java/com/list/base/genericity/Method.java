package com.list.base.genericity;

import java.util.*;

/**
 * 泛型方法
 */
public class Method {

    /**
     * 普通实例方法
     */
    public void normal(){

    }

    /**
     * 普通静态方法
     */
    public static void normal_static(){

    }

    /**
     * <T>声明, 方法才可以使用, 方法参数也有泛型类
     */
    public <T> T generic(T t){
        return t;
    }

    /**
     * <T>声明, 方法才可以使用, 方法参数也有泛型类
     */
    public <T> T generic2(Class<T> clazz) throws Exception{
        return clazz.newInstance();
    }

    /**
     * <K,V>多类型声明,每个方法都是各自定义
     */
    public <K,V> Map<K, V> generic3(K key, V value) throws Exception{
        Map<K, V> map = new HashMap(1);
        map.put(key, value);
        return map;
    }



    /**
     * 数组转集合
     **/
    public static <E> Collection<E> fromArrayToCollection(E[] array, Collection<E> collection){
        for (E e : array) {
            collection.add(e);
        }
        return collection;
    }

    /**
     * 集合拷贝
     */
    public static <E> Collection<E> copy1(Collection<E> source, Collection<E> target){
        for (E e : source) {
            target.add(e);
        }
        return target;
    }

    /**
     * 集合拷贝
     */
    public static <E> Collection<E> copy2(Collection<? extends E> source, Collection<E> target){
        for (E e : source) {
            target.add(e);
        }
        return target;
    }

    /**
     * 集合拷贝
     */
    public static <E> Collection<? super E> copy3(Collection<? extends E> source, Collection<? super E> target){
        for (E e : source) {
            target.add(e);
        }
        return target;
    }

    /**
     * 集合拷贝
     */
    /*public static <E> Collection<? extends E> copy4(Collection<? extends E> source, Collection<? extends E> target){
        for (E e : source) {
            //error, 这个不是E，是? extends E, 这时候你知道具体是啥吗？
            //比如source是E或者子类E1，target是子类E2
            target.add(e);
        }
        return target;
    }*/

    /**
     * 集合拷贝
     */
    /*public static <E> Collection<? super E> copy5(Collection<? super E> source, Collection<? super E> target){
        //error, 这个不是E，是 ? super E，这时候你知道具体是啥吗？
        //比如source是父类SE
        for (E e : source) {
            target.add(e);
        }
        return target;
    }*/


    public static void copyTest(){
        Object[] array_object = new Object[]{1, 2};
        Collection<Object> collection_object = new ArrayList<Object>();
        fromArrayToCollection(array_object, collection_object);

        String[] array_string = new String[]{"1","2"};
        Collection<String> collection_string = new ArrayList<String>();
        fromArrayToCollection(array_string, collection_string);

        //查看方法签名(cmd + mouse on)，都识别为object
        //数组元素本身就是泛型类，而非包装
        fromArrayToCollection(array_string, collection_object);

        //上面类似这个list object添加 string
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add("kk");

        //指定同类型拷贝
        copy1(collection_string, collection_string);

        //error，类型应该被限定，结果两个类型不同
        //copy1(collection_string, collection_object);

        //target范围大于等于source
        copy2(collection_string, collection_object);
        copy3(collection_string, collection_object);
    }


    /**
     * 确定类型
     * @param anyTypeElement
     */
    public void certainType(List<Object> anyTypeElement){
        for (int i = 0; i < anyTypeElement.size(); i++) {
            System.out.println(anyTypeElement.get(i));
        }
    }

    /**
     * 通配符
     */
    public void wildcard(List<?> anyTypeElement){
        for (int i = 0; i < anyTypeElement.size(); i++) {
            System.out.println(anyTypeElement.get(i));
        }
    }
    /**
     * 一般泛型
     */
    public <T> void generic(List<T> anyTypeElement){
        for (int i = 0; i < anyTypeElement.size(); i++) {
            System.out.println(anyTypeElement.get(i));
        }
    }

    public void test_test(){
        List<String> list = new ArrayList<String>();
        //error
        //certainType(list);

        //普通泛型 优先于 通配符
        generic(list);
        wildcard(list);
    }

}
