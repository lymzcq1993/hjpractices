package com.hujian.concurrent.lock;

import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @author hujian
 * @since 2022-03-22 16:13
 */
public class CyclicBarrierTest {
    static ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>(8);
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            Integer score = 0;
            for (String s : map.keySet()) {
                score += map.get(s);
            }
            System.out.println("总分"+score);
        });

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"准备执行");
                try {
                    map.put(Thread.currentThread().getName(), RandomUtil.randomInt(100));
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"结束！");
            },"Thread"+i).start();
        }
    }
}
