package com.mawen.learn.multithreaded.thread12;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Start a {@link java.util.Timer}, and print a message on the screen every half second.
 * The {@link java.util.Timer} is ticking for 2 seconds and then it stop2 the program.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/5
 */
public class Thread12 {

	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Alarm: The machine boils...");
				System.exit(0);
			}
		};

		Timer timer = new Timer();
		timer.schedule(task,2000);

		for (; ; ) {
			System.out.println("Hello world");
			delay(500);
		}
	}

	private static void delay(int time) {
		try {
			Thread.sleep(time);
		}
		catch (Exception e) {}
	}
}
