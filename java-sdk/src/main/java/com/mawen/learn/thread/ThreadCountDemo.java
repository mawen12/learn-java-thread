package com.mawen.learn.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadCountDemo {

    int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ThreadCountDemo demo = new ThreadCountDemo();
        demo.multiAdd();

        System.out.println(demo.i);
    }

    public void multiAdd() throws InterruptedException {
        Semaphore first = new Semaphore(1);
        Semaphore second = new Semaphore(0);
        Semaphore third = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            for (int k = 0; k < 1000; k++) {
                try {
                    first.acquire();
                    System.out.println(Thread.currentThread().getName() + ":" + i++);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    second.release();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int k = 0; k < 1000; k++) {
                try {
                    second.acquire();
                    System.out.println(Thread.currentThread().getName() + ":" + i++);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    third.release();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for (int k = 0; k < 1000; k++) {
                try {
                    third.acquire();
                    System.out.println(Thread.currentThread().getName() + ":" + i++);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    first.release();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }
}
