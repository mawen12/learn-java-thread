package com.mawen.learn.thread.creator;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 通过实现{@link Callable}来实现线程
 */
public class MyCallableDemo {

    public static void main(String[] args) {
        MyCallable callable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);

        thread.start();
        thread1.start();
        thread2.start();
    }

    public static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + ":" + i);
            }
            return "Hello";
        }
    }
}
