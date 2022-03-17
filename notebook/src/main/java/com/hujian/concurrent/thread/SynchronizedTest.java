package com.hujian.concurrent.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hujian
 * @since  2022-03-17 16:18
 * 测试synchronized不同状态的锁的升级
 */
@Slf4j
public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        test();
    }
    public static void test() throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj){
                try {
//                    Thread.sleep(300);
                    obj.wait(100);
                    log.debug(Thread.currentThread().getName() + "释放了锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (obj){
                log.debug("{}拿到了锁",Thread.currentThread().getName());
            }
        }, "Thread2");

        Thread thread3 = new Thread(() -> {
            synchronized (obj){
                log.debug("{}拿到了锁",Thread.currentThread().getName());
            }
        }, "Thread3");
        thread.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        thread3.start();
    }
}
