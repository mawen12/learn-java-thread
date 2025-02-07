package com.mawen.learn.thread.common;

import com.mawen.learn.thread.creator.MyRunnableDemo;

public class MyThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {
        MyRunnableDemo.MyRunnable runnable = new MyRunnableDemo.MyRunnable();
        Thread thread = new Thread(runnable);

        thread.start();

        thread.join();

        System.out.println("End");
    }
}
