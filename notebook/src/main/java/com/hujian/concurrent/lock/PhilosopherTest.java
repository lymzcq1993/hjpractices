package com.hujian.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hujian
 * @since 2022-03-22 16:38
 */
public class PhilosopherTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock c1 = new ReentrantLock();
        ReentrantLock c2 = new ReentrantLock();
        ReentrantLock c3 = new ReentrantLock();
        ReentrantLock c4 = new ReentrantLock();
        ReentrantLock c5 = new ReentrantLock();

        Condition w1 = c1.newCondition();
        Condition w2 = c2.newCondition();
        Condition w3 = c3.newCondition();
        Condition w4 = c4.newCondition();
        Condition w5 = c5.newCondition();


        new Philosopher("一号",c1,c2,w2).start();
        new Philosopher("二号",c2,c3,w3).start();
        new Philosopher("三号",c3,c4,w4).start();
        new Philosopher("四号",c4,c5,w5).start();
        new Philosopher("五号",c5,c1,w1).start();

        Thread.sleep(999999999);
    }

    static class Philosopher extends Thread{
        private ReentrantLock left;
        private ReentrantLock right;
        private Condition w;
        public Philosopher(String name,ReentrantLock left,ReentrantLock right,Condition w){
            super(name);
            this.w = w;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            for (;;){
                System.out.println(Thread.currentThread().getName()+"准备拿左筷子");
                left.lock();
                try {
                    think();
                    System.out.println(Thread.currentThread().getName()+"准备拿右筷子");
                    right.lock();
                    try {
                        System.out.println(Thread.currentThread().getName()+"拿到了右筷子");
                    }
                    finally {
                        right.unlock();
                    }
                } finally {
                    left.unlock();
                    System.out.println(Thread.currentThread().getName()+"吃完了");
                    w.signal();
                }

            }
        }

        private void think(){
            System.out.println(Thread.currentThread().getName()+"开始思考");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
