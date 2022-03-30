package com.hujian.concurrent.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

/**
 * @author hujian
 * @since 2022-03-30 16:52
 */
@Slf4j
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>(); //非公平
//        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>(true); //公平
        new Thread(()->{
            try {
                log.debug(Thread.currentThread().getName()+"准备消费");
                Integer integer = synchronousQueue.take();
                log.debug(Thread.currentThread().getName()+"消费完毕:{}",integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者1").start();

        Thread.sleep(10);

        new Thread(()->{
            try {
                log.debug(Thread.currentThread().getName()+"准备消费");
                Integer integer = synchronousQueue.take();
                log.debug(Thread.currentThread().getName()+"消费完毕:{}",integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者2").start();

        Thread.sleep(3000);


        new Thread(()->{
            try {
                Integer i = 1;
                synchronousQueue.put(i);
                log.debug(Thread.currentThread().getName()+"生产完毕:{}",i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者1").start();

        Thread.sleep(10);

        new Thread(()->{
            try {
                Integer i = 2;
                synchronousQueue.put(i);
                log.debug(Thread.currentThread().getName()+"生产完毕:{}",i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者2").start();

        Thread.sleep(999999);
    }
}
