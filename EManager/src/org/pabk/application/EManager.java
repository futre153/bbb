package org.pabk.application;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.DBConnector;
import org.pabk.application.emanager.module.EventManager;
import org.pabk.application.emanager.module.GUIManager;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.DBTable;
import org.pabk.application.emanager.util.SHook;
import org.pabk.application.emanager.util.Sys;
import org.xml.sax.SAXException;

import com.mchange.v1.cachedstore.CachedStore.Manager;

import javafx.application.Application;
import javafx.stage.Stage;

public class EManager extends Application {

	private static Properties startUpProperties = new Properties();
	private static DBTable dbTable;
	private static String primaryStage;

	List<String> stringList = new ArrayList<String>();
    List<Integer> integerList = new ArrayList<Integer>();
	@Override
	public void start(Stage primary) throws Exception {
		try {
			GUIManager.loadStage(EManager.class, primary, EManager.getPrimaryStage(), false);
			System.err.println("KOMIEC");
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			EventManager.loadModulesConfiguration(EManager.getStartUpProperties());

				 Sys.infof(EManager.class, Const.APP_STARTUP_OK);
			 }
			 catch (Exception e) {
				 Sys.errorf(EManager.class, Const.APP_STARTUP_FAILED);
				 e.printStackTrace();
				 System.exit(1);

			 }

	}

	public static String getPrimaryStage() {
		// TODO Auto-generated method stub
		return EManager.primaryStage;
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException {
		/* load shutdown hook */
		SHook hook = new SHook(EManager.class, Sys.getProperty(EManager.class, Const.SHUTDOWN_HOOK_NAME, Const.DEFAULT_SHUTDOWN_HOOK_NAME));
		hook.setDaemon(true);
		Runtime.getRuntime().addShutdownHook(hook);
		/* end load shutdown hook */
		/* Load startup properties */
		/* load initial resource bundle */
		/* tot je test */
		String baseName = Const.BASIC_RESOURCE_BUNDLE_BASE_NAME;
		Locale locale = Sys.getLocale(Const.INITIAL_LANGUAGE);
		//System.exit(1);

		ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);

		GUIManager.setResourceBundle(rb);
		/* load temporary logback logger */
		Sys.setTemporaryLogger(Sys.loadTemporaryLogger(Const.TEMPORARY_LOGGER_OUTPUT_FILE, Const.TEMPORARY_LOG_PATTERN));



		//System.out.println(String.format(rb.getString(Const.INITIAL_RESOURCE_BUNDLE_LOADED), locale.getDisplayLanguage(), EManager.class.getSimpleName(), baseName));
		Sys.infof(EManager.class, Const.INITIAL_RESOURCE_BUNDLE_LOADED, locale.getDisplayLanguage(), EManager.class.getSimpleName(), baseName);
		/* end of load initial resource bundle */
		Sys.infof(EManager.class, Const.TEMPORARY_LOGGER_LOADED, Const.TEMPORARY_LOGGER_OUTPUT_FILE, Const.TEMPORARY_LOG_PATTERN);
		/* end load temporary logback logger */
		//LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		/* load default properties */
		Properties defProps = Sys.loadDefaultProperties(EManager.class);
		/* end of load default properties */
		/* load startup properties */
		Properties startUpProperties = null;
		if(args != null && args.length > 1) {
			startUpProperties = Sys.loadFromXMLFile(EManager.class, args[0], defProps);
		}
		if(startUpProperties == null) {
			startUpProperties = (startUpProperties = Sys.loadFromXMLFile(EManager.class, Const.STARTUP_PROPERTIES_LOCATION, defProps))  == null ? new Properties(defProps) : startUpProperties;

		}
		setStartUpProperties(startUpProperties);
		/* end of load startup properties */
		/* load default logger */
		String name = Sys.getProperty(EManager.class, Const.MAIN_LOGGER_NAME, Const.DEFAULT_MAIN_LOGGER_NAME);
		String level =  Sys.getProperty(EManager.class, Const.MANAGER_LOGGER_LEVEL, Const.DEFAULTMANAGER_LOGGER_LEVEL);

		Sys.loadDefaultLogger (EManager.class, name, Sys.getLevelFromString(level));
		/* end of load default logger */
		/* load GUIList */
		String guiResource = Sys.getProperty(EManager.class, Const.GUI_RESOURCE, Const.DEFAULT_GUI_RESOURCE);
		try {
			GUIManager.loadGUIList(guiResource);
			Sys.infof(EManager.class, Const.GUI_LIST_LOADED, guiResource);
		} catch (IOException | SAXException | IllegalAccessException | ClassNotFoundException e1) {
			Sys.errorf(EManager.class, Const.FAILED_TO_LOAD_GUI_LIST, guiResource);
			e1.printStackTrace();
			System.exit(1);
		}






		//System.out.println(getStartUpProperties());



		/* Load database data source */
		try {
			DBConnector.createDataSource(EManager.class, getStartUpProperties());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		if(DBConnector.getDataSource() == null) {
			Sys.errorf(EManager.class, Const.DBCONNECTOR_FAILED_ASK);
		}
		else {
			Sys.infof(EManager.class, Const.DBCONNECTOR_ESTABLISHED);
			DBConnector.init();
		}
		/* end load database data source */

		/* load DB table properties */
		try {
			EManager.setDbTable(new DBTable (DBConnector.getDataSource(), DBConnector.tn_properties));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* end load DB Table properties */
		//System.exit(1);
		/* load modules configuration */
		launch(args);





		//System.out.println(rb.getString("okButton"));
		//System.out.println(rb.getString("07"));
		// TODO Auto-generated method stub
	}

	private static void setStartUpProperties(Properties properties) {
		EManager.startUpProperties = properties;
	}

	/**
	 * @return the startupproperties
	 */
	public static Properties getStartUpProperties() {
		return startUpProperties;
	}

	/**
	 * @return the dbTable
	 */
	public static DBTable getDbTable() {
		return dbTable;
	}

	/**
	 * @param dbTable the dbTable to set
	 */
	public static void setDbTable(DBTable dbTable) {
		EManager.dbTable = dbTable;
	}

	public static void setPrimaryStage(String name) {
		EManager.primaryStage = name;

	}

}
