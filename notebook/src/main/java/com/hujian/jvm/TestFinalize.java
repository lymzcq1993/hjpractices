package com.hujian.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试finalize方法
 * 当类重写了finalize()时候，对象即将被回收的时候会被调用该方法，如果不被回收不会调用
 */
public class TestFinalize {
    private static List<Rubbish> list = new ArrayList<>();
    public static void main(String[] args) {
        int i =0;
        int j=0;
        while (true){
            list.add(new Rubbish("我是垃圾："+i++));
            new Rubbish("我是垃圾："+j--);
        }
    }

    private static class Rubbish {
        private String name;
        public Rubbish(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            //此处加上引用关系可以不被回收
            //list.add(this);
            System.out.println(name);
        }
    }
}
