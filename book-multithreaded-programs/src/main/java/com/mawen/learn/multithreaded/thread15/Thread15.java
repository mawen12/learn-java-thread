package com.mawen.learn.multithreaded.thread15;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/5
 */
public class Thread15 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Callable<BigDecimal> callE = new ECalculator(200);
		Callable<Long> callP = new PrimeCalculator(1000000000000000L);

		Future<BigDecimal> eTask = executor.submit(callE);
		Future<Long> pTask = executor.submit(callP);

		try {
			while (!eTask.isDone() || !pTask.isDone()) {
				System.out.println("waiting");
				try {
					Thread.sleep(100);
				}
				catch (Exception e) {
				}
			}

			System.out.println(eTask.get());
			System.out.println(pTask.get());
		}
		catch (Exception e) {
			System.err.println(e);
		}
		executor.shutdownNow();
	}

	static class ECalculator implements Callable<BigDecimal> {

		private int dec;

		public ECalculator(int dec) {
			this.dec = dec;
		}

		public BigDecimal call() {
			MathContext mc = new MathContext(dec, RoundingMode.HALF_UP);
			BigDecimal y = BigDecimal.ZERO;
			for (int i = 0; ; i++) {
				BigDecimal fac = BigDecimal.ONE.divide(factorial(new BigDecimal(i)), mc);
				BigDecimal z = y.add(fac, mc);
				if (z.compareTo(y) == 0) break;
				y = z;
			}
			return y;
		}

		private BigDecimal factorial(BigDecimal n) {
			return n.equals(BigDecimal.ZERO) ? BigDecimal.ONE : n.multiply(factorial(n.subtract(BigDecimal.ONE)));
		}
	}

	static class PrimeCalculator implements Callable<Long> {
		private long n;

		public PrimeCalculator(long n) {
			this.n = n;
		}

		public Long call() {
			long t = n;
			if (t <= 2) return new Long(2);
			if (t % 2 == 0) ++t;
			while (!isPrime(t)) t += 2;
			return t;
		}

		private boolean isPrime(long n) {
			if (n == 2 || n == 3 || n == 5 || n == 7) return true;
			if (n < 11 || n % 2 == 0) return false;
			for (long t = 3, m = (long) (Math.sqrt(n) + 1); t <= m; t += 2) {
				if (n % t == 0) return false;
			}
			return true;
		}
	}
}
