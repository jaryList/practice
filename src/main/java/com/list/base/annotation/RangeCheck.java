package com.list.base.annotation;

import java.lang.reflect.Field;

/**
 * 解析校验范围
 * @author lisongtao3
 * @className RangeCheck
 * @date 2020/6/4
 */
public class RangeCheck<T> {

    /**
     * 校验
     * @author lisongtao3
     * @date 2020/6/4
     * @param t check object
     **/
    public void check(T t) throws Exception{
        if(t == null){
            throw new IllegalArgumentException("the params is null");
        }
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            //private field should set accessible true
            field.setAccessible(true);
            Range range = field.getAnnotation(Range.class);
            if(range != null){
                Object object = field.get(t);
                boolean overRange = false;
                if(object instanceof String){
                    String stringValue = (String) object;
                    if(stringValue.length() < range.min() || stringValue.length() > range.max()){
                        overRange = true;
                    }
                }
                if(object instanceof Integer){
                    Integer intValue = (Integer) object;
                    if(intValue < range.min() || intValue > range.max()){
                        overRange = true;
                    }
                }
                if(overRange){
                    throw new IllegalArgumentException(field.getName() + "'s value is over " + field.getName() + "'s field range");
                }
            }
        }
    }
}
