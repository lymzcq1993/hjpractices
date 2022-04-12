package com.hujian.concurrent.completeablefuteure;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeoutException;

/**
 * @author hujian
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            Thread.sleep(3000);
            return "futureTask执行完毕";
        });
        new Thread(futureTask).start();
        //主动取消任务（传参是否立即取消任务或等任务完成），判断任务是否被取消
//        futureTask.cancel(true);
//        futureTask.isCancelled();
        System.out.println("任务是否完成:"+futureTask.isDone());
        //get方法阻塞当前线程直到获取返回的结果
        System.out.println(futureTask.get());
        System.out.println("任务是否完成:"+futureTask.isDone());
//        带有超时的get方法
//        System.out.println(futureTask.get(1, TimeUnit.SECONDS));
    }
}
