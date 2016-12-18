package com.master.design.create.singleton;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * double checked locking
 */
public class DCLSingleton {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0; i<20; i++) {
            executorService.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    Singleton singleton = Singleton.getInstance();
                    System.out.println(singleton);
                    return null;
                }
            });
        }
    }


    /**
     * 当只有一个cpu访问内存时，可以不用volatile关键字
     * 但是当有多个cpu同时访问内存时，必须用volatile关键字来产生内存屏障保证一致性
     * 详见《java内存模型与线程》 P371
     */
    static class Singleton {

        private volatile static Singleton instance;

        public static Singleton getInstance() {
            if(instance == null) {
                synchronized (Singleton.class) {
                    if(instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }

}
