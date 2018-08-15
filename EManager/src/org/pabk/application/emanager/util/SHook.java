package org.pabk.application.emanager.util;

import org.pabk.application.EManager;

public class SHook extends Thread {

	private Object caller;

	public SHook (Object caller, String name) {
		this.setName(name);
		this.caller = caller;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Sys.infof(caller, Const.SHUTDOWN_HOOK_START, this.getName());
		SHook.gracefullyShutdown(caller);
		Sys.infof(caller, Const.SHUTDOWN_HOOK_END, this.getName());
	}

	private static void gracefullyShutdown(Object caller) {
		Sys.infof(caller, Const.GRACEFULL_SHUTDOWN_START);
		if(Sys.storePrepertiesToFile(caller, EManager.getStartUpProperties(), Const.STARTUP_PROPERTIES_LOCATION)) {
			Sys.infof(caller, Const.STARTUP_PROPERTIES_SAVED, Const.STARTUP_PROPERTIES_LOCATION);
		}
		else {
			Sys.errorf(caller, Const.STARTUP_PROPERTIES_FAILED_TO_SAVED, Const.STARTUP_PROPERTIES_LOCATION);
		}
	}

}
