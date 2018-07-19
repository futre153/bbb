package org.pabk.application.emanager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.xml.parsers.ParserConfigurationException;

import org.pabk.application.EManager;
import org.pabk.application.emanager.module.DBConnector;
import org.pabk.application.emanager.module.ModuleImpl;
import org.pabk.application.emanager.module.ModuleListX;
import org.pabk.application.emanager.module.ModuleX;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.db.DBAppender;
import ch.qos.logback.core.db.DataSourceConnectionSource;

public class Sys {

	private static DataSourceConnectionSource connectionSource;

	private static Logger defaultLogger;

	private static String defaultLoggerName;
	private static Level defaultLoggerLevel;

	/*
	public static Logger getLogger() {
		return defaultLogger;
	}
*/
	public static Properties loadDefaultProperties(Object caller) {
			return Sys.loadFromXMLResource(caller, Const.DEFAULT_PROPERTIES_LOCATION);
	}

	public static Properties loadFromXMLResource(Object caller, String xml) {
		Properties props = new Properties();
		try {
			props.loadFromXML(Sys.class.getResourceAsStream(xml));
			Sys.info(caller, Const.PROPERTIES_LOADED_FROM_XML_RESOURCE, props, xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Sys.error(caller, e);
			//e.printStackTrace();
			props = null;
		}
		return props;
	}
	public static Properties loadFromXMLFile(Object caller, String xml) {
		Properties props = new Properties();
		try {
			props.loadFromXML(new FileInputStream (xml));
			Sys.info(caller, Const.PROPERTIES_LOADED_FROM_XML_FILE, props, xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Sys.error(caller, e);
			//e.printStackTrace();
			props = null;
		}
		return props;
	}
	public static boolean storePrepertiesToFile(Object caller, Properties prop, String xml) {
		File f = new File(xml);
		System.out.println(f.getAbsolutePath());
		if (f.exists()) {
			Sys.deleteFile(caller, f);
		}
		Sys.createNewFile(caller, f);
		try {
			prop.storeToXML(new FileOutputStream(f), null);
			Sys.info(caller, Const.PROPERTIES_STORED, prop, f);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			Sys.log(caller, null, null, Const.ERROR, e.getMessage());
			Sys.error(caller, e);
			Sys.log(caller, null, null, Const.ERROR, Const.FAILED_STORE_PROPERTIES, prop,f);
		}
		return false;
	}

	private static boolean createNewFile(Object caller, File f) {
		if (f.exists()) {
			Sys.warn(caller, Const.FILE_NOT_CREATED_EXISTS, f);
			return true;
		}
		else {
			try {
				if (f.createNewFile()) {
					Sys.info(caller, Const.FILE_CREATED, f);
					return true;
				}
				else {
					Sys.error(caller, Const.FILE_NOT_CREATED_UNKNOWN, f);
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				Sys.error(caller, e);
				return false;
			}
		}
	}

	private static boolean deleteFile(Object caller, File f) {
		if (f.exists()) {
			if (f.delete()) {
				Sys.info (caller, Const.FILE_DELETED, f);
				return true;
			}
			else {
				Sys.error(caller, Const.FAILED_DELETE_FILE_UNKNOWN, f);
				return false;
			}
		}
		else {
			Sys.warn(caller, Const.FAILED_DELETE_FILE_EXISTS, f);
			return true;
		}
	}



	public static void info(Object caller, String unformatedMessage, Object ... args) {
		Object c = caller == null ? Sys.class : caller;
		Logger l = c == null || !(c instanceof ModuleImpl) ? Sys.getLogger() : ((ModuleImpl) c).getLogger();
		Sys.log(c, l, null, Const.INFO, unformatedMessage, args);
	}
	public static void trace(Object caller, String unformatedMessage, Object ... args) {
		Object c = caller == null ? Sys.class : caller;
		Logger l = c == null || !(c instanceof ModuleImpl) ? Sys.getLogger() : ((ModuleImpl) c).getLogger();
		Sys.log(c, l, null, Const.TRACE, unformatedMessage, args);
	}
	public static void error(Object caller, String unformatedMessage, Object ... args) {
		Object c = caller == null ? Sys.class : caller;
		Logger l = c == null || !(c instanceof ModuleImpl) ? Sys.getLogger() : ((ModuleImpl) c).getLogger();
		Sys.log(c, l, null, Const.ERROR, unformatedMessage, args);
	}
	public static void error(Object caller, Throwable error) {
		Object c = caller == null ? Sys.class : caller;
		Logger l = c == null || !(c instanceof ModuleImpl) ? Sys.getLogger() : ((ModuleImpl) c).getLogger();
		Sys.log(c, l, null, Const.ERROR, error.getMessage() == null ? Const.UNKNOWN_ERROR : error.getMessage());
	}
	public static void warn(Object caller, Throwable error) {
		Object c = caller == null ? Sys.class : caller;
		Logger l = c == null || !(c instanceof ModuleImpl) ? Sys.getLogger() : ((ModuleImpl) c).getLogger();
		Sys.log(c, l, null, Const.ERROR, error.getMessage() == null ? Const.UNKNOWN_ERROR : error.getMessage());
	}
	public static void warn(Object caller, String unformatedMessage, Object ... args) {
		Object c = caller == null ? Sys.class : caller;
		Logger l = c == null || !(c instanceof ModuleImpl) ? Sys.getLogger() : ((ModuleImpl) c).getLogger();
		Sys.log(c, l, null, Const.WARN, unformatedMessage, args);
	}
	public static void debug(Object caller, String unformatedMessage, Object ... args) {
		Object c = caller == null ? Sys.class : caller;
		Logger l = c == null || !(c instanceof ModuleImpl) ? Sys.getLogger() : ((ModuleImpl) c).getLogger();
		Sys.log(c, l, null, Const.DEBUG, unformatedMessage, args);
	}


	public static void log(Object caller, Logger log, PrintStream ps, int severity, String unformatedMessage, Object ... args) {
		String formatedMsg = null;
		try {
			formatedMsg = unformatedMessage == null ? Const.ERROR_MESSAGE_NULL_VALUE : String.format(unformatedMessage, args);
		}
		catch (IllegalFormatException e) {
			formatedMsg = unformatedMessage;
		}
		switch(severity) {
		case Const.ALL:
		case Const.TRACE:
			break;
		case Const.DEBUG:
			break;
		case Const.INFO:
			break;
		case Const.WARN:
			break;
		case Const.ERROR:
			break;
		case Const.OFF:
		default:
			break;
		}
		if(log != null) {
			switch(severity) {
			case Const.ALL:
			case Const.TRACE:
				log.trace(formatedMsg);
				break;
			case Const.DEBUG:
				log.debug(formatedMsg);
				break;
			case Const.INFO:
				log.info(formatedMsg);
				break;
			case Const.WARN:
				log.warn(formatedMsg);
				break;
			case Const.ERROR:
				log.error(formatedMsg);
				break;
			case Const.OFF:
			default:
				break;
			}
		}
		else {
			SimpleDateFormat format = new SimpleDateFormat(Const.LOG_DATE_FORMAT);
			(ps == null ? (severity == Const.FATAL || severity == Const.ERROR || severity == Const.WARN ? System.err : System.out) : ps).println(format.format(new Date()) + Const.SPACE + Const.OPENING_BRACKET + caller.toString() + Const.CLOSING_BRACKET + Const.SPACE + (severity == Const.ALL ? Const.SPACE : (Const.SPACE + Sys.getLevelString(severity) + Const.SPACE)) + formatedMsg);
		}
	}

	public static String getLevelString(int severity) {
		switch(severity) {
		case Const.ALL:
			return Const.LOGGER_INFO_TEXT;
		case Const.TRACE:
			return Const.LOGGER_TRACE_TEXT;
		case Const.DEBUG:
			return Const.LOGGER_DEBUG_TEXT;
		case Const.INFO:
			return Const.LOGGER_INFO_TEXT;
		case Const.WARN:
			return Const.LOGGER_WARN_TEXT;
		case Const.ERROR:
			return Const.LOGGER_ERROR_TEXT;
		case Const.OFF:
		default:
			return Const.EMPTY;
		}
	}
	public static String getProperty(Object caller, String key, String defaultValue) {
		//return defaultValue;
		Properties properties = caller == null ? EManager.getStartUpProperties() : (caller instanceof ModuleImpl ? ((ModuleImpl) caller).getProperties() : EManager.getStartUpProperties());
		System.out.println(key + " ='" + properties.getProperty(key) + "'");
		return properties == null || key == null ?  defaultValue : properties.getProperty(key, defaultValue);
	}
	public static ModuleListX loadModuleListFromXML(Properties properties, String key, ModuleListX modulesX) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		String xmlPath = Sys.getProperty(Sys.class, key, null);
		if(xmlPath == null) {
			throw new NullPointerException(String.format(Const.MODULE_LIST_CONFIG_NULL, key));
		}
		Document document = XMLSupport.loadXMLFile(xmlPath);
		ArrayList<Element> nodelist = XMLSupport.getChildrenElementsByTagName(document.getDocumentElement(), Const.MODULE_TAGNAME);
		//nodelist =

		for(int i = 0; i < nodelist.size(); i ++) {
			ModuleX modulex = new ModuleX();
			modulex.setName(XMLSupport.getChildrenElementsByTagName(nodelist.get(i), Const.MODULE_NAME_TAGNAME).get(0).getTextContent());
			modulex.setClassName(XMLSupport.getChildrenElementsByTagName(nodelist.get(i), Const.MODULE_CLASS_TAGNAME).get(0).getTextContent());
			//modulex.setName(XMLSupport.getChildrenElementsByTagName(nodelist.get(i), MODULE_CLASS_TAGNAME).get(0).getTextContent());
			modulex.setPriority(Integer.parseInt(XMLSupport.getChildrenElementsByTagName(nodelist.get(i), Const.MODULE_PRIORITY_TAGNAME).get(0).getTextContent()));
			modulex.setDescription(XMLSupport.getChildrenElementsByTagName(nodelist.get(i), Const.MODULE_DESCRIPTION_TAGNAME).get(0).getTextContent());
			modulex.setCritical(Boolean.parseBoolean((XMLSupport.getChildrenElementsByTagName(nodelist.get(i), Const.MODULE_CRITICAL_TAGNAME).get(0).getTextContent())));
			ArrayList<Element> x = new ArrayList<Element>();
			x = XMLSupport.getChildrenElementsByTagName(XMLSupport.getChildrenElementsByTagName(nodelist.get(i), Const.MODULE_REQUIRED_MODULES_TAGNAME).get(0), Const.MODULE_NAME_TAGNAME);
			ArrayList<String> xx= new ArrayList<String>();
			for (int j = 0; j < x.size(); j ++) {
				xx.add(x.get(j).getTextContent());
			}
			modulex.setRequired(xx);
			modulesX.put(modulex.getName(), modulex);
			}
		return modulesX;
		}

	public static void startConnectionSourceForLogger() {
		if(Sys.connectionSource != null) {
			if(!Sys.connectionSource.isStarted()) {
				Sys.connectionSource.start();
			}
		}

	}
	public static void stopConnectionSourceForLogger() {
		if(Sys.connectionSource != null) {
			if(Sys.connectionSource.isStarted()) {
				Sys.connectionSource.start();
			}
		}
	}
	public static void loadDefaultLogger(Object caller, String name, Level level) {
		Sys.defaultLoggerName = name;
		Sys.defaultLoggerLevel = level;
		Logger log = Sys.loadLogger(name, level);
		if(log != null) {
			Sys.startLogger(log.getName());
			Sys.info(caller, Const.LOGGER_LOADED, name, level);
		}
	}
	public static void stopLogger(String name) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger log = lc.getLogger(name);
		DBAppender appender = (DBAppender) log.getAppender(name);
		appender.stop();

	}
	public static void startLogger(String name) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger log = lc.getLogger(name);
		DBAppender appender = (DBAppender) log.getAppender(name);
		appender.start();

	}
	public static Logger getLogger() {

		Logger log = Sys.defaultLogger;
		if(log == null) {
			log = loadLogger(Sys.defaultLoggerName, Sys.defaultLoggerLevel);
			if(log != null) {
				Sys.setLogger(log);
			}
		}
		return log;
	}

