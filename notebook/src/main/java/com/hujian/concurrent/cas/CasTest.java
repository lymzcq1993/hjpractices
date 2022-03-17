package com.hujian.concurrent.cas;

import com.hujian.concurrent.UnsafeFactory;
import sun.misc.Unsafe;

public class CasTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unSafe = UnsafeFactory.getUnSafe();
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
        long l = UnsafeFactory.getUnSafe().objectFieldOffset(o.getClass().getDeclaredField(field));
        System.out.println("偏移量为:"+l);
        return l;
    }


}
