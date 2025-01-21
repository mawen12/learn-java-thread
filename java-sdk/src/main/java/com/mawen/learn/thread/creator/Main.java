package com.mawen.learn.thread.creator;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // start a thread from Main Thread
        Thread thread = new SimpleThread("Simple", 2);
        thread.start();

        // start a thread from Runnable
        Runnable r = new SimpleRunnable();
        Thread t = new Thread(r);
        t.start();

        // start a thread from Lambda Functions
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println("Ending Simple Thread");
        });

        t2.start();
        t2.interrupt();
    }
}
