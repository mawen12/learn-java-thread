package com.mawen.learn.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);

        System.out.println(semaphore.availablePermits());

        semaphore.release();

        System.out.println(semaphore.availablePermits());

        semaphore.acquire();

        System.out.println(semaphore.availablePermits());

        semaphore.release();

        System.out.println(semaphore.availablePermits());
    }
}
