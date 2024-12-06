package com.mawen.learn.multithreaded.thread08;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/4
 */
public class Thread08 {

	private static double root = 0;
	private static final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			synchronized (lock) {
				root = sqrt2();
			}
		}).start();

		Thread.sleep(10);

		synchronized (lock) {
			System.out.println(root);
		}
	}

	private static double sqrt2() {
		double y = 0;
		for (int i = 0; i < 1000_000_000; i++) { // 耗时操作
			y = Math.sqrt(2);
		}
		return y;
	}
}
