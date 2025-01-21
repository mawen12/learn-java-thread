package com.mawen.learn.thread.methods;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        // Get Current Thread
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());

        // Interrupt another thread
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Ending ");
        });
        t1.interrupt();
        boolean interrupted = t1.isInterrupted();
        System.out.println("T1 is interrupted ?" + interrupted);

        // Join
        Thread t2 = new Thread(() -> {
            System.out.println("Hello World");
        });
        t2.start();
        t2.join();

        // Sleep
        Thread.sleep(Duration.ofSeconds(5).toMillis());

        // Set Daemon status
        thread.setDaemon(true);
    }
}
