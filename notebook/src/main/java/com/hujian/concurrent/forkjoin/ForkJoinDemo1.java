package com.hujian.concurrent.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class ForkJoinDemo1 {
    public static void main(String[] args) {
        singleThread();
    }

    public static void singleThread(){;
        // 准备数组
        int[] arr = buildRandomIntArray(100000000);
        System.out.printf("The array length is: %d\n", arr.length);
        Instant now = Instant.now();
        //数组求和
        long result = 0;
        for (int i : arr) {
            result += i;
        }
        System.out.println("单线程执行时间："+ Duration.between(now,Instant.now()).toMillis());

        System.out.printf("The result is: %d\n", result);
    }

    public static int[] buildRandomIntArray(final int size) {
        int[] arrayToCalculateSumOf = new int[size];
        Random generator = new Random();
        for (int i = 0; i < arrayToCalculateSumOf.length; i++) {
            arrayToCalculateSumOf[i] = generator.nextInt(1000);
        }
        return arrayToCalculateSumOf;
    }

    public static void threadPool(){

    }
}
