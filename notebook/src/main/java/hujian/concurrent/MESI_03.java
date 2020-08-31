package hujian.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 缓存一致性协议MESI
 * 当数据执行自加的时候，因为操作并不是原子性所以当临界资源(count)被不断抢夺后会造成数据的覆盖
 * 例如count++ ，count先要去缓存中取值，然后赋值进行加法，再把结果写回到内存，而这个时候volatile修饰只能告知其余线程count=1这一步失效了，
 * 而加法操作和写回内存操作有可能已经进入寄存器了（MESI协议无法作用到寄存器），此时写回内存造成值的覆盖。需要使用synchroized修饰加锁
 * @author 35918
 *
 */
public class MESI_03 {
	static final Logger log = LoggerFactory.getLogger(MESI_03.class);
	public  volatile static int count = 0; 
	private volatile int mcount = 1;
	private static Object o = new Object();
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch(1);
		for(int i=0;i<10;i++) {
			new Thread(()-> {
				try {
					cdl.await();
					for(int j=0;j<1000;j++) {
						synchronized (o) {
							count++;
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();;				
		}
		//TimeUnit.MILLISECONDS.sleep(1000);
		cdl.countDown();
		TimeUnit.MILLISECONDS.sleep(2000);
		System.out.println("最终count"+count);
	}
}
