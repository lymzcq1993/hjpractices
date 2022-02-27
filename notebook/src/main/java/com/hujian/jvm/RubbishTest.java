package com.hujian.jvm;

/**
 * VM参数 :
 * -verbose:gc
 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:MaxTenuringThreshold=15
 *
 *
 *
 *
 * -XX:+UseSerialGC
 * -XX:+PrintTenuringDistribution
 */
public class RubbishTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        byte[] b1, b2, b3, b4;

        b1 = new byte[_1MB / 4];    //256K
        b2 = new byte[_1MB / 4];    //256K

        b3 = new byte[4 * _1MB];
        b4 = new byte[4 * _1MB];
        b4 = null;
//        b4 = new byte[4 * _1MB];
    }
}
