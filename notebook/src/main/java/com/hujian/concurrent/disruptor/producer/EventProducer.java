package com.hujian.concurrent.disruptor.producer;

import com.hujian.concurrent.disruptor.envent.OrderEvent;
import com.lmax.disruptor.RingBuffer;
import lombok.Data;

/**
 * 事件生产者
 * @author hujian
 */
@Data
public class EventProducer {
    private RingBuffer<OrderEvent> ringBuffer;

    public EventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String name){
        long next = ringBuffer.next();
        OrderEvent orderEvent = ringBuffer.get(next);
        orderEvent.setName(name);
        System.out.println("生产者发布事件....."+name);
        ringBuffer.publish(next);
    }
}
