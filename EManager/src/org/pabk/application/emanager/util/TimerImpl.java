package org.pabk.application.emanager.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class TimerImpl extends Thread implements Timer {

	public static final int AT_THE_START = 0;
	private static final int THE_NEXT_HOUR = 1;
	static final int THE_NEXT_MULTIPLE_OF_INTERVAL_AT_THIS_HOUR = 2;
	private static final long MILLIS_IN_HOUR = 1000L * 60 *60;
	private static final long DEFAULT_INTERVAL = 1000L *60 * 10;
	private static final int MAX_STORED_TIMES = 100;
	private static final String DATE_FORMAT_PATTERN = "dd. MMMM 'at' hh:mm:ss";
	private static final long SCHEDULER_INTERVAL = 1000L;

	protected Sleeper lock;
	private Sleeper sleeper = new Sleeper();
	protected ArrayList<ScheduledTime> scheduler = new ArrayList<ScheduledTime>(MAX_STORED_TIMES);
	protected long firstTime;
	protected long interval;
	protected static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
	protected boolean paused = false;
	protected boolean underShutdown= false;

	public boolean isPaused() {
		return paused;
	}

	protected void pause() {
		this.paused = true;
	}
	protected void unpause() {
		this.paused = false;
	}

	private class ScheduledTime {
		private long time;
		protected long getTime() {
			return time;
		}
		protected void setTime(long time) {
			this.time = time;
		}
		public ScheduledTime(long nextTime) {
			setTime(nextTime);
		}
		public String toString() {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(getTime());
			return TimerImpl.sdf.format(calendar.getTime());

		}

	}
	protected TimerImpl(Sleeper lock, long interval, int firstTimeParam, long ... opts) {
		this.setLock(lock);
		this.setInterval(interval <= 0 ? DEFAULT_INTERVAL: interval);
		long shift = 0L;
		long actual = new Date().getTime();
		switch(firstTimeParam) {
		case AT_THE_START:
			this.setFirstTime(new Date().getTime());
			break;
		case THE_NEXT_HOUR:
				shift = opts.length > 0 ? opts[0] : shift;
				this.setFirstTime((actual / MILLIS_IN_HOUR + 1) * MILLIS_IN_HOUR - actual % MILLIS_IN_HOUR + shift);
				break;
		case THE_NEXT_MULTIPLE_OF_INTERVAL_AT_THIS_HOUR:
		default:
			shift = opts.length > 0 ? opts[0] : shift;
			long th = (actual / MILLIS_IN_HOUR) * MILLIS_IN_HOUR;
			long x = actual - th - shift;
			this.setFirstTime((x / interval + 1) * interval + th);
			break;
			}
		this.setScheduler();
		System.out.println(this.getScheduler());
	}

	protected ArrayList<ScheduledTime> getScheduler() {
		return scheduler;
	}

	protected void setScheduler() {
		this.scheduler = this.scheduler == null ? new ArrayList<ScheduledTime>(MAX_STORED_TIMES) : this.scheduler;
		this.scheduler.clear();
		for (int i = 0; i < MAX_STORED_TIMES; i ++) {
			this.scheduler.add(new ScheduledTime(this.nextTime()));
		}
	}

	protected long getFirstTime() {
		return firstTime;
	}
	protected void setFirstTime(long firstTime) {
		this.firstTime = firstTime;
	}
	protected long getInterval() {
		return interval;
	}
	protected void setInterval(long interval) {
		this.interval = interval;
	}
	@Override
	public void registerSleeper(Sleeper lock) {
		this.lock = lock;

	}

	public long nextTime() {
		return this.getScheduler().size() == 0 ? this.getFirstTime() : this.getScheduler().get(this.getScheduler().size() - 1).getTime() + this.getInterval();
	}

	@Override
	public void run() {
		super.run();
		while (! this.isUnderShutdown()) {
			if(! this.isPaused()) {
				long next = this.getScheduler().get(0).getTime();
				if(new Date().getTime() > next) {
					this.getLock().wakeup();
					this.getScheduler().remove(0);
					this.getScheduler().add(new ScheduledTime(this.nextTime()));
					System.out.println(this.getScheduler());
				}
			}
			sleeper.sleep(SCHEDULER_INTERVAL);
		}
	}

	protected Sleeper getLock() {
		return lock;
	}

	protected void setLock(Sleeper lock) {
		this.lock = lock;
	}

	protected boolean isUnderShutdown() {
		return underShutdown;
	}

	protected void shutdown() {
		this.underShutdown = true;
	}

}
