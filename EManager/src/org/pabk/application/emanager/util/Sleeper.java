package org.pabk.application.emanager.util;

public class Sleeper {
	public synchronized void sleep(long interval) {
		try {wait(interval);} catch (InterruptedException e) {}
	}
	public synchronized void wakeup() {
		notify();
	}
}
