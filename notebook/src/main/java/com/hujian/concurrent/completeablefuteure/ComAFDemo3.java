package com.hujian.concurrent.completeablefuteure;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 描述and关系
 * @author hujian
 *
 */
public class ComAFDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runAfterEitherTest();
    }

    /**
     * 用于获取先返回结果的那个CompletableFuture
     */
    public static void applyToEitherTest(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = "502公交";
            return s;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = "603公交";
            return s;
        });

        CompletableFuture<String> future3 = future1.applyToEither(future2, s -> {
            return s + "先到站了，我先撤了";
        });

        System.out.println(future3.join());
    }


    /**
     * 这里是会消耗执行的快的结果，所以返回值获取到的值是null
     */
    public static void acceptEitherTest(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = "502公交";
            return s;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = "603公交";
            return s;
        });

        CompletableFuture<Void> future3 = future1.acceptEither(future2, s -> {
            System.out.println(s + "先到站了，我先撤了");
        });

        System.out.println(future3.join());
    }


    /**
     * 因为runnable没有返回值，所以这里适用与C依赖A或B完成其中一个，而不关心A或B返回
     */
    public static void runAfterEitherTest() throws ExecutionException, InterruptedException {
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

        CompletableFuture<Void> future3 = future1.runAfterEither(future2, () -> {
            System.out.println("有一个执行完了，开始执行第三个任务");
        });

        future3.get();
    }

}
