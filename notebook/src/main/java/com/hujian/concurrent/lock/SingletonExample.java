package com.hujian.concurrent.lock;

/**
 * 双重锁检测
 * @author hujian
 * @since 2022-04-12 17:05
 */
public class SingletonExample {
    private SingletonExample(){};

    static volatile SingletonExample singletonExample;

    public static SingletonExample get(){
        if (singletonExample == null){
            //只有一个能初始化
            synchronized (SingletonExample.class){
                if (singletonExample == null){
                    //并不是原子操作，有概率指令重排，后面的线程拿到空的对象
                    singletonExample = new SingletonExample();
                }
                return singletonExample;
            }
        }
        else {
            return singletonExample;
        }
    }
}
