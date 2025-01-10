package com.mawen.learn.thread;

import java.util.concurrent.Semaphore;

public class ABCPrint {

    public static void main(String[] args) throws InterruptedException {

        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    a.acquire();
                    System.out.print("A");
                    b.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    b.acquire();
                    System.out.print("B");
                    c.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    c.acquire();
                    System.out.print("C");
                    a.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t1.join();
        t1.join();

    }
}
