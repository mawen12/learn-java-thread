package com.mawen.learn.multithreaded.thread05;

import java.util.Map;
import java.util.Random;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/4
 */
public class Thread05 {

	private static ID id = new ID();

	private static ThreadSafeID threadSafeID = new ThreadSafeID();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 2; ++i) { // start two threads to run getId
			new Thread(() -> {
				while (id.getValue() < 10) {
					System.out.printf("[%d] %d%n", Thread.currentThread().getId(), id.getId());
				}
			}).start();
		}

		Thread.sleep(1000L);
		System.out.println("Restart with thread safe");

		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				while (threadSafeID.getValue() < 10) {
					System.out.printf("[%d] %d%n", Thread.currentThread().getId(), threadSafeID.getId());
				}
			}).start();
		}
	}


	static class ID {
		private int id = 1;

		public int getValue() {
			return id;
		}

		public int getId() {
			int t = id;
			for (int i = 0; i < 1000_000; i++) {
				Math.cos(Math.sqrt(2));
			}

			++id;
			return t;
		}
	}

	static class ThreadSafeID {
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
