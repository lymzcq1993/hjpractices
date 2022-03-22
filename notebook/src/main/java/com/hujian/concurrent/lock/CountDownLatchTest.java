package com.hujian.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author hujian
 * @since 2022-03-22 15:30
 */
public class CountDownLatchTest {
//    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        for (int i = 0; i < 5; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName()+"准备执行");
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+"起跑！");
//            },"Thread"+i).start();
//        }
//        Thread.sleep(2000);
//        countDownLatch.countDown();
//        Thread.sleep(5000);
//    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        new Thread(()->{
            try {
                System.out.println("等待玩家准备好");
                countDownLatch.await();
                System.out.println("所有玩家已准备好，开始比赛");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+"准备好了");
            },"玩家"+i).start();
            Thread.sleep(1000);
        }

        Thread.sleep(999999);
    }
}
