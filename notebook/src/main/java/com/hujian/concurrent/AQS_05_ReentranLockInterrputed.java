package com.hujian.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Thread.interrputed()   返回中断状态并设置interrpyted为初始值false
 * Thread.currentThread().isInterrupted()   返回中断状态，但不会重置
 * @author 35918
 *
 */
public class AQS_05_ReentranLockInterrputed {
	static ReentrantLock lock = new ReentrantLock(false);
	static boolean flag = false;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("dd");
		List<Thread> tList  = new ArrayList<Thread>();
		for(int i =0;i<10;i++) {
			Thread t = new Thread(()->{
				lock.lock();
				while(!flag) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("线程已中断......");
						System.out.println(Thread.interrupted());
						System.out.println(Thread.currentThread().isInterrupted());
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					System.out.println(Thread.currentThread().getName());
				}
				lock.unlock();
			},"t_"+i);
			t.start();
			tList.add(t);
		}
		System.out.println("睡眠...");
		Thread.sleep(2000);
		tList.get(0).interrupt();
		
	}
}
