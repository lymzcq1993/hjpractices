package com.hujian.concurrent.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author hujian
 * @since 2022-03-31 16:17
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //默认是最小堆，可以自己实现排序规则
        DelayQueue<Order> priorityBlockingQueue = new DelayQueue<>();

        priorityBlockingQueue.add(new Order(3));
        priorityBlockingQueue.add(new Order(6));
        priorityBlockingQueue.add(new Order(1));


        System.out.println(priorityBlockingQueue.take());
        System.out.println(priorityBlockingQueue.take());
        System.out.println(priorityBlockingQueue.take());

    }

    static class Order implements Delayed{
        private String name;
        private long time;   //延时时间

        public Order(long time){
            this.time  = time;
        }

        @Override
        public String toString() {
            return String.valueOf(time);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = time - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.SECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.time < ((Order) o).time) {
                return -1;
            }
            if (this.time > ((Order) o).time) {
                return 1;
            }
            return 0;
        }
    }
}
