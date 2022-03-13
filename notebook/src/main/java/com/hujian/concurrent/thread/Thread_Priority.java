package com.hujian.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 线程池
 * @author 35918
 *
 */
public class Thread_Priority implements Runnable{
	static final Logger log = LoggerFactory.getLogger(Thread_Priority.class);
	private CountDownLatch countDownLatch;
	private Map<String,Integer> map;

	public Thread_Priority(CountDownLatch countDownLatch, Map<String, Integer> map) {
		this.countDownLatch = countDownLatch;
		this.map = map;
	}

	private static Integer ticket = 1000;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		Map<String,Integer> map = new HashMap<>();
		map.put("黄牛1",0);
		map.put("黄牛2",0);
		map.put("黄牛3",0);
		map.put("黄牛4",0);
		Thread_Priority priority = new Thread_Priority(countDownLatch,map);
		Thread thread1 = new Thread(priority,"黄牛1");

		Thread thread2 = new Thread(priority,"黄牛2");

		Thread thread3 = new Thread(priority,"黄牛3");

		Thread thread4 = new Thread(priority,"黄牛4");


		thread1.setPriority(10);
		thread2.setPriority(1);
		thread3.setPriority(10);
		thread4.setPriority(1);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		countDownLatch.countDown();
		Thread.sleep(2000);
		System.out.println(map);
	}


	@Override
	public void run() {
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			while (ticket > 0) {
				synchronized (this){
					ticket--;
					Integer integer = map.get(Thread.currentThread().getName());
					map.put(Thread.currentThread().getName(),++integer);
				}
				Thread.yield();
		}
	}
}
