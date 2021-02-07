package com.list.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    /**
     * TransmittableThreadLocal继承InheritableThreadLocal
     * 此处就是父子线程,此种情况建议直接使用InheritableThreadLocal
     * @see MyThreadLocal#cleanInheritableThreadLocal()
     * https://github.com/alibaba/transmittable-thread-local
     */
    public void ttlJustInherit(){
        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal();
        transmittableThreadLocal.set("parent");
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
        });
        thread.setName("sonThread");
        thread.start();
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程池中获取外部线程副本变量
     * 采用TtlRunnable修饰实际Runnable; TtlCallable封装Callable方式
     * 注:线程池任务中还是要自行处理副本变量,同{@link MyThreadLocal#cleanInheritableThreadLocal()}
     * @throws Exception
     * https://github.com/alibaba/transmittable-thread-local
     */
    public void ttlTtlRunnable() throws Exception{
        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal();
        transmittableThreadLocal.set("parent");
        //此处用这个只是为了方便，正常使用spring线程池,ThreadPoolTaskExecutor
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(TtlRunnable.get(()->{
            System.out.println(Thread.currentThread().getName() + ":" + transmittableThreadLocal.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":before remove:" + transmittableThreadLocal.get());
            transmittableThreadLocal.remove();
            System.out.println(Thread.currentThread().getName() + ":remove:" + transmittableThreadLocal.get());
        }));
        executorService.submit(TtlRunnable.get(()->{
            System.out.println(Thread.currentThread().getName() + ":" + transmittableThreadLocal.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ":before remove:" + transmittableThreadLocal.get());
            transmittableThreadLocal.remove();
            System.out.println(Thread.currentThread().getName() + ":remove:" + transmittableThreadLocal.get());
        }));
        System.out.println(Thread.currentThread().getName() + ":" + transmittableThreadLocal.get());
        Thread.sleep(100);
        transmittableThreadLocal.remove();
        System.out.println(Thread.currentThread().getName() + ":remove:" + transmittableThreadLocal.get());
        Thread.sleep(500);
    }


    /**
     * 原生Runnable、Callable验证非修饰是否有问题
     * 非TtlRunnable修饰实际Runnable; TtlCallable封装Callable方式
     * 注:依然能获得？？？是java agent吗？
     * @throws Exception
     */
    public void ttlRunnable() throws Exception{
        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal();
        transmittableThreadLocal.set("parent");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
        });
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
        });
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
    }


    /**
     * 线程池中获取外部线程副本变量
     * 采用TtlExecutors封装线程池的方式ExecutorService的方式
     * 注:因不建议Executors创建线程方式,建议使用 {@link MyThreadLocal#ttlTtlRunnable()} 方式或者获取spring的ThreadPoolTaskExecutor
     * getTtlExecutor：修饰接口Executor   注:spring的ThreadPoolTaskExecutor实现自Executor
     * getTtlExecutorService：修饰接口ExecutorService
     * getTtlScheduledExecutorService：修饰接口ScheduledExecutorService
     * 注:线程池任务中还是要自行处理副本变量,同{@link MyThreadLocal#ttlTtlRunnable()}{@link MyThreadLocal#cleanInheritableThreadLocal()}
     * @throws Exception
     * https://github.com/alibaba/transmittable-thread-local
     */
    public void ttlTtlExecutors() throws Exception{
        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal();
        transmittableThreadLocal.set("parent");
        ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
        });
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
        });
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "-" + transmittableThreadLocal.get());
    }



}
