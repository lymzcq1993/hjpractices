package com.hujian.concurrent.forkjoin.arraysum;

import com.hujian.concurrent.forkjoin.utils.Utils;

import java.time.Duration;
import java.time.Instant;

/**
 * @author hujian
 *
 * 单线程计算1亿个整数的和
 */
public class SumSequential {
    public static long sum(int[] arr){
        return SumUtils.sumRange(arr, 0, arr.length);
    }

    public static void main(String[] args) {
        // 将一亿个数组随机存储1000以内的任意一个数字
        int[] arr = Utils.buildRandomIntArray(100000000);
        System.out.printf("The array length is: %d\n", arr.length);
        Instant now = Instant.now();
        //将数组所有的数字进行累加
        long result = sum(arr);
        System.out.println("执行时间："+Duration.between(now,Instant.now()).toMillis());

        System.out.printf("The result is: %d\n", result);
    }
}
