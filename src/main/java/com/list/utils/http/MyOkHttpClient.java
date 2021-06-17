package com.list.utils.http;

import okhttp3.*;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.http.RealInterceptorChain;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyOkHttpClient {

    /**
     * 同步get请求
     */
    public void get(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cpro.baidustatic.com/cpro/ui/cm.js")
                .headers(Headers.of("Referer","http://pcms.jd.com/go/left","User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"))
                .get()
                .build();
        System.out.println(request.headers());
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步get请求
     */
    public void asynGet(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cpro.baidustatic.com/cpro/ui/cm.js")
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("call failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 子线程执行
     * 主线程执行也没错（主要针对Android3.0以后不允许主线程执行）
     */
    public void getInSubThread(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cpro.baidustatic.com/cpro/ui/cm.js")
                .get()
                .build();
        Call call = client.newCall(request);
        new Thread(()->{
            try {
                Response response = call.execute();
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * post请求简单数据
     */
    public void postString(){
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String postString = "I am jary";
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, postString))
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        try {
            Response response =  call.execute();
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++){
                System.out.println(headers.name(i) + " : " + headers.value(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("call failure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++){
                    System.out.println("header = " + headers.name(i) + ", value = " + headers.value(i));
                }
            }
        });*/
    }


    public void api(){
        OkHttpClient client = new OkHttpClient();
        OkHttpClient client1 = client.newBuilder().build();
        OkHttpClient client2 = new OkHttpClient.Builder().build();

        client.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        }).cache(new Cache(new File("/Users/lisongtao/cachDir"), 10))
          .readTimeout(100, TimeUnit.SECONDS).build();
    }

    /**
     * 缓存
     */
    public void cache(){
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(new File("/Users/lisongtao/export/cache"), 10))
                .build();
        Request request = new Request.Builder()
                .url("https://cpro.baidustatic.com/cpro/ui/cm.js")
                .get()
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 连接池
     * 相同的url
     *   静态文件(且缓存下来)获取和post获取数据不同,前者使用一个连接，后者使用多个连接
     */
    public void poolWithSameUrl(){
        ConnectionPool pool = new ConnectionPool(5, 10, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();
        System.out.println("client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount());
        System.out.println("client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());

        //获取完全相同的静态文件
        Request request = new Request.Builder()
                .url("https://dss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/components/tips-e2ceadd14d.js")
                .get()
                .build();
        Request request1 = new Request.Builder()
                .url("https://dss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/components/tips-e2ceadd14d.js")
                .get()
                .build();
        Request request2 = new Request.Builder()
                .url("https://dss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/components/tips-e2ceadd14d.js")
                .get()
                .build();
        //认为是不同请求
        /*Request request = new Request.Builder()
                .url("http://pcms.jd.com/config/getConfigListByTypeId?typeId=1025&token=aaeaa276b43a4e7eb043206a30f27dd1")
                .get()
                .build();
        Request request1 = new Request.Builder()
                .url("http://pcms.jd.com/config/getConfigListByTypeId?typeId=1025&token=aaeaa276b43a4e7eb043206a30f27dd1")
                .get()
                .build();
        Request request2 = new Request.Builder()
                .url("http://pcms.jd.com/config/getConfigListByTypeId?typeId=1025&token=aaeaa276b43a4e7eb043206a30f27dd1")
                .get()
                .build();*/
        Call call = client.newCall(request);
        Call call1 = client.newCall(request1);
        Call call2 = client.newCall(request2);
        try {
            Response response = call.execute();
            Response response1 = call1.execute();
            Response response2 = call2.execute();
            System.out.println("client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount());
            System.out.println("client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接池
     * 不同的请求Url使用相同connection
     */
    public void poolWithDiffUrl(){
        ConnectionPool pool = new ConnectionPool(2, 10, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();
        System.out.println("before call - client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount());
        System.out.println("before call - client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
        Request request = new Request.Builder()
                .url("https://cpro.baidustatic.com/cpro/ui/cm.js")
                .get()
                .build();
        Request request1 = new Request.Builder()
                .url("https://dss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/js/components/tips-e2ceadd14d.js")
                .get()
                .build();
        Request request2 = new Request.Builder()
                .url("https://passport.baidu.com/passApi/js/wrapper.js?cdnversion=1622712586346&_=1622712585946")
                .get()
                .build();

          //认为是不同请求,虽然是获取静态文件(没有缓存静态文件)
/*        Request request = new Request.Builder()
                .url("https://passport.baidu.com/passApi/js/wrapper.js?cdnversion=1622712586346&_=1622712585946")
                .get()
                .build();
        Request request1 = new Request.Builder()
                .url("https://passport.baidu.com/passApi/js/wrapper.js?cdnversion=1622712586346&_=1622712585946")
                .get()
                .build();
        Request request2 = new Request.Builder()
                .url("https://passport.baidu.com/passApi/js/wrapper.js?cdnversion=1622712586346&_=1622712585946")
                .get()
                .build();*/

        //认为是不同的请求
 /*     Request request = new Request.Builder()
                .url("http://pcms.jd.com/config/getConfigListByTypeId?typeId=1025&token=aaeaa276b43a4e7eb043206a30f27dd1")
                .get()
                .build();
        Request request1 = new Request.Builder()
                .url("http://pcms.jd.com/config/getConfigListByTypeId?typeId=2241&token=a45bbdd194e76ee5de2da09f12c111f7")
                .get()
                .build();
        Request request2 = new Request.Builder()
                .url("http://pcms.jd.com/config/getConfigListByTypeId?typeId=800&token=a150464555a0f15c008cce38f11622e5")
                .get()
                .build();*/
        Call call = client.newCall(request);
        Call call1 = client.newCall(request1);
        Call call2 = client.newCall(request2);
        try {
            Response response = call.execute();
            Response response1 = call1.execute();
            Response response2 = call2.execute();
            System.out.println("after call - client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount());
            System.out.println("after call - client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
            System.out.println(response.body().string());
            Thread.sleep(3000);
            System.out.println("in keepalive - client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount());
            System.out.println("in keepalive - client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
            Thread.sleep(20000);
            System.out.println("over keepalive - client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount());
            System.out.println("over keepalive - client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
            Call recall = client.newCall(request);
            Call recall1 = client.newCall(request1);
            Call recall2 = client.newCall(request2);
            recall.execute();
            recall1.execute();
            recall2.execute();
            System.out.println("over keepalive recall - client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount());
            System.out.println("over keepalive recall - client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 多次调用观察连接池情况
     * response中Connection:close处理
     */
    public void poolLoop1(){
        ConnectionPool pool = new ConnectionPool(2, 1200, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                //不起作用，因为intercept顺序原因，还是会执行现在CallServerInterceptor的服务端长连接header判断进行清除连接
                /*.addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        RealInterceptorChain realChain = (RealInterceptorChain) chain;
                        Request request = realChain.request();
                        Response response = realChain.proceed(request);
                        response = response.newBuilder().header("Connection", "keep-alive").build();
                        return response;
                    }
                })*/
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("start","0")
                .add("length","10")
                .add("draw","3")
                .add("typeId","1025")
                .build();
        Request request = new Request.Builder()
                .url("http://11.50.69.3:8001/config/showNewConfigs")
                .addHeader("Cookie", "sso.jd.com=BJ.2DB82B60E0008F7DEF105829FD29E63E7820210617090750")
                .post(formBody)
                .build();

        try {
            for (int i = 0; i < 100; i++) {
                Call call = client.newCall(request);
                Response response = call.execute();
                //1、如果没有获取结果，会一直创建新连接，推断:原因是没有读取响应数据(流中读取),连接不会释放
                //2、获取结果也创建连接，因为连接每次使用完被关闭？？ 根本原因是测试调用服务端response的header要求关闭Connection:closed
                /**
                 * 类CallServerInterceptor
                 *     if ("close".equalsIgnoreCase(response.request().header("Connection"))
                 *         || "close".equalsIgnoreCase(response.header("Connection"))) {
                 *       exchange.noNewExchangesOnConnection();
                 *     }
                 */
                //System.out.println(response.body().string());
                response.body().string();
                System.out.println("client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount() + ", client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 多次调用观察连接池情况
     */
    public void poolLoop2(){
        ConnectionPool pool = new ConnectionPool(2, 1200, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("systemEnv","2")
                .add("columnId","1")
                .add("orderId","205072494796")
                .add("flowId","-1")
                .build();
        Request request = new Request.Builder()
                .url("http://yf.manage.jd.local/manage/findPurchaseTaskInfo.action")
                .addHeader("Cookie", "sso.jd.com=BJ.4A4F2343D065F96EEB42F33FC1C4057D6020210615180449")
                .post(formBody)
                .build();

        try {
            for (int i = 0; i < 100; i++) {
                Call call = client.newCall(request);
                Response response = call.execute();
                //1、如果没有获取结果，会一直创建新连接，推断:原因是没有读取响应数据(流中读取),连接不会释放
                //2、获取结果也创建连接，因为连接每次使用完被关闭？？ 根本原因是测试调用服务端response的header要求关闭Connection:closed
                /**
                 * 类CallServerInterceptor
                 *     if ("close".equalsIgnoreCase(response.request().header("Connection"))
                 *         || "close".equalsIgnoreCase(response.header("Connection"))) {
                 *       exchange.noNewExchangesOnConnection();
                 *     }
                 */
                //System.out.println(response.body().string());
                response.body().string();
                System.out.println("client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount() + ", client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步，对比查看连接池情况
     */
    public void poolLoopAsyn(){
        ConnectionPool pool = new ConnectionPool(2, 1200, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("systemEnv","2")
                .add("columnId","1")
                .add("orderId","205072494796")
                .add("flowId","-1")
                .build();
        Request request = new Request.Builder()
                .url("http://yf.manage.jd.local/manage/findPurchaseTaskInfo.action")
                .addHeader("Cookie", "sso.jd.com=BJ.4A4F2343D065F96EEB42F33FC1C4057D6020210615180449")
                .post(formBody)
                .build();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() ->{
                try {
                    Call call = client.newCall(request);
                    Response response = call.execute();
                    //1、如果没有获取结果，会一直创建新连接，推断:原因是没有读取响应数据(流中读取),连接不会释放
                    //2、获取结果也创建连接，因为连接每次使用完被关闭？？ 根本原因是测试调用服务端response的header要求关闭Connection:closed
                    /**
                     * 类CallServerInterceptor
                     *     if ("close".equalsIgnoreCase(response.request().header("Connection"))
                     *         || "close".equalsIgnoreCase(response.header("Connection"))) {
                     *       exchange.noNewExchangesOnConnection();
                     *     }
                     */
                    //System.out.println(response.body().string());
                    response.body().string();
                    System.out.println("client.connectionPool().connectionCount() = " + client.connectionPool().connectionCount() + ", client.connectionPool().idleConnectionCount() = " + client.connectionPool().idleConnectionCount());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * header
     */
    public void header(){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", "http://pcms.jd.com/go/left");
        headerMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        Request request = new Request.Builder()
                .url("https://cpro.baidustatic.com/cpro/ui/cm.js")
                //单个添加方式一
                //.addHeader("Referer", "http://pcms.jd.com/go/left")
                //.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                //单个添加方式二
                //.header("Referer", "http://pcms.jd.com/go/left")
                //.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                //批量添加方式一
                .headers(Headers.of(headerMap))
                //批量添加方式二
                //.headers(Headers.of("Referer","http://pcms.jd.com/go/left","User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"))
                .get()
                .build();
        System.out.println(request.headers());
        System.out.println(request.headers().size());
        System.out.println(request.headers().name(0));
        System.out.println(request.headers().get("Referer"));
        System.out.println(request.headers().toMultimap());
    }

    /**
     * 配置管理测试环境
     */
    public void postPCMS(){
        ConnectionPool pool = new ConnectionPool(2, 1200, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();
        //MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

        // 可封装工具
        FormBody formBody = new FormBody.Builder()
                .add("typeId","1025")
                .add("draw","3")
                .add("start","0")
                .add("length","10")
                .build();
        Request request = new Request.Builder()
                .url("http://test.pcms.jd.local/config/showNewConfigs")
                .post(formBody)
                .addHeader("Cookie", "sso.jd.com=BJ.408EA9BE058FEA814D1067D378F124944220210615171815")
                //.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .build();

        try {
            for (int i = 0; i < 3; i++) {
                Call call = client.newCall(request);
                Response response = call.execute();
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
