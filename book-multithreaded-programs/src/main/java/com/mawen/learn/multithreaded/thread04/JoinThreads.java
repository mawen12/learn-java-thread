package com.mawen.learn.multithreaded.thread04;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/1
 */
public class JoinThreads {

	public static void main(String[] args) {
		long[] values = {1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L,
				1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L,
				10000000000000000L, 100000000000000000L, 1000000000000000000L};

		Prime[] primes = new Prime[values.length];

		for (int i = 0; i < primes.length; i++) {
			primes[i] = new Prime();
		}

		for (int i = 0; i < values.length; i++) {
			Creator.nextPrime(values[i], primes[i]);
		}

		for (int i = 0; i < primes.length; i++) {
			System.out.println(primes[i].getValue());
		}
	}
}