	private static void setLogger(Logger log) {
		Sys.defaultLogger = log;

	}

	public static DataSourceConnectionSource setConnectionSourceForLogger() {
		if(DBConnector.getDataSource() != null) {
			Sys.connectionSource = new DataSourceConnectionSource();
			Sys.connectionSource.setDataSource(DBConnector.getDataSource());
		}
		return Sys.connectionSource;
	}



	public static Logger loadLogger (String loggerName, Level level) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger log = null;
		DataSourceConnectionSource cs = Sys.getConnectionSource();



		if(cs != null) {
			DBAppender appender = new DBAppender();
			appender.setName(loggerName);
			appender.setConnectionSource(Sys.connectionSource);
			log = lc.getLogger(loggerName);
			log.setLevel(level);
			log.addAppender(appender);
		}
		if(log!= null) {
			Sys.startLogger(loggerName);
		}
		return log;
	}

	private static DataSourceConnectionSource getConnectionSource() {
		if(Sys.connectionSource == null) {
			Sys.setConnectionSourceForLogger();
		}
		Sys.startConnectionSourceForLogger();
		return Sys.connectionSource;
	}
	public static Level getLevelFromString(String level) {
		if(level != null) {
			if(level.equalsIgnoreCase(Level.ALL.toString())) {
				return Level.ALL;
			}
			else if (level.equalsIgnoreCase(Level.TRACE.toString())) {
				return Level.TRACE;
			}
			else if (level.equalsIgnoreCase(Level.DEBUG.toString())) {
			return Level.DEBUG;
			}
			else if (level.equalsIgnoreCase(Level.INFO.toString())) {
				return Level.INFO;
			}
			else if (level.equalsIgnoreCase(Level.WARN.toString())) {
				return Level.WARN;
			}
			else  if (level.equalsIgnoreCase(Level.ERROR.toString())) {
				return Level.ERROR;
			}
			else {
				return Level.INFO;
			}
		}
		else {
			return Level.INFO;
		}
	}
	private static boolean createNewFile(Object caller, File f, Logger log) {
		if (f.exists()) {
			Sys.warn(caller, Const.FILE_NOT_CREATED_EXISTS, f);
			return true;
		}
		else {
			try {
				if (f.createNewFile()) {
					Sys.info(caller, Const.FILE_CREATED, f);
					return true;
				}
				else {
					Sys.error(caller, Const.FILE_NOT_CREATED_UNKNOWN, f);
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				Sys.error(caller, e.getMessage());
				return false;
			}
		}
	}

	private static boolean deleteFile(Object caller, File f, Logger log) {
		if (f.exists()) {
			if (f.delete()) {
				Sys.info(caller, Const.FILE_DELETED, f);
				return true;
			}
			else {
				Sys.warn(caller, Const.FAILED_DELETE_FILE_UNKNOWN, f);
				return false;
			}
		}
		else {
			Sys.warn(caller, Const.FAILED_DELETE_FILE_NOT_EXISTS, f);
			return true;
		}
	}

	public static boolean storePrepertiesToFile(Object caller, Properties prop, Logger log) {
		String proPath = Const.RELATIVE_CONFIGURATION_PATH + Const.FS + caller.toString() + Const.PROPERTIES_EXTENSION;
		File f = new File(proPath);
		System.out.println(f.getAbsolutePath());
		if (f.exists()) {
			Sys.deleteFile(caller, f, log);
		}
		Sys.createNewFile(caller, f, log);
		try {
			prop.storeToXML(new FileOutputStream(f), null);
			Sys.info(caller, Const.PROPERTIES_STORED, prop, f);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();Sys.log(caller, null, null, Const.ERROR, e.getMessage());
			Sys.error(caller, Const.FAILED_STORE_PROPERTIES, prop,f);
		}
		return false;
	}
	public static Properties loadProperties(Object obj) throws Exception {
		String proPath = Const.RELATIVE_CONFIGURATION_PATH + Const.FS + obj.toString() + Const.PROPERTIES_EXTENSION;
		Logger log = obj instanceof ModuleImpl ? ((ModuleImpl) obj).getLogger() : Sys.getLogger();
		Properties props = new Properties();
		File f = new File(proPath);
			if (f.exists()) {
				try {
					props.loadFromXML(new FileInputStream(f));
					Sys.info(obj, Const.PROPERTIES_LOADED_FROM_FILE, obj.toString(), f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					props = null;
					Sys.error(obj, Const.FAILED_TO_LOAD_PROPERTIES_FROM_FILE,  obj.toString(), f);
				}
			}
			else {
				props = null;
				Sys.log(obj, log, null, Const.WARN, Const.FILE_NOT_EXIST_IN_CONF, f);
			}
		if(props == null) {
			System.out.println(Sys.class.getResource("."));
			String resourcePath = Const.RESOURCE_PATH + Const.SLASH + Const.PROPERTIES_RELATIVE_PATH + Const.SLASH + obj.toString() + Const.PROPERTIES_EXTENSION;
			props = new Properties();
			try {
				props.loadFromXML(Sys.class.getResourceAsStream(resourcePath));
				Sys.info(obj, Const.PROPERTIES_LOADED_FROM_RESOURCE, obj.toString(), resourcePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				props = null;
				Sys.error(obj, Const.FAILED_TO_LOAD_PROPERTIES_FROM_RESOURCE, obj.toString(), resourcePath);
				throw e;

			}
		}
		return props == null ? new Properties(): props;
	}
	public static  TimerImpl getScheduler(Object caller, Sleeper lock, String prefix) {
		String className = Sys.getProperty(caller, prefix + Const.SCHEDULER_CLASSNAME_SUFFIX, null);
		TimerImpl retValue = null;
		if(className != null && className.length() > 0) {
			long interval = Long.parseLong(Sys.getProperty(caller, prefix + Const.SCHEDULER_INTERVAL_SUFFIX, Const.DEFAULT_SCHEDULER_INTERVAL));
			int firstTimeParam = Integer.parseInt(Sys.getProperty(caller, prefix + Const.SCHEDULER_FIRST_TIME_PARAM_SUFFIX, Const.DEFAULT_SCHEDULER_FIRST_TIME_PARAM));
			long[] opts = Sys.StringArrayToLongArray(Sys.getProperty(caller, prefix + Const.SCHEDULER_OPTIONS_SUFFIX, Const.DEFAULT_SCHEDULER_OPTIONS), Const.DEFAULT_DELIMITER);
			try {
				Class<?> timer = Class.forName(className);
				Class<?>[] params = new Class<?>[]{lock.getClass(), long.class, int.class, opts.getClass()};
				retValue = (TimerImpl) timer.getDeclaredConstructor(params).newInstance(new Object[]{lock, interval, firstTimeParam, opts});
				 Sys.info(caller, Const.SCHEDULER_CREATED, caller);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				retValue = null;
				e.printStackTrace();
				Sys.error(caller, Const.SCHEDULER_CREATION_ERROR, caller);
			}
		}
		if(retValue == null) {
			Sys.warn(caller, Const.NO_SCHEDULER_CREATED, caller);
		}
		return retValue;
	}
	private static long[] StringArrayToLongArray(String array, String delimiter) {
		long[] l;
		try {
			if(array != null && array.length() > 0) {
				String[] a = array.split(delimiter);
				l = new long[a.length];
				for(int i = 0; i < a.length; i ++) {
					l[i] = Long.parseLong(a[i]);
				}
			}
			else {
				l = new long[0];
			}
		}
		catch (Exception e) {
			l = new long[0];
		}
		return l;
	}

	public static Locale getLocale(String language) {
		String[] array = language.split(new String(new char[]{Const.UNDERSCORE}));
		String l = Const.GUI_DEFAULT_LANGUAGE;
		String c = Const.DEFAULT_COUNTRY;
		if(array.length > 0 ) {
			l = array[0];
		}
		if(array.length > 1) {
			l = array[0];
			c = array[1];
		}
		Locale[] locales = Locale.getAvailableLocales();
		Locale locale = null;
		for(int i = 1; i < locales.length; i ++) {
			if(locales[i].getCountry().equals(c) && locales[i].getLanguage().equals(l)) {
				locale = locales[i];
				break;
			}
		}
		return locale == null ? Locale.getDefault() : locale;
	}

	public static ResourceBundle getBundle(String resourceBundleClassName, String baseName, Locale locale, String resourceBundleConttrolClassName) {
		ResourceBundle rb = null;
		try {
			ResourceBundle.Control rbc = (Control) Class.forName(resourceBundleConttrolClassName).newInstance();
			return (ResourceBundle) ResourceBundle.class.getMethod(Const.RESOURCE_BUNDLE_GET_BUNDLE_METHOD_NAME, new Class<?>[] {baseName.getClass(), locale.getClass(), Control.class}).invoke(null, new Object[] {baseName, locale, rbc});

		} catch (Exception  e) {
			e.printStackTrace();
		}
		return rb;
	}
	public static <T> void addItemOnPosition(List<T> list, T item, int index) {
		if(index < list.size()) {
			list.remove(index);
			list.add(index, item);
		}
		else if (index == list.size()) {
			list.add(item);
		}
		else {
			for (int i = index; i < list.size(); i ++) {
				list.add(null);
			}
			list.add(item);
		}
	}
}
