package com.hujian.concurrent.thread;

import com.hujian.concurrent.UnsafeFactory;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.nio.ByteOrder;

/**
 * 偏向锁和延迟偏向,偏向锁默认状态下会延迟4s开启
 * 关闭延迟偏向锁 ‐XX:BiasedLockingStartupDelay=0
 * 禁止偏向锁   ‐XX:‐UseBiasedLocking
 * @author hujian
 * @since 2022-03-17 17:21
 */
@Slf4j
public class BiasedLock {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        log.debug(ClassLayout.parseInstance(obj).toPrintable());
        Thread.sleep(5000);
        log.debug(ClassLayout.parseInstance(new Object()).toPrintable());
    }

    public static void testEden(){
        Unsafe unsafe = UnsafeFactory.getUnSafe();
        long a = unsafe.allocateMemory(8);
        try {
            unsafe.putLong(a, 0x0102030405060708L);
            byte b = unsafe.getByte(a);
            ByteOrder byteOrder;
            switch (b) {
                case 0x01: byteOrder = ByteOrder.BIG_ENDIAN;     break;
                case 0x08: byteOrder = ByteOrder.LITTLE_ENDIAN;  break;
                default:
                    byteOrder = null;
            }
            System.out.println(byteOrder);
        } finally {
            unsafe.freeMemory(a);
        }
    }

}
