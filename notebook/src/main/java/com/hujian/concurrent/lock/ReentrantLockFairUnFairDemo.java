package com.hujian.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平/非公平DEMO
 */
public class ReentrantLockFairUnFairDemo {
    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock lock = new ReentrantLock(false);     //默认非公平
        ReentrantLock lock = new ReentrantLock(true);  //公平
        for (int i = 0; i < 400; i++) {
            new Thread(()-> {
                lock.lock();
                try {
                    System.out.println("--------------"+Thread.currentThread().getName() + "执行");
                    //此处让队排起来
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            },"Thread"+i).start();
        }
        Thread.sleep(500);
        System.out.println("开始");
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                lock.lock();
                try {
                    System.out.println("++++++++++++++++"+Thread.currentThread().getName() + "执行");
                } finally {
                    lock.unlock();
                }
            },"Thread"+i).start();
        }
        Thread.sleep(3000);

    }

}
