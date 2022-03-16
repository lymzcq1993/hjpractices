package com.hujian.concurrent;

/**
 * 缓存一致性
 */
public class JMM_02_Cache {
    static int  c = 0;
    public static void main(String[] args) throws InterruptedException {
        Pointer pointer = new Pointer();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
    }

    private static class Pointer{
        volatile long x;
        //填充超过一个cacheline的大小，8byte+56byte
        long a,b,c,d,e,f,g;
        volatile long y;
    }
}
