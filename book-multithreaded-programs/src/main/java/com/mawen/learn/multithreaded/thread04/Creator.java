package com.mawen.learn.multithreaded.thread04;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/1
 */
public class Creator implements Runnable {

	private long value;
	private Prime prime;

	public Creator(long value, Prime prime) {
		this.value = value;
		this.prime = prime;
	}

	@Override
	public void run() {
		nextPrime(value, prime);
	}

	public static void nextPrime(long t, Prime prime) {

	}
}
