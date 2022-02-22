package com.hujian.jvm;

public class MyClassLoaderTest {
    private String name;
    private int a;
    public void say(String text){
        System.out.println("你找到我自定义加载的类拉！"+text);
    }
}
