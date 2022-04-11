package com.hujian.concurrent.disruptor.consume;

import com.hujian.concurrent.disruptor.envent.OrderEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 消费者
 * EventHandler 广播模式
 * WorkHandler 共同消费模式
 * @author hujian
 */
public class OrderEventHandle implements EventHandler<OrderEvent>, WorkHandler<OrderEvent> {
    /**
     * 广播模式消费者处理方法
     * @param orderEvent
     * @param sequence
     * @param b
     */
    @Override
    public void onEvent(OrderEvent orderEvent, long sequence, boolean b) {
        System.out.println("广播模式消费者"+Thread.currentThread().getName()+"获取到了："+orderEvent.getName());
    }

    /**
     * 共同消费的处理方法
     * @param orderEvent
     * @throws Exception
     */
    @Override
    public void onEvent(OrderEvent orderEvent) throws Exception {
        System.out.println("共同消费消费者"+Thread.currentThread().getName()+"获取到了："+orderEvent.getName());
    }
}
