package com.hujian.jvm;

/**
 * 字符串常量池的试验
 */
public class StringConstPoolTest {
    public static void main(String[] args) {
        String s1 = new String("he")+new String("llo");
        String s2 = "hello";
        System.out.println(s1== s2);    //false   一个在堆内存里，一个在字符串常量池
        System.out.println(s1.intern()== s2);    //true  intern返回的是字符串里值，所以为true


        System.out.println("------------------------------");
        //此处为什么java的intern()和自己比较是false?因为java在初始化的时候java程序肯定有用到java的关键字的
        //所以字符串常量池之中是有java的，intern()返回的是字符串常量池的引用
        //而java2在字符串常量池没有，所以intern()返回的是对象本身
        String s3 = new String("ja")+new String("va");
        System.out.println( s3.intern() == s3);    //false

        String s4 = new String("ja")+new String("va2");
        System.out.println( s4.intern() == s4);   //true

        //toString返回一个new 的新对象
        String jisuanji = new StringBuilder("计算").append("机").toString();
        String jisuan2 =  "计算机";
        System.out.println("计算机比较:"+(jisuanji==jisuan2));

        System.out.println("------------------------------");


        //因为 “a”和对象拼接返回的是一个全新的对象
        String s5 = "a" + new String("b");
        String s6 = "ab";
        System.out.println(s5 == s6);   //false


        System.out.println("------------------------------");

        //使用变量拼接，java在运行时会将该变量当作对象而拼接，所以返回的是一个新的对象
        String s7 = "a";
        String s8 = "b";
        String s9 = "c";
        String s10 = "abc";
        String s11 = s7+s8+s9;
        System.out.println(s10==s11);   //false

    }


}
