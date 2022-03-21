package com.hujian.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 {
    static int a = 0;
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                lock.lock();
                try {
                    a++;
                }
                finally {
                    lock.unlock();
                }

            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                lock.lock();
                try {
                    a++;
                }
                finally {
                    lock.unlock();
                }

            }
        }).start();
        Thread.sleep(3000);
        System.out.println(a);

    }

}
