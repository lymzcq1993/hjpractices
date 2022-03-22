package com.hujian.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockTest {

    public static void test(){
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
    }
}
