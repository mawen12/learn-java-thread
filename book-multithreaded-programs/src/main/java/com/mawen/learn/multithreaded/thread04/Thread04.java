package com.mawen.learn.multithreaded.thread04;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/1
 */
public class Thread04 {

	private static long prime;

	public static void main(String[] args) {
		Thread th;
		(th = new Thread(() -> nextPrime(10000000))).start();
		// The primary thread don't wait the thread to terminate.
		System.out.println(prime);
	}

	private static void nextPrime(long t) {
		if (t < 2) prime = 2;
		else for (prime = t % 2 == 0 ? t + 1 : t; !isPrime(prime); prime += 2);
	}

	private static boolean isPrime(long t) {
		if (t == 2 || t == 3 || t == 5 || t ==7) return true;
		if (t < 11 || t % 2 == 0) return false;
		for (long k = 3, m = (long) Math.sqrt(t) + 1; k <= m; k += 2) {
			if (t % k == 0) return false;
		}
		return true;
	}
}
