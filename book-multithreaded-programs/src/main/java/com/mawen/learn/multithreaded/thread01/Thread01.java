package com.mawen.learn.multithreaded.thread01;

import java.util.Random;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/11/29
 */
public class Thread01 {

	public static void main(String[] args) {
		new Thread(new Worker1()).start();
		new Worker2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				ToDo.work(2, 5);
			}
		}).start();
		new Thread(() -> ToDo.work(2, 5)).start();
		ToDo.work(2, 5);
	}

	static class Worker1 implements Runnable {
		@Override
		public void run() {
			ToDo.work(2,5);
		}
	}
}

class Worker2 extends Thread {

	public Worker2() {
		start();
	}

	@Override
	public void run() {
		ToDo.work(2, 5);
	}
}

class ToDo {
	private static Random random = new Random();

	public static void work(int a, int b) {
		print("started");
		for (int i = 0, n = random.nextInt(b - a) + a; i < n; i++) {
			print("working......");
			work();
		}
		print("terminated");
	}

	private static void print(String text) {
		long id = Thread.currentThread().getId();
		System.out.println("[" + id + "] " + text);
	}

	private static void work() {
		double y;
		for (int i = 0; i < 1000000L; i++) {
			y = Math.cos(Math.sqrt(random.nextDouble()));
		}
	}
}
