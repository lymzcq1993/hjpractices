package com.hujian.concurrent.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author hujian
 * @since 2022-03-31 16:17
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //默认是最小堆，可以自己实现排序规则
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>(10,(o1,o2)->{
            return o1-o2;
        });
        priorityBlockingQueue.put(86);
        priorityBlockingQueue.put(100);
        priorityBlockingQueue.put(1);

        System.out.println(priorityBlockingQueue.take());
        System.out.println(priorityBlockingQueue.take());
        System.out.println(priorityBlockingQueue.take());

    }
}
