package com.hujian.concurrent.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hujian
 * @since 2022-03-21 15:49
 */
public class SemaphoreCode {
    /**
     * 物品库存
     */
    static AtomicInteger atomicInteger = new AtomicInteger(1000);

    public static void main(String[] args) {
        //同时允许5个线程进行内存扣减
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"开始买票");
                    Thread.sleep(2000);
                    atomicInteger.decrementAndGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"买完票了释放锁");
                    semaphore.release();
                }
            },"Thread"+i).start();
        }
    }
}
