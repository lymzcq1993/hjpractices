package com.hujian.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 拿多少锁资源 释放多少资源
 * @author 35918
 *
 */
public class Semaphore_07Runner {
	public static void main(String[] args) throws InterruptedException {
		Semaphore s = new Semaphore(10);
		ReentrantLock lock =new ReentrantLock();
		s.acquireUninterruptibly(10);
		s.acquire(10);
	}

}
