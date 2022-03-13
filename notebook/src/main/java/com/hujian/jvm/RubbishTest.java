package com.hujian.jvm;

/**
 * VM参数 :
 * 输出gc日志：
 *  -Xloggc:./gc-%t.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause 2 -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M
 *
 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:MaxTenuringThreshold=15
 *-Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:MaxTenuringThreshold=15 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Xloggc:D:\gc-%t.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M
 * -XX:+UseSerialGC
 * -XX:+PrintTenuringDistribution
 */
public class RubbishTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                byte[] b1, b2, b3, b4;

                b1 = new byte[_1MB / 4];    //256K
                b2 = new byte[_1MB / 4];    //256K

                b3 = new byte[4 * _1MB];
                b4 = new byte[4 * _1MB];
                b4 = null;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        b4 = new byte[4 * _1MB];
        });
        Thread.sleep(3000);
        thread.start();
    }
}
