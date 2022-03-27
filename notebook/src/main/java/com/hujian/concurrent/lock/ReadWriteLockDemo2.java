package com.hujian.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 降级
 */
public class ReadWriteLockDemo2 {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock readLock = rwl.readLock();
    private final Lock writeLock = rwl.writeLock();
    private volatile boolean update = false;

    public static void main(String[] args) {
        new ReadWriteLockDemo2().processData();
    }

    public void processData() {
        writeLock.lock();
        try {
            update = true;
            readLock.lock();
        }finally {
            writeLock.unlock();
        }
        try {
            //如果没有上面的读锁，假设在写锁解锁的时候，有另一个线程进来修改了update的值
            //而这个业务场景是分段式的，会导致这里的取值取的不是上面代码修改后的值，导致了脏读
            //因此降级在这种场景下是有必要的
            boolean s = update;
        }finally {
            readLock.unlock();
        }
    }
}
