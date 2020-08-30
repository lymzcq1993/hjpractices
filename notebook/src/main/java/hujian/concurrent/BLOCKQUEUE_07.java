package hujian.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BLOCKQUEUE_07 {
	static final Logger log = LoggerFactory.getLogger(BLOCKQUEUE_07.class);
	
	private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
	
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch(1);
		
		//创建一个武大郎吃药
		Thread t = new Thread(()-> {
			try {
				cdl.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			BLOCKQUEUE_07.Wudalang.eat();
		} );
		t.start();
		for(int i =0;i<20;i++) {
			Thread thread = new Thread(()-> {
				try {
					cdl.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}	
				try {
					//log.info("潘金莲{} 号开始送药",Thread.currentThread().getName());
					BLOCKQUEUE_07.Panjinlian.putPill(false);
					log.info("潘金莲{} 送药完毕",Thread.currentThread().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},"潘金莲"+i+"号");
			
			thread.start();
		}
		log.info("潘金莲集合开始给武大郎喂药....");
		cdl.countDown();
		
	}
	
	public static class Wudalang{
		private static Integer posionPill = 101;
		
		public static void eat() {
				try {
					for(;;) {
						Integer pill = queue.take();
						if(pill == posionPill) {
							log.info("武大郎 {} 号吃到毒药挂了",Thread.currentThread().getName());
							break;
						}
						log.info("武大郎 {} 正常吃药 {}",Thread.currentThread().getName(),pill);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error("线程 {} 被异常中断",Thread.currentThread().getName());
				}				
		}
		
	}
	
	public static class Panjinlian{
		
		public static void putPill(boolean isPosion) throws InterruptedException {
			if(isPosion) {
				queue.put(Wudalang.posionPill);
			}
			else {
				queue.put((int)Math.random() *100-1);
			}
		}
		
	}
}
