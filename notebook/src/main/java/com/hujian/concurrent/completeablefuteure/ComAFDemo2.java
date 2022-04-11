package com.hujian.concurrent.completeablefuteure;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 描述and关系
 * @author hujian
 *
 */
public class ComAFDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runAfterBothTest();
    }

    public static void combineTest(){
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
            System.out.println("我是第一个future，返回5");
            return 5;
        });

        CompletableFuture<Integer> future2 = future1.thenCompose(str -> CompletableFuture.supplyAsync(() -> {
            System.out.println("我是第二个future，返回11");
            return 11;
        }));

        CompletableFuture<Integer> future3 = future1.thenCombine(future2, (x, y) -> {
            return x + y;
        });

        System.out.println(future3.join());
    }

    public static void thenAccepetBothTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
            System.out.println("我是第一个future，返回5");
            return 5;
        });

        CompletableFuture<Integer> future2 = future1.thenCompose(str -> CompletableFuture.supplyAsync(() -> {
            System.out.println("我是第二个future，返回11");
            return 11;
        }));

        //thenAcceptBoth会将结果直接在这里消耗，而不会返回通过返回值拿到
        CompletableFuture<Void> future3 = future1.thenAcceptBoth(future2, (x, y) -> {
            System.out.println(x + y);
        });

        System.out.println(future3.get());
    }


    /**
     * 因为runnable没有返回值，所以这里适用与C依赖AB都完成，而不关心AB返回
     */
    public static void runAfterBothTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("正在执行第一个任务");
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("正在执行第二个任务");
        });

        CompletableFuture<Void> future3 = future1.runAfterBoth(future2, () -> {
            System.out.println("第一和第二个任务执行完了，开始执行第三个任务");
        });

        future3.get();
    }
}
