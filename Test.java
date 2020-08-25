package hj.prac.concurrent;

import java.util.concurrent.TimeUnit;

//volatile可以保证可见性，有序性。但不能保证原子性，需要在代码块加synchronized关键字
import org.apache.log4j.Logger;

public class Test {
	private static Logger log = Logger.getLogger(Test.class);
	public static int count; 
	
	public static void main(String[] args) throws InterruptedException {
		int num = 0;
		for(int i=0;i<200;i++) {
			count = 0;
			Thread t1 = new Thread(()-> {
				synchronized (Test.class) {
					count++;
					
				}
			});
			Thread t2 = new Thread(()-> {
				synchronized (Test.class) {
					count++;
					
				}
			});		
			
			t1.start();
			t2.start();
			TimeUnit.MILLISECONDS.sleep(10);
			if(count ==1) {
				num++;
			}
			System.out.println("第"+i+"次结果"+count);
		}
		System.out.println("出现1的次数为"+num);
	}
}
