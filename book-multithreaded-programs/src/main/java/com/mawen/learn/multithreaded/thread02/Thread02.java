package com.mawen.learn.multithreaded.thread02;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/11/29
 */
public class Thread02 {

	public static void main(String[] args) {
		System.out.println("Number of processors: " + Runtime.getRuntime().availableProcessors());
		Thread th1 = new Thread(new InfoThread(), "Thread number one");
		Thread th2 = new Thread(new InfoThread(), "Thread number two");
		System.out.println(th1.getState());
		th2.setDaemon(true); // th2 as soon as the primary thread and th1 terminated
		th1.start();
		th2.start();
		System.out.println(th1.isAlive());
	}
}

class InfoThread implements Runnable {
	@Override
	public void run() {
		Thread th = Thread.currentThread();
		System.out.println("[" + th.getId() + "] " + th.getName() + " is started");
		System.out.println("[" + th.getId() + "] " + (th.isDaemon() ? "Daemon" : "None Daemon"));
		System.out.println("[" + th.getId() + "] " + th.getState());

		try {
			Thread.sleep(th.isDaemon() ? 200 : 100);
		}
		catch (Exception ex) {

		}
		System.out.println("[" + th.getId() + "] " + th.getName() + " is terminated");
	}
}
