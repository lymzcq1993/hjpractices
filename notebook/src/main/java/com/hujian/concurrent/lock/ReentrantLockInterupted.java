package com.hujian.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试可中断
 */
public class ReentrantLockInterupted {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try{
                    System.out.println("线程1正常获取到了锁！");
                }finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("线程1中断获取到了锁！");
            }
        },"Thread1");

        lock.lock();
        thread1.start();
        try {
            System.out.println("main线程获得锁");
            thread1.interrupt();
            Thread.sleep(1000);
        }
        finally {
            lock.unlock();
            System.out.println("main线程释放锁");
        }

        Thread.sleep(3000);

    }

}
