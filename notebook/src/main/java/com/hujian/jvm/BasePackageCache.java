package com.hujian.jvm;

/**
 * 基本数据类型的包装
 */
public class BasePackageCache {
    public static void main(String[] args) {
        Long long1 = 127l;
        Long long2 = 127l;

        Long long3 = 128l;
        Long long4 = 128l;
        System.out.println(long1 == long2);   //true
        System.out.println(long3 == long4);   //false

        Character char1 = 127;
        Character char2 = 127;

        Character char3 = 128;
        Character char4 = 128;
        System.out.println(char1 == char2);   //true
        System.out.println(char3 == char4);   //false


        //Byte类型只到-128-127
        Byte byte1 = 127;
        Byte byte2 = 127;
        System.out.println(byte1 == byte1);   //true


        Short short1 = 127;
        Short short2 = 127;

        Short short3 = 128;
        Short short4 = 128;
        System.out.println(short1 == short2);   //true
        System.out.println(short3 == short4);   //false

        //BooleanCache
        Boolean boo1 = true;
        Boolean boo2 = true;
        System.out.println(boo1 == boo2);   //true

        //------------------------------------------------------
        //浮点型未实现相关的Cache池
        Double d1 = 127d;
        Double d2 = 127d;

        Double d3 = 128d;
        Double d4 = 128d;
        System.out.println(d1 == d2);   //false
        System.out.println(d3 == d4);   //false

        Float f1 = 127f;
        Float f2 = 127f;

        Float f3 = 128f;
        Float f4 = 128f;
        System.out.println(f1 == f2);   //false
        System.out.println(f3 == f4);   //false

    }
}
