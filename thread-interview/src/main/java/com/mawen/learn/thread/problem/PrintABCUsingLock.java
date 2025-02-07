package com.mawen.learn.thread.problem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用{@link java.util.concurrent.locks.Lock}实现顺序打印
 */
public class PrintABCUsingLock {

    /**
     * 打印总次数
     */
    private final int times;
    /**
     * 锁
     */
    private final Lock lock = new ReentrantLock();
    /**
     * 匹配状态
     */
    private int state;

    public PrintABCUsingLock(int times) {
        this.times = times;
    }

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times;) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.print(name);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingLock print = new PrintABCUsingLock(3);

        new Thread(() -> {
            print.printLetter("A", 0);
        }).start();
        new Thread(() -> {
            print.printLetter("B", 1);
        }).start();
        new Thread(() -> {
            print.printLetter("C", 2);
        }).start();
    }
}
