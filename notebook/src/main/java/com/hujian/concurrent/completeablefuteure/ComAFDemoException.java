package com.hujian.concurrent.completeablefuteure;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 描述and关系
 * @author hujian
 *
 */
public class ComAFDemoException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> cf = CompletableFuture.runAsync(()->{
//            int i = 10/0;
//        });
//        cf.exceptionally(a ->{
//            System.out.println("触发了异常"+a.getMessage());
//            return null;
//        });
//
//        System.out.println(cf.join());



        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(()->{
            int i = 10/0;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  233;
        });
        //如果有异常先执行此处逻辑
        cf2.exceptionally(a ->{
            System.out.println("触发了异常"+a.getMessage());
            return 333;
        });

        //handle多了映射的功能，可以将返回值进行map
        CompletableFuture<String> handle = cf2.handle((i, t) -> {
            return String.valueOf(i);
        });
        System.out.println("handle的输出"+handle.get());

        //有处理异常和接受返回值的功能
        cf2.whenComplete((i,t) ->{
            System.out.println("输出结果:"+i);
            System.out.println(t);
        }).join();
    }

}
