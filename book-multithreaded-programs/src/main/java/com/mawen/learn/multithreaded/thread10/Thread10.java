package com.mawen.learn.multithreaded.thread10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/4
 */
public class Thread10 {

	public static void main(String[] args) {
		List<AThread> threads = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			threads.add(new AThread());
		}
		threads.forEach(Thread::start);

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {}

		threads.forEach(AThread::stopThread);
	}

	static class AThread extends Thread {

		private volatile boolean stopped = false;

		public void run() {
			while (!stopped) {
				System.out.printf("%d is running%n", Thread.currentThread().getId());
			}
		}

		public void stopThread() {
			stopped = true;
		}
	}
}
