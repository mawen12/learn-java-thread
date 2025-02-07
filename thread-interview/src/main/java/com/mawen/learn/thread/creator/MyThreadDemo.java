package com.mawen.learn.thread.creator;

/**
 * 通过继承{@link Thread}来实现线程
 *
 * <p>Java仅支持单线程
 */
public class MyThreadDemo {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();

        myThread.start();
        myThread1.start();
        myThread2.start();

        System.out.println("Main End");
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + ":" + i);
            }
        }
    }
}
