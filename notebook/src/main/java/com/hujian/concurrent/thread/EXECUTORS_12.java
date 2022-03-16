package com.hujian.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 线程池
 * @author 35918
 *
 */
public class EXECUTORS_12 {
	static final Logger log = LoggerFactory.getLogger(EXECUTORS_12.class);
	
	private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
	static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		ss();

	}
	
	public static void ss() throws InterruptedException {
		HashMap<String, String> s = new HashMap<String, String>();
		s.containsKey("ddd");
		ConcurrentHashMap<String, String> d = new ConcurrentHashMap<String, String>();
		ScheduledThreadPoolExecutor st = new ScheduledThreadPoolExecutor(2);
		ExecutorService service = Executors.newCachedThreadPool();
		Thread.sleep(5000);
		st.schedule(()->{
			
		}, 1000	, TimeUnit.MILLISECONDS);
		st.scheduleAtFixedRate(()->{
			System.out.println("我是"+Thread.currentThread().getName()+"1号");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, 2000, 2000, TimeUnit.MILLISECONDS);
		
		st.scheduleAtFixedRate(()->{
			System.out.println("我是"+Thread.currentThread().getName()+"2号");
		}, 2000, 2000, TimeUnit.MILLISECONDS);
	}
	
	public static class test implements Runnable{
		@Override
		public void run() {
			System.out.println("sdsd");
		}
	}
	

}
