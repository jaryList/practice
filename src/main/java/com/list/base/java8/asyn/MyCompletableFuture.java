package com.list.base.java8.asyn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyCompletableFuture {

    public static void main(String[] args) {
        CompletableFuture<Double> completableFuture = exceptionNeverReturn();
        try {
            double price = completableFuture.get();
           //double price = completableFuture.get(2, TimeUnit.SECONDS);
            System.out.println(price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }/*catch (TimeoutException e){
            e.printStackTrace();
        }*/
    }

    private static void delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
        }
    }

    private static double calculatePrice(){
        delay();
        int i = 10 / 0;
        return 50.0;
    }

    public static CompletableFuture<Double> exceptionNeverReturn(){
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice();
            System.out.println("thread, price = " + price);
            completableFuture.complete(price);
            /*try {
                double price = calculatePrice();
                System.out.println("thread, price = " + price);
                completableFuture.complete(price);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }*/
        }).start();

        return completableFuture;
    }
}
