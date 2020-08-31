package hujian.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Synchronized_04_LOCK {
	private static Object o = new Object();
	public static void main(String[] args) throws InterruptedException {
		Object o = new Object();
		new Thread(()-> {
			synchronized (o) {
				System.out.println("---");
			}
		}).start();;
		List<String> list  = Collections.synchronizedList(new ArrayList<String>());		
		list.add("sdsdsd");
	}
}
