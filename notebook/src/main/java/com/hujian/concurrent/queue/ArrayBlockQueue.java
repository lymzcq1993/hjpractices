package com.hujian.concurrent.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayBlockingQueue<>(10);
    }
}
