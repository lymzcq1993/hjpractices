package com.hujian.concurrent.disruptor.envent;

import com.lmax.disruptor.EventFactory;

/**
 * 事件工厂
 * @author hujian
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
