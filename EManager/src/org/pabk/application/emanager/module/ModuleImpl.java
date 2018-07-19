package org.pabk.application.emanager.module;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.Sleeper;
import org.pabk.application.emanager.util.Sys;
import org.pabk.application.emanager.util.TimerImpl;

import ch.qos.logback.classic.Logger;

public abstract class ModuleImpl extends Thread implements Module {

	protected Properties properties;


	public Properties getProperties() {
		return properties;
	}

	//


	//private static final String NOT_RUNNING_MODULES = "Some modules are still not initialized and running yet. %s";

	//private static final String MODULE_NOT_FOUND = "Required module %s not found. Check failed.";

	//private static final String MODULE_FOUND = "Required module %s found. Checking begin.";

	//private static final String MODULE_NOT_INITIALIZED = "Required module %s is not initialized yet.Check failed.";

	//private static final String MODULE_INITIALIZED = "Required module %s is initialized. Check passed.";

	//private static final String MODULE_RUNNING = "Required module %s is running. Check Passed.";

	//private static final String MODULE_NOT_RUNNING = "Required module %s is not runnong. Check failed.";

	//private static final int MAX_CHECK_FOR_REQUIRED = 10;

	//private static final long WAIT_FOR_INTERVAL = 5000;

	//private static final String MODULE_WAIT_FOR = "Module %s will wait for %d seconds while the required modules will started.";

	//private static final String MODULES_OK = "All required modules for module %s are started. Application continues.";



	//private static final long DEFAULT_WAIT_INTERVAL = 0L;


	//


	//


	//


	//


	//


	//


	//private static final String MODULE_LOGGER_LOADED = "Logger for module %s has been successfully loaded at level %s";


	//;


	//private static final String PROPERTIES_LOADED = "Properties for %s has been successfully loaded";


	//


	//


	//


	//


	//


	//


	//;


	//;








	//


	//



	//protected long waitInterval = DEFAULT_WAIT_INTERVAL;
	protected Sleeper sleeper;
	protected Logger logger;
	//protected Properties properties;
	protected ModuleX modulex;
	//protected String id;
	protected TimerImpl scheduler;
	/*
	 * bit 8	running state
	 * bit 7	initialization
	 * bit 6	initialized
	 * bit 5	sleeping
	 * bit 4 	working
	 * bit 3	under shutdown
	 * bit 2	on error
	 * bit 1	working state
	 */
	protected int state = 0x0;


	protected long startTime;
	private String id;


	public String toString() {
		return getIdm();
	}

	public String getIdm() {
		return this.id;
	}
	protected void setIdm(String idm) {
		this.id = idm;
	}


	@Override
	public void initialize() {
		state ^= Const.INITIALIZATION_FLAG;
		this.startTime = new Date().getTime();
		Sys.info(this, Const.INITIALIZATION_MODULE_START, this.getIdm(), new SimpleDateFormat(Const.COMMON_DATE_FORMAT).format(this.startTime));
		commonInitialize();
		state ^= Const.INITIALIZATION_FLAG;
		state ^= Const.INITIALIZED_FLAG;
		Sys.info(this, Const.INITIALIZATION_MODULE_END, this.getIdm(), new SimpleDateFormat(Const.COMMON_DATE_FORMAT).format(new Date()));
	}

