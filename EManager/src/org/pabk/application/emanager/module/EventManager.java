package org.pabk.application.emanager.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.Sys;
import org.xml.sax.SAXException;

public class EventManager extends ModuleImpl {

	private static final Hashtable<String, ModuleImpl> modules = new Hashtable<String, ModuleImpl>();
	private static ModuleListX modulesX = new ModuleListX ();
	private static EventManager manager;

	public static Thread getModule(String id) {
		EventManager.getModules().get(id);
		return null;
	}

	/**
	 * @return the modules
	 */
	public static Hashtable<String, ModuleImpl> getModules() {
		return modules;
	}


	public static void loadModulesConfiguration(Properties props) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException, ClassCastException, IndexOutOfBoundsException {
		EventManager.modulesX = Sys.loadModuleListFromXML (props, Const.MODULE_LIST_CONF, modulesX);
		//System.out.println(props.getProperty(Const.MANAGER_MODULE_NAME));
		/* load shutdown hook module */
		ModuleX modulex = modulesX.get(Sys.getProperty(EventManager.class, Const.MANAGER_MODULE_NAME, Const.DEFAULT_MANAGER_MODULE_NAME));
		if(modulex.getPriority() != 0) {
			throw new IndexOutOfBoundsException (Const.WRONG_PRIORITY);

			//EventManager.loadModules (EventManager.modulesX, modulex);
		}
		try {
			EventManager.loadModule(modulex);
		}
		catch (Exception e) {
			//e.printStackTrace();
			Sys.errorn(EventManager.getManager() == null ? EventManager.class.getSimpleName(): EventManager.getManager(), Const.MANAGER_FAILED);
			throw new IOException(e);
		}
	}
	protected static synchronized void loadModule(ModuleX modulex) throws Exception {
		ModuleImpl module = (ModuleImpl) Class.forName(modulex.getClassName()).newInstance();
		module.setIdm(modulex.getName());
		module.setModuleX(modulex);
		int maxLoops = Const.DEFAULT_MAX_LOOPS_OF_CHECKING;
		try {
			maxLoops = Integer.parseInt(Sys.getProperty(EventManager.class, Const.MAX_LOOPS_OF_CHECKING, Integer.toString(maxLoops)));
		}
		catch (Exception e) {maxLoops = Const.DEFAULT_MAX_LOOPS_OF_CHECKING;}
		long wait = Const.DEFAULT_WAIT_FOR_CHECKING_INTERVAL;
		try {
			wait = Long.parseLong(Sys.getProperty(EventManager.class, Const.WAIT_FOR_CHECKING_INTERVAL, Long.toString(wait)));
		}
			catch (Exception e) {wait = Const.DEFAULT_MAX_LOOPS_OF_CHECKING;}
		if(modules.get(modulex.getName()) != null) {
			throw new IOException(String.format(Const.DUPLICATE_MODULE_ID, modulex.getName()));
		}
		//module.initialize();
		//module.setDaemon(true);
		EventManager.modules.put(modulex.getName(), module);
		module.setModuleX(modulex);
		boolean passed = false;
		for(int i = 0; i < maxLoops; i ++) {
			//System.err.println("check module" + module + "-" + i);
			passed = EventManager.checkRequiredModules(module);
				if(passed) {
					break;
				}
				else {
					//Sys.log(module, module.getLogger(), null, Const.INFO, MODULE_WAIT_FOR_NEXT_CHECK, module, WAIT_FOR_CHECKING_INTERVAL/1000 );
					//module.sleeper.sleep(WAIT_FOR_CHECKING_INTERVAL);
					//throw new IOException(String.format(MODULE_WAIT_FOR_NEXT_CHECK, module, WAIT_FOR_CHECKING_INTERVAL/1000));
				}
			}
		if(passed) {
			module.initialize();
			module.setDaemon(true);

			module.start();
		}
		else {
			Sys.errorn(module, Const.WAITING_FAILS, module);
			module.invokeShutdown();
			//*TODO*/
			throw new IOException (String.format(Const.WAITING_FAILS, module));
		}
	}

	public static boolean checkRequiredModules(ModuleImpl module) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> list = module.getModuleX().getRequired();
		boolean passed = true;
		for (int i = 0; i < list.size(); i ++) {
			ModuleX modulex = EventManager.getModulesX().get(list.get(i));
			if(modulex == null) {

				Sys.errorn(module, Const.REQUIRED_MODULE_NOT_FOUND, list.get(i), module);
				return false;
			}
			Sys.infon(module, Const.CHECK_REQUIRED_MODULE_START, module, modulex.getName(), modulex.getClassName(),modulex.getName());
			ModuleImpl required = EventManager.getModuleForName(modulex.getName());
			if(required == null) {
				Sys.infon(module, Const.MODULE_NOT_LOADED, modulex.getName(), modulex.getName());
				try {
					EventManager.loadModule(modulex);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();

					Sys.errorn(module, Const.MODULE_FAILED_TO_LOAD, modulex.getName());


					if(modulex.isCritical()) {
						Sys.errorn(module, Const.CRITICAL_MODULE_NOT_LOADED, modulex.getName());
						module.invokeShutdown();
						passed = false;
						throw e;
					}
					else {
						passed = true;
						Sys.warnn(module, Const.NON_CRITICAL_MODULE_NOT_LOADED, modulex.getName());
					}
				}
			}
			else {
				System.err.println("---------------------------------------------------------------");
				System.err.println(module);
				System.err.println(module.isAlive() && module.isInitialized() && module.isRunning());
				System.err.println(module.isAlive());
				System.err.println(module.isRunning());
				System.err.println(module.isInitialized());
				System.err.println(module.isInterrupted());
				System.err.println(module.getState());
				System.err.println(module.state);
				System.err.println("------------------------------------------------------------------");
				System.err.println(required);
				System.err.println(required.isAlive() && required.isInitialized() && required.isRunning());
				System.err.println(required.isAlive());
				System.err.println(required.isRunning());
				System.err.println(required.isInitialized());
				System.err.println(required.isInterrupted());
				System.err.println(required.getState());
				System.err.println(required.state);

				if(required.isAlive() && required.isInitialized() && required.isRunning()) {

					Sys.infon(module, Const.REQUIRED_MODULE_RUNNING, modulex.getName(), module);
					passed = true;
					continue;
				}
				else {
					Sys.errorn(module, Const.REQUIRED_MODULE_NOT_RUNNING, modulex.getName());
					passed = false;
					break;
				}
			}
		}
		if(passed) {
			Sys.infon(module, Const.REQUIRED_MODULES_CHECK_PASSED, module);
		}
		else {
			Sys.warnn(module, Const.REQUIRED_ODULES_CHECK_FAILS, module);
		}
		return passed;
	}




	private static ModuleListX getModulesX() {
		return EventManager.modulesX;
	}

	public static EventManager getManager() {
		return manager;
	}

	public static void setManager(EventManager manager) {
		EventManager.manager = manager;
	}
	public static ModuleImpl getModuleForName(String moduleName) {
		return modules.get(moduleName);
	}

}
