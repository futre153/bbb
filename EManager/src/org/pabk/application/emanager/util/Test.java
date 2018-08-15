package org.pabk.application.emanager.util;

public class Test extends Thread {


	private GUIItem item;
	private Sleeper sleeper = new Sleeper();

	public Test( GUIItem item) {
		this.item = item;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true) {
			System.err.println(item.isVisible());
			sleeper.sleep(500);
		}
	}

}
