package com.hujian.jvm;

/**
 * 逃逸分析和标量替换
 * ‐XX:+PrintGC 打印GC
 */
public class EscapeAnalysis {
    /**
     * 开启  -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
     * 标量替换或者逃逸分析任意关闭都不会生效果
     * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i <  100000000; i++) {
            createUser();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void createUser(){
        User user = new User();
        user.setAge(1);
        user.setName("hu");
    }
}
