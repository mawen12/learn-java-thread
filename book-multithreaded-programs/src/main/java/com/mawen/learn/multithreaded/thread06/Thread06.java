package com.mawen.learn.multithreaded.thread06;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/4
 */
public class Thread06 {

	private static ID ID = new ID();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				while (ID.getValue() < 10) {
					System.out.printf("[%d] %d%n", Thread.currentThread().getId(), ID.getId());
				}
			}).start();
		}
	}

	static class ID {
		private int id = 1;

		public int getValue() {
			return id;
		}

		public synchronized int getId() {
			int t = id;
			for (int i = 0; i < 1000_000; i++) {
				Math.cos(Math.sqrt(2));
			}

			++id;
			return t;
		}
	}
}
