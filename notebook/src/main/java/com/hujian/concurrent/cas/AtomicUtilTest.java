package com.hujian.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

//原子工具类测试
public class AtomicUtilTest {
    public static void main(String[] args) {
        atomicArrayTest();
    }

    public static void atomicStampedReferenceTest(){
        String girl = "女孩";
        String one = "1号男友";
        String two = "2号男友";
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(girl,1);

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            LockSupport.parkNanos(1000000000L);
            System.out.println("1号男友想要恋爱");
            System.out.println("此时"+Thread.currentThread().getName()+"stamp:"+stamp);
            boolean b = atomicStampedReference.compareAndSet(girl, one, stamp, stamp + 1);
            if (b) {
                System.out.println("1号男友和女友开始恋爱");
            } else {
                System.out.println("1号男友和女孩恋爱失败，因为中间有第三者:"+atomicStampedReference.getStamp());
            }
        },"Thread1").start();

         new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("2号男友恋爱");
            boolean b = atomicStampedReference.compareAndSet(girl, two, stamp, stamp+1);
            System.out.println("2号男友和女孩分手!"+b);
            stamp ++;
             boolean b1 = atomicStampedReference.compareAndSet(two, girl, stamp, stamp + 1);
             System.out.println("分手:"+b1+"stamp"+atomicStampedReference.getStamp());
         },"Thread2").start();
    }

    public static void atomicIntegerTest(){
        AtomicInteger atomicInteger = new AtomicInteger(0);

        //先增加值，返回增加后的值
        int incrementAndGet = atomicInteger.incrementAndGet();
        //获取原值，再增加
        int andIncrement = atomicInteger.getAndIncrement();
    }

    public static void atomicReference(){
        AtomicReference<Integer> atomicReference = new AtomicReference<>();
        atomicReference.compareAndSet(1,10);

        //相比AtomicReference这里多个时间戳，功能更加多一点。但是使用字符串的时候需要注意，这里比较的是引用而不是字符串
        String str = "h";
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(str,0);
        boolean strIs = atomicStampedReference.compareAndSet(new String("h"), "dfff", 0, 1);//false
        boolean referenceIs = atomicStampedReference.compareAndSet("h", "dfff", 0, 1);//true
        System.out.println(strIs+"---"+referenceIs);
    }

    public static void atomicArrayTest(){
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,1,2,3});
        atomicIntegerArray.compareAndSet(0,1,3);
        System.out.println(atomicIntegerArray.get(0));
    }

}
