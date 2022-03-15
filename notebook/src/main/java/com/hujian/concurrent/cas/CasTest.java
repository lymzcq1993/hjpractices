package com.hujian.concurrent.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CasTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unSafe = getUnSafe();
        CasT casT = new CasT();
        casT.setI(5);
        long offSet = getFieldOffSet(casT, "i");
        //参数解释，实例对象，偏移量（目标值的偏移量），期望的值，修改的值
        boolean b = unSafe.compareAndSwapInt(casT,offSet , 5, 6);
        boolean c = unSafe.compareAndSwapInt(casT,offSet , 5, 6);  //值已经变为6，所以无法修改   false
        boolean d = unSafe.compareAndSwapInt(casT,offSet , 6, 7);  //目标值是7，可以修改  true
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    public static class CasT{
        //偏移量12，klass point（指针压缩4位）+对象头（8位）+int（4位）
        int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    /**
     * 获取字段偏移量
     */
    public static long getFieldOffSet(Object o,String field) throws NoSuchFieldException, IllegalAccessException {
        long l = getUnSafe().objectFieldOffset(o.getClass().getDeclaredField(field));
        System.out.println("偏移量为:"+l);
        return l;
    }

    /**
     * unsafe方法java不允许直接使用，因此使用反射获取
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Unsafe getUnSafe() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }
}
