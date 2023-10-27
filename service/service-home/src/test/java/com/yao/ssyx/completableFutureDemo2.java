package com.yao.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: completableFutureDemo2
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 15:50
 */
public class completableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("主线程启动！！！！！ " );

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread());
            int result = 1024;
            System.out.println("result = " + result);
            return result;
        }, executorService);

        Integer integer = integerCompletableFuture.get();
        System.out.println("integer = " + integer);

    }


}
