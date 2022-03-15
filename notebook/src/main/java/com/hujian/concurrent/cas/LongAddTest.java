package com.hujian.concurrent.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 测试性能
 */
public class LongAddTest {
    public static void main(String[] args) {
        useTime(10,1000);
        useTime(100,10000);
        useTime(1000,100000);
    }



    public static void useTime(int threadCount,Integer count){
        atomicLongT(threadCount,count);
        longAddT(threadCount,count);
    }

    public static void atomicLongT(int threadCount,Integer count){
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        AtomicLong atomicLong = new AtomicLong();
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            new Thread(()->{
                for (int j = 0; j < count; j++) {
                    atomicLong.incrementAndGet();
                }
                countDownLatch.countDown();
            },"AtomicLongThread").start();
        }
        try {
            countDownLatch.await();
            System.out.println("AtomicLongThread耗时"+(System.currentTimeMillis()-start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void longAddT(int threadCount,Integer count){
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        LongAdder longAdder = new LongAdder();
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            new Thread(()->{
                for (int j = 0; j < count; j++) {
                    longAdder.add(1);
                }
                countDownLatch.countDown();
            },"LongAdderThread").start();
        }
        try {
            countDownLatch.await();
            System.out.println("LongAdderThread耗时"+(System.currentTimeMillis()-start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
