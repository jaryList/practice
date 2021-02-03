package com.list.base.annotation.inherited;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Documented
@Inherited
public @interface MyInherited {

    String test() default "extend";

}
