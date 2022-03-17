package com.hujian.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author hujian
 * @since 2022-03-17 17:52
 */
public class UnsafeFactory {
    /**
     * unsafe方法java不允许直接使用，因此使用反射获取
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Unsafe getUnSafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        }
         catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
