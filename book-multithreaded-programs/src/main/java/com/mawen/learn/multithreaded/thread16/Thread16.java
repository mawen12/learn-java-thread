package com.mawen.learn.multithreaded.thread16;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/5
 */
public class Thread16 {

	private static final int N = 5;

	public static void main(String[] args) {
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch doneLatch = new CountDownLatch(N);

		Runnable worker = new Worker(startLatch, doneLatch);
		ExecutorService executor = Executors.newFixedThreadPool(N);
		for (int i = 0; i < N; i++) executor.execute(worker);

		try {
			System.out.println("Main working");
			Thread.sleep(1000);
			startLatch.countDown();
		}
		catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	static class Worker implements Runnable {
		private static final Random rand = new Random();
		private final CountDownLatch start;
		private final CountDownLatch done;

		public Worker(CountDownLatch start, CountDownLatch done) {
			this.start = start;
			this.done = done;
		}

		public void run() {
			try {
				print("Entered run()");
				start.await();
				print("Working");
				Thread.sleep(rand.nextInt(1000));
				done.countDown();
			}
			catch (InterruptedException e) {
				System.err.println(e);
			}
		}

		void print(String text) {
			System.out.printf("[%d] %s\n", Thread.currentThread().getId(), text);
		}
	}
}
