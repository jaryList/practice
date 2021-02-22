package com.list.thread;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MyForkJoinPool {

    /**
     * 跟踪ForkJoinPool的执行
     */
    public void debugForkJoinPool(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        //invoke为同步调用
        forkJoinPool.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                System.out.println("compute" + ":" + Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //最终也是包装成ForkJoinTask,调用 externalPush(task)
        //execute为异步调用
        /*forkJoinPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("execute" + ":" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
        //最终也是包装成ForkJoinTask,调用externalPush(task)
        //submit为异步调用
        /*forkJoinPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("submit" + ":" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
        System.out.println("invoke end");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }

    /**
     * 多任务，少线程，跟踪任务结束，线程情况
     */
    public void debugForkJoinPoolParallel(){
        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        for (int i = 0; i <=5; i++){
            forkJoinPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("submit start" + ":" + Thread.currentThread().getName());
                        Thread.sleep(50000);
                        System.out.println("submit end" + ":" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    /**
     * 查看ForkJoin线程的回收情况
     * 超过会回收，再使用再创建新线程
     */
    public void forkJoinPoolRecycleThread(){
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        forkJoinPool.submit(()->{
            Arrays.stream(new int[]{1,2}).parallel().forEach(i->{
                try {
                    System.out.println("first:" + Thread.currentThread().getName());
                    threadSet.add(Thread.currentThread());
                    Thread.sleep(20000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        try {
           Thread.sleep(120000);
            System.out.println("first end, theadSet size is " + threadSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        forkJoinPool.submit(()->{
            Arrays.stream(new int[]{1,2}).parallel().forEach(i->{
                try {
                    System.out.println("second:" + Thread.currentThread().getName());
                    threadSet.add(Thread.currentThread());
                    Thread.sleep(20000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        try {
            Thread.sleep(120000);
            System.out.println("second end, theadSet size is " + threadSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
