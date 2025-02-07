package com.mawen.learn.thread.pool.creator;

import java.util.concurrent.*;

/**
 * 使用{@link java.util.concurrent.Executors}创建线程池
 */
public class MyExecutorsDemo {

    public static void main(String[] args) {
//        cachedThreadPool();

//        fixedThreadPool();

//        scheduledThreadPool();

//        singleThreadExecutor();
    }

    public static void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() + " " + index);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() + " " + index);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void scheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.schedule(() -> {
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() + " " + index);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

    public static void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() + " " + index);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
