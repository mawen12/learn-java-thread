package com.mawen.learn.multithreaded.thread09;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/4
 */
public class Thread09 {

	private final Object lock1 = new Object();

	private final Object lock2 = new Object();

	private static int count = 0;

	public static void main(String[] args) {
		Thread09 instance = new Thread09();

		new Thread(() -> {
			while (true) {
				instance.work1();
				instance.delay();
			}
		}).start();

		new Thread(() -> {
			while (true) {
				instance.work2();
				instance.delay();
			}
		}).start();
	}

	public void work1() {
		synchronized (lock1) {
			synchronized (lock2) {
				System.out.printf("[%d-%s] %d%n", Thread.currentThread().getId(), Thread.currentThread().getName(), ++count);
			}
		}
	}

	public void work2() {
		synchronized (lock2) {
			synchronized (lock1) {
				System.out.printf("[%d-%s] %d%n", Thread.currentThread().getId(), Thread.currentThread().getName(), ++count);
			}
		}
	}

	private void delay() {
		try {
			Thread.sleep(50);
		}
		catch (Exception e) {}
	}
}
