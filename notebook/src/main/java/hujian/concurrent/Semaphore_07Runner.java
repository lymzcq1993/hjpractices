package hujian.concurrent;

import java.util.concurrent.Semaphore;

public class Semaphore_07Runner {
	public static void main(String[] args) throws InterruptedException {
		Semaphore s = new Semaphore(10);
		s.acquire();
		s.release();
	}

}
