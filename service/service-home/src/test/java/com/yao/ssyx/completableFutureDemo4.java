package com.yao.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: completableFutureDemo4
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 16:13
 */
public class completableFutureDemo4 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("主线程启动！！！！！ " );

        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread());
            int result = 1024;
            System.out.println("result = " + result);
            return result;
        }, executorService);

        CompletableFuture<Void> futureB = futureA.thenAcceptAsync((res) -> {
            System.out.println("res = " + res);
            res++;
            System.out.println("res2 = " + res);
        }, executorService);

        CompletableFuture<Integer> futureC = futureA.thenApplyAsync((res -> {
            System.out.println("res = " + res);
            res++;
            System.out.println("res22 = " + res);
            return res;
        }), executorService);

        CompletableFuture<Void> futureD = futureA.thenRunAsync(() -> {
            System.out.println("线程D！！！！！！！！");
        }, executorService);


    }

}
