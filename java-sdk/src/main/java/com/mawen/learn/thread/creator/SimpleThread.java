package com.mawen.learn.thread.creator;

import java.util.concurrent.TimeUnit;

public class SimpleThread extends Thread{

    private final int secs;

    public SimpleThread(String name, int secs) {
        this.secs = secs;
        this.setName(name);
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting Simple Thread\n", getName());

        try {
            TimeUnit.SECONDS.sleep(this.secs);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.printf("%s: Ending Simple Thread\n", getName());
    }
}
