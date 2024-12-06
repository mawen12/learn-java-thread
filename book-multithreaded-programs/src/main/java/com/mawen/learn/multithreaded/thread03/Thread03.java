package com.mawen.learn.multithreaded.thread03;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/1
 */
public class Thread03 {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(Thread03::work).start();
		}
	}

	private static void work() {
		print("Started");
		double y = 0;
		for (int i = 0; i < 1000000L; i++) {
			y = Math.cos(Math.sqrt(2));
		}
		print(String.format("%.14f",y));
	}

	private static void print(String text) {
		System.out.println("[" + Thread.currentThread().getId() + "] " + text);
	}
}
