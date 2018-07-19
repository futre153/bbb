package org.pabk.application;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.pabk.application.emanager.module.DBConnector;
import org.pabk.application.emanager.module.EventManager;
import org.pabk.application.emanager.module.GUIManager;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.DBTable;
import org.pabk.application.emanager.util.Sys;
import org.xml.sax.SAXException;

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
			GUIManager.loadStage(EManager.class, primary, EManager.getPrimaryStage(), true);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	private static String getPrimaryStage() {
		// TODO Auto-generated method stub
		return EManager.primaryStage;
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
/*


		ArrayList<String> xxx = new ArrayList<String>();
		xxx.getClass().getTypeName();


		Field stringListField = EManager.class.getDeclaredField("stringList");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.

        Field integerListField = EManager.class.getDeclaredField("integerList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass); // class java.lang.Integer.

		System.exit(1);
*/
		//ResourceBundle.Control control = new EManagerResourceBundleControl();
		//ResourceBundle rb = ResourceBundle.getBundle("dictionary", control);

		//System.out.println(rb.getString("okButton"));
		//rb = ResourceBundle.getBundle("dictionary", Locale.US, control);
		//System.out.println(rb.getString("okButton"));
		//System.exit(1);
		/* Load srartup properties */
		Properties defProps = Sys.loadDefaultProperties(EManager.class);
		Properties startUpProperties = null;
		if(args != null && args.length > 1) {
			startUpProperties = Sys.loadFromXMLFile(EManager.class, args[0]);
		}
		if(startUpProperties == null) {
			startUpProperties = (startUpProperties = Sys.loadFromXMLFile(EManager.class, Const.STARTUP_PROPERTIES_LOCATION))  == null ? new Properties(defProps) : startUpProperties;
		}
		setStartUpProperties(startUpProperties);

		System.out.println(getStartUpProperties());
		/* end load startup properties */
		/* load default logger */
		String name = Sys.getProperty(EManager.class, Const.MAIN_LOGGER_NAME, Const.DEFAULT_MAIN_LOGGER_NAME);
		String level =  Sys.getProperty(EManager.class, Const.MANAGER_LOGGER_LEVEL, Const.DEFAULTMANAGER_LOGGER_LEVEL);

		Sys.loadDefaultLogger (EManager.class, name, Sys.getLevelFromString(level));
		/* load GUIManager */
		String guiResource = Sys.getProperty(EManager.class, Const.GUI_RESOURCE, Const.DEFAULT_GUI_RESOURCE);
		try {
			GUIManager.loadGUIList(guiResource);
			launch(args);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SAXException | IOException
				| ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/* end load GUIManager */

		/* end load default logger */
		/* Load database data source */
		DBConnector.createDataSource(EManager.class, getStartUpProperties());
		if(DBConnector.getDataSource() == null) {
			Sys.error(EManager.class, Const.DBCONNECTOR_FAILED_ASK);
		}
		else {
			Sys.info(EManager.class, Const.DBCONNECTOR_ESTABLISHED);
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

		/* load modules configuration */
		try {
		//EventManager.loadModulesConfiguration(EManager.getStartUpProperties());

			 Sys.info(EManager.class, Const.APP_ENDS);
		 }
		 catch (Exception e) {
			 //e.printStackTrace();
			 Sys.error(EManager.class, Const.APP_ENDS_ERROR);
		 }




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
