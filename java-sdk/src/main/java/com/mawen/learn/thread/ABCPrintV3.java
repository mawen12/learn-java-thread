package com.mawen.learn.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ABCPrintV3 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);

        // 对比v2，改为减少一次
        CountDownLatch isDone = new CountDownLatch(1);

        executor.execute(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    a.acquire();
                    System.out.print("A");
                    b.release();
                }
            } catch (Exception e) {
            }
        });
        executor.execute(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    b.acquire();
                    System.out.print("B");
                    c.release();
                }
            } catch (Exception e) {
            }
        });
        executor.execute(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    c.acquire();
                    System.out.print("C");
                    a.release();
                }
                isDone.countDown();
            } catch (Exception e) {
            }
        });

        isDone.await();

        System.out.println("\nEnd");
    }
}
