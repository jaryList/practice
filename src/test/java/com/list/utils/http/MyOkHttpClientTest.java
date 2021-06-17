package com.list.utils.http;

import org.junit.Before;
import org.junit.Test;

public class MyOkHttpClientTest {

    private MyOkHttpClient myClient;

    @Before
    public void init(){
        myClient = new MyOkHttpClient();
    }

    @Test
    public void getTest(){
        myClient.get();
    }

    @Test
    public void asynGetTest(){
        myClient.asynGet();
    }

    @Test
    public void getInSubThreadTest(){
        myClient.getInSubThread();
    }

    @Test
    public void postStringTest(){
        myClient.postString();
    }

    @Test
    public void cacheTest(){
        myClient.cache();
    }

    @Test
    public void poolWithSameUrlTest(){
        myClient.poolWithSameUrl();
    }

    @Test
    public void poolWithDiffUrlTest(){
        myClient.poolWithDiffUrl();
    }

    @Test
    public void poolLoop1Test(){
        myClient.poolLoop1();
    }

    @Test
    public void poolLoop2Test(){
        myClient.poolLoop2();
    }

    @Test
    public void poolLoopAsynTest(){
        myClient.poolLoopAsyn();
    }

    @Test
    public void headerTest(){
        myClient.header();
    }

    @Test
    public void postPCMSTest(){
        myClient.postPCMS();
    }


}