	public void commonInitialize() {
		sleeper = new Sleeper();
		this.setLogger(Sys.loadLogger(modulex.getName(), Const.DEFAULT_MODULE_LOG_LEVEL));
		try {
			properties = Sys.loadProperties(this);
			Sys.info(this, Const.PROPERTIES_LOADED, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Sys.error(this, Const.PROPERTIES_FAILED, this);
			e.printStackTrace();
		}
		properties = properties == null ? new Properties() : properties;
		Sys.storePrepertiesToFile(this, properties, getLogger());
		scheduler = Sys.getScheduler(this, this.sleeper, this.getIdm());
		if(scheduler == null) {
			Sys.warn(this, Const.NO_SCHEDULER, this);
		}
		else {
			scheduler.setDaemon(true);
			scheduler.start();
			Sys.info(this, Const.SCHEDULER_APPENDED, this);
		}

	}

/*
	public void init(ModuleX modulex) {

		Date date = new Date();
		sleeper = new Sleeper();
		this.setIdm(modulex.getName());


		Sys.log(this, Sys.getLogger(), null, Const.INFO, INITIALIZATION_MODULE_START, this.getIdm(), new SimpleDateFormat(Const.COMMON_DATE_FORMAT).format(date));
		try {
			this.setLogger(Sys.loadLogger(modulex.getName(), DEFAULT_MODULE_LOG_LEVEL));
			Sys.startLogger(this.getLogger().getName());
			Sys.log(this, getLogger(), null, Const.INFO, MODULE_LOGGER_LOADED, this, ModuleImpl.DEFAULT_MODULE_LOG_LEVEL);
		}
		catch (Exception e) {
			e.printStackTrace();
			Sys.log(this, this.getLogger(), null, Const.ERROR, MODULE_LOGGER_FAILED, this);
		}
		try {
			properties = Sys.loadProperties(this, logger);
			Sys.log(this, getLogger(), null, Const.INFO, PROPERTIES_LOADED, this);
		}
		catch (Exception e) {
			properties = null;
			e.printStackTrace();
			Sys.log(this, getLogger(), null, Const.ERROR, PROPERTIES_FAILED, this);
		}
		properties = properties == null ? new Properties() : properties;
		Sys.storePrepertiesToFile(this, properties, getLogger());
		//waitInterval = Long.parseLong();
		scheduler = Sys.getScheduler(this, getLogger(), this.sleeper, properties, this.getIdm());
		if(scheduler == null) {
			Sys.log(this, getLogger(), null, Const.WARN, NO_SCHEDULER, this);
		}
		else {
			scheduler.setDaemon(true);
			scheduler.start();
			Sys.log(this, getLogger(), null, Const.INFO, SCHEDULER_APPENDED, scheduler.getClass(), this);
		}

		Sys.log(this, null, null, Const.INFO, LOADED_PROPERTIES, properties);

	}
*/



	public ModuleX getModuleX() {
		return modulex;
	}


	public void setModuleX(ModuleX modulex) {
		this.modulex = modulex;
	}

	@Override

	public void run() {
		super.run();
		initialize();
		state ^= Const.RUNNING_FLAG;
		state ^= Const.SLEEPING_FLAG;
		while(true) {
		//System.err.println(this + "+ status " + state);
		//System.err.println(this.isInitialized());
		//System.err.println(this.isRunning());
		//System.err.println(this.isUnderShutdown());
		if(this.isUnderShutdown()) {
			Sys.warn(this, Const.MODULE_UNDER_SHUTDOWN, this);
			break;
		}
		state ^= Const.SLEEPING_FLAG;
		Sys.info(this, Const.MODULE_SLEEP, this);
		this.scheduler.start();
		state ^= Const.WORKING_STATE;
		Sys.info(this, Const.MODULE_WORK, this);
		todoBusiness();
		Sys.info(this, Const.MODULE_SLEEP, this);
		state ^= Const.WORKING_STATE;
		state ^= Const.SLEEPING_FLAG;
		if(this.isUnderShutdown()) {
			Sys.warn(this, Const.MODULE_UNDER_SHUTDOWN, this);
			break;
		}
	}
	//System.err.println("WHY IS TERMINATED");
	state ^= Const.RUNNING_FLAG;
	}

	@Override
	public void todoBusiness() {
		// TODO Auto-generated method stub

	}



	protected boolean isRunning() {
		return (state & Const.RUNNING_FLAG) == Const.RUNNING_FLAG && this.isAlive();
	}
	protected boolean isInitialized() {
		return (state & Const.INITIALIZED_FLAG) == Const.INITIALIZED_FLAG && this.isAlive();
	}
	protected boolean isUnderShutdown() {
		return (state & Const.UNDER_SHUTDOWN_FLAG) == Const.UNDER_SHUTDOWN_FLAG;
	}

	public Logger getLogger() {
		return logger == null ? Sys.getLogger() : logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}


	public void invokeShutdown() {
		// TODO Auto-generated method stub

	}


}
