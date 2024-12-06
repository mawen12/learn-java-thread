package com.mawen.learn.multithreaded.thread11;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/4
 */
public class Thread11 {

	public static void main(String[] args) {
		Shared shared = new Shared();
		new Producer(shared).start();
		new Consumer(shared).start();
		new Consumer(shared).start();
	}

	static class Shared {
		private char value;

		private volatile boolean writable = true;

		public synchronized void setValue(char value) {
			while (!writable) {
				try {
					wait();
					System.out.println("The producer was notified, " + (writable ? "写" : "读"));
				}catch (InterruptedException e) {}
			}

			this.value = value;
			this.writable = false;
			notify();
			System.out.println("The producer notify other thread.");
		}

		synchronized char getValue() {
			while (writable) {
				try {
					wait();
					System.out.println("The consumer was notified, " + (writable ? "写" : "读"));
				}
				catch (InterruptedException e) {}
			}
			writable = true;
			notify();
			System.out.println("The consumer notify other thread.");
			char ch = value;
			value = '0';
			return ch;
		}
	}

	static class Producer extends Thread {
		private final Shared shared;

		public Producer(Shared shared) {
			this.shared = shared;
		}

		public void run() {
			for (char ch = 'A'; ch <= 'Z'; ++ch) shared.setValue(ch);
		}
	}

	static class Consumer extends Thread {
		private final Shared shared;

		public Consumer(Shared shared) {
			this.shared = shared;
		}

		public void run() {
			char ch;
			do {
				System.out.println(ch = shared.getValue());
			}
			while (ch != 'Z');
			System.out.println();
		}
	}
}
