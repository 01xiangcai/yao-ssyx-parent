package com.yao.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: completabbleFutureDemo5
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 16:23
 */
public class completableFutureDemo5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        System.out.println("主线程启动！！！！！ " );

        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread());
            int result = 1024;
            System.out.println("resultB = " + result);
            return result;
        }, executorService);

        CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread());
            int result = 2048;
            System.out.println("resultB = " + result);
            return result;
        }, executorService);

        CompletableFuture.anyOf(futureA, futureB);

        System.out.println("主线程结束！！！！！ " );


    }
}
