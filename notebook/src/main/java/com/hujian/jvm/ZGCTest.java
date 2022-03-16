package com.hujian.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * 测试ZGC的GC效率，需要将项目的JDK版本在11以上，本次测试用JDK17
 * 默认G1： -Xmx2g -XX:+PrintGCDetails
 * ZGC：-XX:+UseZGC -Xmx1g -XX:+PrintGCDetails
 * ParNew+CMS（只在JDK8上测试）:   -Xmx2g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+PrintGCDetails
 */
public class ZGCTest {
    /*不停往list中填充数据*/
    //就使用不断的填充 堆 -- 触发GC
    public static class FillListThread extends Thread{
        List<byte[]> list = new LinkedList<>();
        @Override
        public void run() {
            try {
                while(true){
                   if(list.size()*512/1024/1024>=990){
                       list.clear();
                       System.out.println("list is clear");
                    }
                    byte[] bl;
                    for(int i=0;i<100;i++){
                        bl = new byte[512];
                        list.add(bl);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    public static void main(String[] args) {
        FillListThread myThread = new FillListThread(); //造成GC，造成STW
        myThread.start();
    }
}
