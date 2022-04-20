package com.hujian.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockTest {
    static int a = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        for(int i= 0;i<10;i++){
            new Thread(()->{
                for(int j =0;j<100000;j++){
                    while (!lock.tryLock()){}
                    a++;
                    lock.unlock();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(a);
    }
}
