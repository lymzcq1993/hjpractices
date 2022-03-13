package com.hujian.concurrent;

/**
 * volatile禁止指令重排
 * @author 35918
 *
 */
public class JMM_02_CodeAtomic {
	private volatile static int a = 0,b=0;
	private volatile static int x = 0,y=0;
	public static void main(String[] args) throws InterruptedException {
		int i=0;
		for(;;) {
			i++;
			x=0;y=0;
			a=0;b=0;
			Thread ta = new Thread(()->{
				x = b;
				a = 1;
			});

			Thread tb = new Thread(()->{
				y = a;
				b = 1;
			});			
			ta.start();
			tb.start();
			
			ta.join();
			tb.join();
			System.out.println("x:"+x+"y:"+y);
			//b=1 和a=x交换位置 则是指令重排
			if(x == 1 && y==1) {
				System.out.println("指令重排...停止");
				break;
			}
		}
		 
	}
	
}
