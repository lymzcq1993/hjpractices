package com.hujian.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 简单实现一个伪ConcurrentHashMap
 */
public class ReadWriteLockDemo1 {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private Map<String,String> map = new HashMap<>();
    public static void main(String[] args) {

    }

    /**
     * 读操作，加读锁，不会被阻塞
     * @param key
     * @return
     */
    public String get(String key){
        reentrantReadWriteLock.readLock().lock();
        String s;
        try {
            s = map.get(key);
        }
        finally {
            reentrantReadWriteLock.readLock().unlock();
        }
        return s;
    }

    /**
     * 写操作，写锁
     * @param key
     * @param value
     */
    public void put(String key,String value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            map.put(key,value);
        }
        finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    /**
     * 写操作，写锁
     */
    public void clear(){
        reentrantReadWriteLock.writeLock().lock();
        try {
            map.clear();
        }
        finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}
