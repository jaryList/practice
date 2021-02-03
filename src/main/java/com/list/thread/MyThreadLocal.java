package com.list.thread;

public class MyThreadLocal {

    /**
     * 可继承ThreadLocal,即InheritableThreadLocal使用时
     * 父线程清理,不影响子线程; 子线程也需要清理,因为是从父线程中复制到子线程中，是子线程自己的ThreadLocalMap
     */
    public void cleanInheritableThreadLocal() {
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("main-parent");
        Thread sonThread = new Thread(()->{
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
                Thread grandsonThread = new Thread(()->{
                    System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
                    inheritableThreadLocal.remove();
                    System.out.println(Thread.currentThread().getName() + "=======remove======");
                    System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
                });
                grandsonThread.setName("grandsonThread");
                grandsonThread.start();
                Thread.sleep(100);
                inheritableThreadLocal.remove();
                System.out.println(Thread.currentThread().getName() + "=======remove======");
                System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        sonThread.setName("sonThread");
        sonThread.start();
        System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
        try {
            Thread.sleep(1000);
            inheritableThreadLocal.remove();
            System.out.println(Thread.currentThread().getName() + "=======remove======");
            System.out.println(Thread.currentThread().getName() + "线程 value = " + inheritableThreadLocal.get());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
