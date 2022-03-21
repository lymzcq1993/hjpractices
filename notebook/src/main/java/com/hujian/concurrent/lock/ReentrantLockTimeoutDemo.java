package com.hujian.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 超时锁demo
 */
public class ReentrantLockTimeoutDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"结束释放锁");
            }
        },"Thread1").start();

        new Thread(()->{
            try {
                if (!lock.tryLock(500, TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName()+"等的不耐烦了不干了");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行");
            }
            finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"结束释放锁");
            }
        },"Thread2").start();
        Thread.sleep(3000);

    }

}
