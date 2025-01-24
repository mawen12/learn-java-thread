package com.mawen.learn.thread.future;

import java.util.concurrent.TimeUnit;

public class FuturesPlay {

    public static void doSimpleTask() {
        System.out.printf("%s: Starting Simple Task\n", Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("Task Interrupted");
        }

        System.out.printf("%s: Ending Simple Task\n", Thread.currentThread().getName());
    }

    public static TaskResult doTask(String name, int secs, boolean fail) {
        System.out.printf("%s: Starting Task%s\n", Thread.currentThread().getName(), name);

        try {
            TimeUnit.SECONDS.sleep(secs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (fail) {
            throw new RuntimeException("Task Failed");
        }

        System.out.printf("%s: Ending Task%s\n", Thread.currentThread().getName(), name);

        return new TaskResult(name, secs);
    }
}
