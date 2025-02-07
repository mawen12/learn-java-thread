package com.mawen.learn.thread.creator;

/**
 * 通过实现{@link Runnable}来实现线程
 *
 * <p>封装性更好
 */
public class MyRunnableDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());

        thread.start();
        thread1.start();
        thread2.start();
    }

    public static class MyRunnable implements Runnable {
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
