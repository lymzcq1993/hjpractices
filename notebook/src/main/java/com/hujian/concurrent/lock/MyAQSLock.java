package com.hujian.concurrent.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 模拟公平锁
 */
public class MyAQSLock extends AbstractQueuedSynchronizer {
    static int a = 0;
    public static void main(String[] args) throws InterruptedException {
        MyAQSLock lock = new MyAQSLock();
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

    /**
     * 这里实现cas获取锁的逻辑
     * @param arg
     * @return
     */
    @Override
    protected boolean tryAcquire(int arg) {
        return compareAndSetState(0, arg);
    }

    public void lock(){
        acquire(1);
    }

    public void unlock(){
        release(0);
    }


    /**
     * 这里是释放锁的逻辑，不需要cas
     * @param arg
     * @return
     */
    @Override
    protected boolean tryRelease(int arg) {
        setState(arg);
        return true;
    }
}
