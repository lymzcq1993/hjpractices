package hujian.concurrent;

import org.openjdk.jol.info.ClassLayout;

public class Synchronized_04_LOCK {
	private static Object o = new Object();
	public static void main(String[] args) throws InterruptedException {
		Object o = new Object();
		new Thread(()-> {
			synchronized (o) {
				System.out.println("---");
				System.out.println(ClassLayout.parseInstance(o).toPrintable());
			}
		}).start();;
				
	}
}
