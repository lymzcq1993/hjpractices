package com.hujian.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁和延迟偏向,偏向锁默认状态下会延迟4s开启
 * 关闭延迟偏向锁 ‐XX:BiasedLockingStartupDelay=0
 * 禁止偏向锁   ‐XX:‐UseBiasedLocking
 * @author hujian
 * @since 2022-03-17 17:21
 */
@Slf4j
public class BiasedLock {
    public static void main(String[] args) throws InterruptedException {
        hashcodeLock();
    }


    public static void hashcodeLock() throws InterruptedException {
        Thread.sleep(5000);

        Object obj = new Object();
//        obj.hashCode();
        log.debug(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj){
//            obj.hashCode();
//            obj.wait(1000);
            obj.notify();
            log.debug(ClassLayout.parseInstance(obj).toPrintable());
        }
    }

    public static void classLockTest() throws InterruptedException {
        Thread.sleep(5000);
        synchronized (BiasedLock.class){
            log.debug(ClassLayout.parseInstance(BiasedLock.class).toPrintable());
        }

    }


    public static void lockUpdate() throws InterruptedException {
        Thread.sleep(5000);

        Object obj = new Object();
        log.debug(ClassLayout.parseInstance(obj).toPrintable());


        new Thread(()->{
            synchronized (obj){
                log.debug("获取锁");
                log.debug(ClassLayout.parseInstance(obj).toPrintable());
            }
            log.debug("释放锁");
        }).start();

        log.debug(ClassLayout.parseInstance(obj).toPrintable());
        Thread.sleep(100);

        new Thread(()->{
            synchronized (obj){
                log.debug("获取锁");
                log.debug(ClassLayout.parseInstance(obj).toPrintable());
            }
        }).start();

        Thread.sleep(1000);
    }



    public static void initStatus(){
        log.debug(ClassLayout.parseInstance(new Object()).toPrintable());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(ClassLayout.parseInstance(new Object()).toPrintable());
    }

}
