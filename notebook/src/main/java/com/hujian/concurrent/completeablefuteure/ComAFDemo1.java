package com.hujian.concurrent.completeablefuteure;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author hujian
 */
@Slf4j
public class ComAFDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApplyTest();
    }

    /**
     * thenApply返回的是同一个CompletableFuture，使用的上一个异步任务的返回结果作为入参
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void thenApplyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "第一个任务执行完毕";
        }).thenApply(last -> {
            System.out.println(last);
            return "第二个任务执行完毕";
        });
        System.out.println(future.get());
    }

    /**
     * thenCompose返回的是不同的CompletableFuture，使用的上一个CompletableFuture的出参作为入参
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void thenComposeTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
//            System.out.println("第一个Future");
            return "第一个Future";
        });

        CompletableFuture<String> future2 = future1.thenCompose(str -> CompletableFuture.supplyAsync(() -> {
            return str;
        }));
        log.debug("future1:{},get:{}",future1,future1.get());
        log.debug("future2:{},get:{}",future2,future2.get());
    }
}
