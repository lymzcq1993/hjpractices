package com.hujian.concurrent.disruptor;

import com.hujian.concurrent.disruptor.consume.OrderEventHandle;
import com.hujian.concurrent.disruptor.envent.OrderEvent;
import com.hujian.concurrent.disruptor.envent.OrderEventFactory;
import com.hujian.concurrent.disruptor.producer.EventProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * 单生产者模式
 * @author hujian
 */
public class DisruptorDemo {
    public static void main(String[] args) {
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                new OrderEventFactory()
                , 1024
                , Executors.defaultThreadFactory()
                , ProducerType.SINGLE   //SINGLE与MULTI为了区分在并发多生产者下的数据安全问题
                , new SleepingWaitStrategy()
        );
        //必须在start之前设置消费者

        //广播模式消费
//        disruptor.handleEventsWith(new OrderEventHandle(),new OrderEventHandle());
        //共同消费
        disruptor.handleEventsWithWorkerPool(new OrderEventHandle(),new OrderEventHandle());


        disruptor.start();

        //获取ringBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        //生产者
        EventProducer eventProducer = new EventProducer(ringBuffer);
        for (int i = 0; i < 100; i++) {
            eventProducer.onData("生产者"+i);
        }

        disruptor.shutdown();
    }
}
