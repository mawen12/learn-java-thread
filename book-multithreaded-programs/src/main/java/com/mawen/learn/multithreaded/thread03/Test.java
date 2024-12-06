package com.mawen.learn.multithreaded.thread03;

import java.util.Random;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/1
 */
public class Test {

	public static void main(String[] args) {
		Thread th1 = new Thread(() -> {
			Random random = new Random();

			for (int i = 0; i < 10; i++) {
				print(1);
				try {
					Thread.sleep(random.nextInt(1000));
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		});

		Thread th2 = new Thread(() -> {
			Random random = new Random();

			for (int i = 0; i < 10; i++) {
				print(random.nextDouble());
			}
		});

		th1.start();
		th2.start();
	}

	private static void print(Object content) {
		System.out.println("[" + Thread.currentThread().getId() + "] " + content);
	}
}

