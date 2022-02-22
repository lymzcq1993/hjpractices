package com.hujian.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 使用jol-core打印对象头
 */
public class JOLSample {
    public static void main(String[] args) {
        MyClassLoaderTest myClassLoaderTest = new MyClassLoaderTest();
        ClassLayout layout = ClassLayout.parseInstance(myClassLoaderTest);
        System.out.println(layout.toPrintable());
    }
}
