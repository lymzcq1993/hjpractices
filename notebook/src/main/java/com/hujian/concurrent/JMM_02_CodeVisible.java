package com.hujian.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMM_02_CodeVisible {
	//如果线程A中while循环，可能会导致initFlag的修改后的值一直无法从主内存读取更新到工作内存中，造成结果错乱.
	//因为空间连续性的原因，当while循环中只有基本类型的改变（基本数据类型会直接储存在工作内存中，会使线程不会重新从主内存中读取数据）
	//private static boolean initFlag = false;   
	private volatile static boolean initFlag = false;
	private static int counter = 0;
	static final Logger log = LoggerFactory.getLogger(JMM_02_CodeVisible.class);
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
			JMM_02_CodeVisible.refesh();
		 },"threadB");
		 tb.start();
		 
	}
	
}
