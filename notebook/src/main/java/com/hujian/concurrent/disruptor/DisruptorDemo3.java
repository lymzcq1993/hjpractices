package com.hujian.concurrent.disruptor;

import com.hujian.concurrent.disruptor.envent.OrderEvent;
import com.hujian.concurrent.disruptor.envent.OrderEventFactory;
import com.hujian.concurrent.disruptor.producer.EventProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * 顺序消费模式
 * @author hujian
 */
public class DisruptorDemo3 {
    public static void main(String[] args) {
        //SINGLE与MULTI为了区分在并发多生产者下的数据安全问题
        //在多生产者下使用SINGLE会导致数据被覆盖的可能性
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                new OrderEventFactory()
                , 1024
                , Executors.defaultThreadFactory()
                , ProducerType.SINGLE
                , new SleepingWaitStrategy()
        );
        //必须在start之前设置消费者

        //广播模式消费
//        disruptor.handleEventsWith(new OrderEventHandle(),new OrderEventHandle());
        //共同消费
        disruptor.handleEventsWith(( (event, sequence, endOfBatch) -> {
            System.out.println("我是第一个消费的！");
        })).handleEventsWith(( (event, sequence, endOfBatch) -> {
            System.out.println("我是第二个消费的！");
        })).handleEventsWith(( (event, sequence, endOfBatch) -> {
            System.out.println("我是第三个消费的！");
        }));
        //获取ringBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        disruptor.start();

        //生产者
        EventProducer eventProducer = new EventProducer(ringBuffer);
        for (int i = 0; i < 1; i++) {
            eventProducer.onData("生产者"+i);
        }


        disruptor.shutdown();
    }
}
