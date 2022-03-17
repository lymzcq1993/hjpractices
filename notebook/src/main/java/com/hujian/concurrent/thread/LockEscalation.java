package com.hujian.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁
 */
@Slf4j
public class LockEscalation {
    public static void main(String[] args) throws InterruptedException {
        testEscalation();
    }

    public static void testEscalation() throws InterruptedException {
        Thread.sleep(5000);
        final Object obj = new Object();
        log.debug(ClassLayout.parseInstance(obj).toPrintable());
        new Thread(() -> {
            log.debug("开始{}",Thread.currentThread().getName());
            synchronized (obj) {
                log.debug(Thread.currentThread().getName()+"拿到了锁");
                log.debug(ClassLayout.parseInstance(obj).toPrintable());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug(Thread.currentThread().getName()+"释放锁");
        }, "Thread1").start();

        Thread.sleep(1);

        new Thread(() -> {
            log.debug(Thread.currentThread().getName()+"开始");
            synchronized (obj) {
                log.debug(Thread.currentThread().getName()+"拿到了锁");
                log.debug(ClassLayout.parseInstance(obj).toPrintable());
            }
        }, "Thread2").start();

//        Thread thread3 = new Thread(() -> {
//            synchronized (obj) {
//                log.debug(Thread.currentThread().getName()+"拿到了锁");
//                log.debug(ClassLayout.parseInstance(obj).toPrintable());
//            }
//        }, "Thread3");
//        thread1.start();
//        Thread.sleep(4000);
//        thread2.start();
//        thread3.start();
        Thread.sleep(5000);
    }


}

