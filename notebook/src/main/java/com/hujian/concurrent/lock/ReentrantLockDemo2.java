package com.hujian.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入性测试
 */
public class ReentrantLockDemo2 {
    public static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        t1();

    }

    public static void t1(){
        lock.lock();
        try {
            System.out.println("测试可重入代码块1");
            t2();
        }
        finally {
            lock.unlock();
        }
    }

    public static void t2(){
        lock.lock();
        try {
            System.out.println("测试可重入代码块2");
            t3();
        }
        finally {
            lock.unlock();
        }
    }

    public static void t3(){
        lock.lock();
        try {
            System.out.println("测试可重入代码块3");
        }
        finally {
            lock.unlock();
        }
    }
}
