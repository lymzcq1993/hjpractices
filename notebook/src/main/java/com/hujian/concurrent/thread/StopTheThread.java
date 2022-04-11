package com.hujian.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 终止线程
 * @author hujian
 */
public class StopTheThread {
    public static volatile boolean started = false;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //保证只有一个线程运行
            if (started){
                return;
            }
            started = true;
            //需要扫描数据库中未完成的数据
            while(!Thread.currentThread().isInterrupted()){
                //开始扫描，假设耗时3s
                try {
                    System.out.println("开始进行扫描");
                    Thread.currentThread().interrupt();
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    //如果在调用sleep()之前已经调用interrupt()，sleep会将中断重置成false，所以这里需要将中断标识处理成正常的标志位告知循环已经可以跳出
                    Thread.currentThread().interrupt();
//                    Thread.currentThread().interrupt();
                }
            }
            started = false;
        });
        thread.start();
//        以下集中方法不能使用，会导致线程的后续逻辑都无法处理。例如释放锁
//        thread.stop();
//        使用linux kill命令

        TimeUnit.DAYS.sleep(1);
    }
}
