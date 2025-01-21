package com.mawen.learn.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NotifyOther {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread("NotifyDemo-");
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
