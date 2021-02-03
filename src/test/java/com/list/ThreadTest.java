package com.list;

import com.list.thread.MyThreadLocal;
import org.junit.Test;

public class ThreadTest {

    @Test
    public void cleanInheritableThreadLocalTest(){
        MyThreadLocal threadLocal = new MyThreadLocal();
        threadLocal.cleanInheritableThreadLocal();
    }
}
