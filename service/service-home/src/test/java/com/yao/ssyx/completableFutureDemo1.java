package com.yao.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: completableFutureDemo1
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 15:24
 */
public class completableFutureDemo1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("主线程运行  " );

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread());
            int result = 1024;
            System.out.println("result = " + result);
        }, executorService);
    }


}
