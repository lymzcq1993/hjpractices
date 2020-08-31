package hujian.concurrent;
/**
 * 测试消费者和生产者
 * @author 35918
 *
 */

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;


public class BLOCKQUEUE_06_CONSUME_PRODUCER {
	static final Logger log = LoggerFactory.getLogger(BLOCKQUEUE_06_CONSUME_PRODUCER.class);
	static BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
	
	public static void main(String[] args) throws InterruptedException {
		queue.add(123);
		queue.add(45);
		
		System.out.println(queue.size());
		
		System.out.println(queue.take());
		System.out.println(queue.size());
		System.out.println(queue.take());
		System.out.println("第三次取"+queue.take());
		System.out.println(queue.size());
		
	}
	
	//消费者
	public class wudalalng implements Runnable{
		private final int posionPill;
		private BlockingQueue<Integer> queue;
		public wudalalng(BlockingQueue<Integer> queue,int poisonPill) {
			this.queue = queue;
			this.posionPill = poisonPill;
		}
		
		@Override
		public void run() {
			while(true) {
				Integer n;
				try {
					n = queue.take();
					if(n == posionPill) {
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
		
	}
	
	public class panjinlian{
		
	}
}
