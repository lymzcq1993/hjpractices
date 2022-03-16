package com.hujian.jvm;

/**
 * 模拟死锁
 */
public class DeadLock {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1){
                System.out.println("线程1启动！");
                try {
                    Thread.sleep(3000);
                    synchronized (lock2){
                        System.out.println("我要抢锁2!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread-1");


        Thread t2 = new Thread(() -> {
            synchronized (lock2){
                System.out.println("线程2启动！");
                try {
                    Thread.sleep(3000);
                    synchronized (lock1){
                        System.out.println("我要抢锁1!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread-2");
        t1.start();
        t2.start();
    }
}
