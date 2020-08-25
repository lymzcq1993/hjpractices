package hujian.jmm;

import org.apache.log4j.Logger;

public class JMM_CodeVisible {
	private static boolean initFlag = false;
	private static int counter = 0;
	private static Logger log = Logger.getLogger(JMM_CodeVisible.class); 
	static void refesh() {
		log.debug("refresh initFlag true");
		initFlag = true;
		log.debug("refresh success");
	}
	public static void main(String[] args) {
		 Thread ta = new Thread(()->{
			while(!initFlag) {
				counter++;
			}
			log.debug("线程:"+Thread.currentThread().getName()+"的initFlag的状态已经改变....为{}");
		 },"threadA");
		 ta.start();
		 try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Thread tb = new Thread(()->{
			JMM_CodeVisible.refesh();
		 },"threadB");
		 tb.start();
		 
	}
	
}
