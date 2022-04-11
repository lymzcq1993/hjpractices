package com.hujian.concurrent.completeablefuteure;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CompletionServiceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        completionService.submit(()->{
            Thread.sleep(10000);
            return "我查询到A的价格了";
        });

        completionService.submit(()->{
            Thread.sleep(5000);
            return "我查询到B的价格了";
        });

        completionService.submit(()->{
            Thread.sleep(2000);
            return "我查询到C的价格了";
        });
        for (int i = 0; i < 3; i++) {
            System.out.println(completionService.take().get());
        }
    }
}
