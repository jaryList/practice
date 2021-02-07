package com.list;

import com.list.thread.MyThreadLocal;
import org.junit.Test;

public class ThreadTest {

    @Test
    public void cleanInheritableThreadLocalTest(){
        MyThreadLocal threadLocal = new MyThreadLocal();
        threadLocal.cleanInheritableThreadLocal();
    }

    @Test
    public void ttlJustInheritTest(){
        MyThreadLocal threadLocal = new MyThreadLocal();
        threadLocal.ttlJustInherit();
    }

    @Test
    public void ttlRunnableTest() throws Exception{
        MyThreadLocal threadLocal = new MyThreadLocal();
        threadLocal.ttlRunnable();
    }

    @Test
    public void ttlTtlRunnableTest() throws Exception{
        MyThreadLocal threadLocal = new MyThreadLocal();
        threadLocal.ttlTtlRunnable();
    }

    @Test
    public void ttlTtlExecutorsTest() throws Exception{
        MyThreadLocal threadLocal = new MyThreadLocal();
        threadLocal.ttlTtlExecutors();
    }

}
