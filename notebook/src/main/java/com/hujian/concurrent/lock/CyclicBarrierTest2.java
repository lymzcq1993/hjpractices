package com.hujian.concurrent.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环发车
 * @author hujian
 * @since 2022-03-22 16:13
 */
public class CyclicBarrierTest2 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("开始发车咯");
        });

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"上车了");
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"gogogo");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"Thread"+i).start();
            if((i+1)%5 ==0){
                Thread.sleep(2000);
            }
        }
    }
}
