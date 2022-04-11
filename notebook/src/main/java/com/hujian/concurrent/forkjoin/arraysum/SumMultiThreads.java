package com.hujian.concurrent.forkjoin.arraysum;


import com.hujian.concurrent.forkjoin.utils.Utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author hujian
 *
 * 多线程计算1亿个整数的和
 */
public class SumMultiThreads {
    //拆分的粒度，此处设置一千万。
    public final static int NUM = 10000000;

    public static long sum(int[] arr, ExecutorService executor) throws Exception {
        long result = 0;
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;
        int num = arr.length / numThreads;
        //将任务根据粒度进行拆分
        SumTask[] tasks = new SumTask[numThreads];
        Future<Long>[] sums = new Future[numThreads];
        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new SumTask(arr, (i * NUM),
                    ((i + 1) * NUM));
            sums[i] = executor.submit(tasks[i]);
        }
        //结果合并
        for (int i = 0; i < numThreads; i++) {
            result += sums[i].get();
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        // 将一亿个数组随机存储1000以内的任意一个数字
        int[] arr = Utils.buildRandomIntArray(100000000);
        //获取线程数
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;

        System.out.printf("The array length is: %d\n", arr.length);
        // 构建线程池
        //
//        ExecutorService executor = Executors.newCachedThreadPool();
        //如果使用FixedThreadPool执行线程池的数量，如果拆分粒度小，会导致需要依赖的后面的线程的结果
        //但是线程队列又满了而无法创建，导致线程阻塞
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        Instant now = Instant.now();
        // 数组求和
        long result = sum(arr, executor);
        System.out.println("执行时间："+Duration.between(now,Instant.now()).toMillis());

        System.out.printf("The result is: %d\n", result);

        executor.shutdown();

    }


    /**
     * 实现Callable有返回值的接口
     */
    static class SumTask implements Callable<Long> {
        int lo;
        int hi;
        int[] arr;

        /**
         *
         * @param a  数组
         * @param l   低位数下标
         * @param h    高位数下标
         */
        public SumTask(int[] a, int l, int h) {
            lo = l;
            hi = h;
            arr = a;
        }

        @Override
        public Long call() { //override must have this type
            //进行累加操作
            long result = SumUtils.sumRange(arr, lo, hi);
            return result;
        }
    }
}
