package com.yao.ssyx;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: completableFutureDemo3
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 16:05
 */
public class completableFutureDemo3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("主线程启动!!!!!");
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread());
            int result = 11/0;
            System.out.println("result = " + result);
        }, executorService).whenComplete((res,exception)->{
            System.out.println("res = " + res);
            System.out.println("exception = " + exception);
        });
        System.out.println("主线程G!!!!");
    }
}
