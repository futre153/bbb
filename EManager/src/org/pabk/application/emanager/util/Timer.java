package org.pabk.application.emanager.util;

public interface Timer {
	void registerSleeper(Sleeper sleeper);
	long nextTime();

}