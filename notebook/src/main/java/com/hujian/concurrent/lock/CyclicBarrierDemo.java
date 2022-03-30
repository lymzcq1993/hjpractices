package com.hujian.concurrent.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author hujian
 * @since 2022-03-22 16:13
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"准备执行");
            try {
//                Thread.sleep(5000);
                System.out.println("尝试唤醒");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"结束！");
        },"Thread").start();

        Thread.sleep(3000);
        cyclicBarrier.await();

        System.out.println("停止阻塞");
    }
}
