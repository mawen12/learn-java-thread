package com.mawen.learn.multithreaded.thread13;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/12/5
 */
public class Thread13 {

	public static void main(String[] args) {
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				System.out.println("Alarm: The machine boils...");
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 500, 1000);
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello world");
			delay(300);
		}
		timer.cancel();
	}

	private static void delay(int time) {
		try {
			Thread.sleep(time);
		}
		catch (Exception e) {}
	}
}
