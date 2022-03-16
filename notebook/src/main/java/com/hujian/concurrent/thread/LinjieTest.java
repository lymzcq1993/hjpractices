package com.hujian.concurrent.thread;

public class LinjieTest {
    public static void main(String[] args) throws InterruptedException {
        criticalSection();
    }
    static int a = 0;

    /**
     * 结果有很多种，反而我们得到正确结果0的概率是最小的。
     * 因为a++和a--的操作并不是代码显示的那样只有一行的，在class文件中a++操作并非原子的，而是需要多步的
     * 即使将a用volatile修饰只能保证可见性，但是仍然没法保证原子性
     * @throws InterruptedException
     */
    public synchronized static  void criticalSection() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                // 9 getstatic #10 <com/hujian/concurrent/thread/LinjieTest.a : I>  获取变量a
                //12 iconst_1     压入操作数栈
                //13 iadd     自增
                //14 putstatic #10 <c   设置变量的值
                a++;

            }
            ;
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                // 9 getstatic #10 <com/hujian/concurrent/thread/LinjieTest.a : I>  获取变量a
                //12 iconst_1     压入操作数栈
                //13 isub     自减
                //14 putstatic #10 <c   设置变量的值
                a--;
            }
            ;
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(a);
    }
}
