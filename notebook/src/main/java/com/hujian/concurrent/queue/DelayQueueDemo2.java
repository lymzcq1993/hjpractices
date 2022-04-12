package com.hujian.concurrent.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo2 {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Order> delayQueue  =new DelayQueue<>();
        delayQueue.put(new Order(1000L));
        delayQueue.put(new Order(5000L));

        System.out.println(delayQueue.take());
        System.out.println(delayQueue.take());

        Thread.sleep(999999);
    }

    /**
     * 对象需要继承Delayed接口
     * getDelay在内部是要一直调用的，所以需要和当前时间做比对，当时间差=0就会返回
     * compareTo  也需要实现比较接口，这样会把时间短的排前面，而不用监控后面的时间
     */
    static class Order implements Delayed{
        private long timed;

        public Order(long timed) {
            this.timed = timed +System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long convert = unit.convert(timed - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            return convert;
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return String.valueOf(timed);
        }
    }
}
