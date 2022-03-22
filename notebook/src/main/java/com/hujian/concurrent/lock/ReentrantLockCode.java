package com.hujian.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCode {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行");
            }finally {
                lock.unlock();
            }
        },"Thread1").start();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行");
            }finally {
                lock.unlock();
            }
        },"Thread2").start();


        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行");
            }finally {
                lock.unlock();
            }
        },"Thread3").start();
    }
}
