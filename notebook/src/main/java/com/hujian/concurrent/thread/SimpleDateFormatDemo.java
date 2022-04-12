package com.hujian.concurrent.thread;

import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author hujian
 */
public class SimpleDateFormatDemo {
    public static void main(String[] args) throws InterruptedException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //每次获取到的都是新的SimpleDateFormat
        ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));
        new Thread(()->{
//            System.out.println(dateFormat.format(DateUtil.parseDate("2022-4-11")));
            System.out.println(threadLocal.get().format(DateUtil.parseDate("2022-4-11")));
        }).start();

        TimeUnit.SECONDS.sleep(1);
//        dateFormat.format(DateUtil.parseDate("2023-4-11"));
        threadLocal.get().format(DateUtil.parseDate("2023-4-11"));
        System.out.println("结束");
//        TimeUnit.DAYS.sleep(1);
    }
}
