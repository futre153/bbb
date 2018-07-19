package org.pabk.application.emanager.util;

import ch.qos.logback.classic.Level;

public class Const {
	/* common constants */
	/* default module log level */
	public static final Level DEFAULT_MODULE_LOG_LEVEL = Level.TRACE;
	/* resource absolute path */
	static final String RESOURCE_PATH = "/org/pabk/application/emanager/resource";
	/* resource xml path */
	private static final String RELATIVE_XML_RESOURCE_PATH = "xml";
	/*xml resource absolute path */
	public static final String ABSOLUTE_XML_RESOURCE_PATH = RESOURCE_PATH + Const.SLASH + RELATIVE_XML_RESOURCE_PATH;
	/* ralative configuration path */
	static final String RELATIVE_CONFIGURATION_PATH = "conf";
	/* file separator */
	static final String FS = System.getProperty("file.separator");
	/* empty string */
	public static final String EMPTY = "";
	/* properties configuration */
	static final String PROPERTIES_EXTENSION = ".properties.xml";
	private static final String DEFAULT_PROPERTIES_FILENAME = "default.properties.xml";
	private static final String STARTUP_PROPERTIES_FILENAME = "startup.properties";
	static final String PROPERTIES_RELATIVE_PATH = "properties";
	/* resource bundle configuration */
	public static final String EMANAGER_RESOURCE_BUNDLE_FORMAT = "EManager.rb";
	/* default properties location */
	public static final String DEFAULT_PROPERTIES_LOCATION = RESOURCE_PATH + Const.SLASH + PROPERTIES_RELATIVE_PATH + Const.SLASH + DEFAULT_PROPERTIES_FILENAME;
	/* startup properties location */
	public static final String STARTUP_PROPERTIES_LOCATION = RELATIVE_CONFIGURATION_PATH + FS + STARTUP_PROPERTIES_FILENAME;
	/* error messages */
	public static final String PROPERTIES_LOADED_FROM_XML_FILE = "Properties %s \r\n successfully loaded from file %s.";
	public static final String PROPERTIES_LOADED_FROM_XML_RESOURCE = "Properties %s \r\n successfully loaded from resource %s.";
	public static final String ERROR_MESSAGE_NULL_VALUE = "Error message has null value";
	public static final String UNKNOWN_ERROR = "Unknown error or error message is null.";
	public static final String PROPERTIES_STORED = "Properties %s was successfully stored to file %s.";
	public static final String FAILED_STORE_PROPERTIES = "Failed to store properties %s to file %s.";
	public static final String FAILED_DELETE_FILE_EXISTS = "Failed to delete file %s. File does not exists";
	public static final String FILE_DELETED = "File %s successfully deleted.";
	public static final String FAILED_DELETE_FILE_UNKNOWN = "Failed to delete file %s. Unknown reason.";
	public static final String FILE_NOT_CREATED_EXISTS = "File %s will be not created. Allready exists";
	public static final String FILE_CREATED = "File %s was successfully created.";
	public static final String FILE_NOT_CREATED_UNKNOWN = "Failed to create file %s. Reason unknown.";
	public static final String DBCONNECTOR_FAILED = "Failed to establish connection to database. Application will be terminated";
	public static final String DBCONNECTOR_FAILED_ASK = "Failed to establish connection to database. Application will be ask you for database credentials.";
	public static final String DBCONNECTOR_ESTABLISHED = "Connection to database has been successfully established";
	public static final String FAILED_SET_DATASOURCE = "Failed to set datasource";
	public static final String MODULE_LIST_CONFIG_NULL = "Property %s cannot have a null value. Application will be terminated.";
	public static final String WRONG_PRIORITY = "Manager module must have priority 0.";
	public static final String MANAGER_FAILED =  "Module manager failed to start. Application will be terminated.";
	public static final String DUPLICATE_MODULE_ID = "Duplicate module ID. Failed to load module ID ='%s'";
	public static final String REQUIRED_MODULE_NOT_FOUND = "Required module %s was not found in configuration for module %s.";
	public static final String CHECK_REQUIRED_MODULE_START = "Module %s. Found required module %s, [%s]. Required module %s will be checked for its status.";
	public static final String MODULE_FAILED_TO_LOAD = "Failet to load module %s";
	public static final String MODULE_NOT_LOADED = "Required module %s is not loaded yet. Module %s will be loaded now.";
	public static final String CRITICAL_MODULE_NOT_LOADED = "Module %s is critical. Initialization of the application will be terminated.";
	public static final String NON_CRITICAL_MODULE_NOT_LOADED = "Module %s is non critical. Initialization of the application will be continued without this";
	public static final String REQUIRED_MODULE_RUNNING = "Required module %s is running for module %s";
	public static final String REQUIRED_MODULE_NOT_RUNNING = "Required module %s is not running. Check failed.";
	public static final String REQUIRED_MODULES_CHECK_PASSED = "All required modules are running. The checking for module %s was passed. Initilization continues.";
	public static final String REQUIRED_ODULES_CHECK_FAILS = "Some of required module/s is/are are not running. The checking for module %s fails.";
	public static final String WAITING_FAILS = "Module %s failed to check for required module.";
	public static final String USERNAME_NULL = "Database user cannot have a null value";
	public static final String USERNAME_SET = "Database user is set in encrypted form";
	public static final String PASSWORD_NULL = "Database password cannot have a null value";
	public static final String PASSWORD_SET = "Database password is set in encrypted form";

	public static final String SCHEMA_NULL = "Database schema cannot have a null value";
	public static final String SCHEMA_SET = "Database schema  is set to '%s'";
	public static final String PORT_NULL = "Database server port cannot have a null value";
	public static final String PORT_SET = "Database server port is set to %s";
	public static final String SERVER_NULL = "Database server cannot have a null value";
	public static final String SERVER_SET = "Database server is set to '%s'";
	public static final String URL_NULL = "Database URL cannot have a null value";
	public static final String URL_SET = "Database URL is set to '%s'";
	public static final String DRIVER_NULL = "Database driver class cannot have a null value";
	public static final String DRIVER_SET = "Database driver class is set to '%s'";
	public static final String USERNAME_DECODE_FAIL = "Failed to decode username";
	public static final String PASSWORD_DECODE_FAIL = "Failed to decode password";
	public static final String INITIALIZATION_MODULE_START = "Initialization of module %s started on %s";
	public static final String INITIALIZATION_MODULE_END = "Initialization of module %s were ended on %s";
	static final String PROPERTIES_LOADED_FROM_FILE = "Properties for %s has been successsfully loaded from file %s";
	static final String FAILED_TO_LOAD_PROPERTIES_FROM_FILE = "Failed to load properties for %s from file %s. Attempt to read from resources.";
	static final String FILE_NOT_EXIST_IN_CONF = "File %s is not exists in configuration folder. Attempt to read from resources.";
	static final String PROPERTIES_LOADED_FROM_RESOURCE = "Properties for %s has been successfully loades from resource '%s'";
	static final String FAILED_TO_LOAD_PROPERTIES_FROM_RESOURCE = "Failed toload properties for %s from resource %s. New properties will be created.";
	public static final String PROPERTIES_LOADED = "Properties for %s has been successfully loaded";
	public static final String MODULE_LOGGER_FAILED = "Logger for module %s failed to start. The default logger will be used instead.";
	public static final String PROPERTIES_FAILED = "Failed to load properties for %s";
	//static final String FILE_DELETED = "File %s successfully deleted.";
	//static final String FAILED_DELETE_FILE_UNKNOWN = "Failed to delete file %s. Unknown reason.";
	static final String FAILED_DELETE_FILE_NOT_EXISTS = "Failed to delete file %s. File does not exists";
	//static final String FILE_NOT_CREATED_EXISTS = "File %s will be not created. Allready exists";
	static final String SCHEDULER_CREATED = "Scheduler %s has been successfully created for %s.";
	static final String NO_SCHEDULER_CREATED = "No scheduler was created for %s.";
	static final String SCHEDULER_CREATION_ERROR = "Error was found while time scheduler %s were created for %s";
	public static final String LOADED_PROPERTIES = "Properties %s loaded.";
	public static final String NO_SCHEDULER = "No time scheduler was appenden to module %s.";
	public static final String MODULE_WORK = "Module %s is going to start work.";
	public static final String MODULE_SLEEP = "Module %s stopped work and going to start sleep.";
	public static final String SCHEDULER_APPENDED = "Time scheduler %s has been successfully appended to module %s";
	public static final String MODULE_UNDER_SHUTDOWN = "Module %s is under shutdown and will be stopped.";
	public static final String APP_ENDS = "Application ends with no error";
	public static final String APP_ENDS_ERROR = "Application terminated with error";
	public static final String GUI_ITEM_NAME_NULL = "GUI item name cannot have a null value.";
	public static final String GUI_ITEM_FXML_NULL = "FXML resource path for GUI cannot have a null value.";
	public static final String GUI_ITEM_SCENE_NULL = "Flag scene for GUI cannot hsve a null value.";
	public static final String GUI_ITEM_PRIMARY_NULL = "Flag primary for GUI cannot have a null value.";
	public static final String GUI_ITEM_LOADED = "GUI item %s has been successfully loaded";
	public static final String GUI_ITEM_IS_NOT_CONTAINER = "GUI item %s is not a container.";
	public static final String SCENE_CREATED = "Scene created from GUI item %s with initial size (%f x %f).";
	public static final String SCENE_NOT_CREATED = "Failed to create scene from GUI item %s.";
	public static final String STYLESHEET_LOADED = "Stylesheet successfully loaded for GUI item %s from external resource %s.";
	public static final String FAILED_TO_LOAD_STYLESHEET = "Failed to load stylesheet for GUI item %s from externalresource %s.";
	public static final String ICON_LOADED = "Icon successfully loaded for GUI item %s from resource %s.";
	public static final String FAILED_TO_LOAD_ICON = "Failed to load icon for GUI item %s from resource %s.";

	/* EventManager properties keys */
	public static final String MODULE_LIST_CONF = "manager.moduleList.config";
	public static final String MANAGER_MODULE_NAME = "manager.module.name";
	public static final String MAX_LOOPS_OF_CHECKING = "manager.module.maxLoopsOfChecking";
	public static final String WAIT_FOR_CHECKING_INTERVAL = "manager.module.waitForCheckingInterval";
	public static final String MAIN_LOGGER_NAME = "manager.logger.name";
	//static final String WAIT_INTERVAL_SUFFIX = ".waitInterval";
	/* EventManager properties values */
	public static final String DEFAULT_MAIN_LOGGER_NAME = "main";
	//public static final String DEFAULTWAIT_INTERVAL = "0";

	/* GUIManager properties */
	/* GUIManager properties keys */
	public static final String  RESOURCE_BUNDLE_BASE_NAME = "GUIManager.resourceBundle.baseName";
	public static final String GUI_RESOURCE = "GUIManager.resource";

	/* GUIManager properties default values */
	public static final String  DEFAULT_RESOURCE_BUNDLE_BASE_NAME = "dictionary";
	public static final String GUI_LIST_FILENAME = "gui-list.xml";
	/* GUIManager XML constant */
	//private static final String GUI_LIST_RESOURCE_KEY = "GUIManager.GUIList.resource";
	//private static final String DEFAULT_GUI_RESOURCE_PATH = "";
	public static final String GUI_ITEM_TAGNAME = "fxml-item";
	public static final String GUI_ITEM_NAME_TAGNAME = "name";
	public static final String GUI_ITEM_FXML_TAGNAME = "fxml";
	public static final String GUI_ITEM_STYLESHEET_TAGNAME = "stylesheet";
	public static final String GUI_ITEM_CONTROLLER_TAGNAME = "controller";
	public static final String GUI_ITEM_DESCRIPTION_TAGNAME = "description";
	public static final String GUI_ITEM_TITLE_TAGNAME = "title";
	public static final String GUI_ITEM_STAGE_STYLE_TAGNAME = "stage-style";
	public static final String GUI_ITEM_WIDTH_TAGNAME = "width";
	public static final String GUI_ITEM_HIGHT_TAGNAME = "height";
	public static final String GUI_ITEM_SCENE_TAGNAME = "scene";
	public static final String GUI_ITEM_PRIMARY_TAGNAME = "primary";
	public static final String GUI_ITEM_ICON_TAGNAME = "icon";
	public static final String GUI_ITEM_MODALITY_TAGNAME = "modality";
	public static final String GUI_ITEM_STARTUP_TAGNAME = "startup";
	public static final String GUI_LANGUAGE_ATTRIBUTE_NAME = "language";
	public static final String GUI_RESOURCE_BUNDLE_CLASS_NAME_ATTRIBUTE_NAME ="resourceBundleClass";
	public static final String GUI_RESOURCE_BUNDLE_BASE_NAME_ATTRIBUTE_NAME = "resourceBundleName";
	public static final String GUI_RECOURCE_BUNDLE_CONTROL_CLASS_NAME_ATTRIBUTE_NAME = "resourceBundleControl";




	/* Scheduler properties keys */
	static final String SCHEDULER_CLASSNAME_SUFFIX = ".scheduler.className";
	static final String SCHEDULER_INTERVAL_SUFFIX = ".scheduler.interval";
	static final String SCHEDULER_FIRST_TIME_PARAM_SUFFIX = ".scheduler.firstTimeParameter";
	public static final String SCHEDULER_OPTIONS_SUFFIX = ".scheduler.options";


	/* Scheduler properties default values */
	static final String DEFAULT_SCHEDULER_INTERVAL = "600000";
	static final String DEFAULT_SCHEDULER_FIRST_TIME_PARAM = Integer.toString(TimerImpl.THE_NEXT_MULTIPLE_OF_INTERVAL_AT_THIS_HOUR);
	static final String DEFAULT_SCHEDULER_OPTIONS = null;
	static final String DEFAULT_DELIMITER = new String(new char[]{Const.COMMA});

	/* DBConnector properties */


	/* DBConnector properties default values */
	public static final String DEFAULT_DATABASE_SCHEMA = "eman";
	public static final String DEFAULT_DATABASE_USERNAME = null;
	public static final String DEFAULT_DATABASE_PASSWORD = null;
	public static final String DEFAULT_DATABASE_PORT = "3306";
	public static final String DEFAULT_DATABASE_SERVER = "P3600X006";
	public static final String DEFAULT_DATABASE_URL_PATTERN = "jdbc:mysql://%s:%d/%s?useSSL=false";
	public static final String DEFAULT_DATABASE_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String DEFAULT_DATABASE_CHECK_IN_SQL = "select * from properties";
	/* DBConnector properties keys */
	public static final String DATABASE_SERVER = "database.server";
	public static final String DATABASE_USERNAME = "database.username";
	public static final String DATABASE_PASSWORD = "database.password";
	public static final String DATABASE_PORT = "database.port";
	public static final String DATABASE_SCHEMA = "database.schema";
	public static final String DATABASE_URL_PATTERN = "database.URLPattern";
	public static final String DATABASE_DRIVER_CLASS_NAME = "database.driver.className";
	public static final String DATABASE_CHECK_IN_SQL = "database.checkInSQL";
	public static final String DATABASE_OBTAIN_DATASOURCE_METHOD = "database.obtainDataSourceMethod";



	public static final String JDBC_MYSQL_IDENTIFIER_QUOTE = "jdbc.mysql.identifier.quote";
	public static final String JDBC_MYSQL_RESTRICTED_SEPARATOR = "jdbc.mysql.restricted.separator";
	public static final String JDBC_MYSQL_RESTRICTED = "jdbc.mysql.restricted";
	public static final String JDBC_MYSQL_RESTRICTED_DEFAULT = "key,to,desc";
	public static final String JDBC_AUTOCOMMIT = "jdbc.autoCommit";
	public static final String DEFAULT_JDBC_AUTOCOMMIT = "false";
	public static final String ERROR_DBCONNECTOR_DATASOURCE_FAILS = "Datasource is not reacheable. Database connector will be stopped!";
	public static final String JDBC_DEBUG = "jdbc.debug";
	public static final String DEFAULT_JDBC_DEBUG = "false";
	public static final String JDBC_MYSQL_FUNCTION_COUNT = "jdbc.mysql.function.count";
	public static final String DEFAULT_JDBC_MYSQL_FUNCTION_COUNT = "COUNT";
	public static final String DB_BATCH_CREATE_DB = "database.batch.createDb";
	public static final String DEFAULT_DB_BATCH_CREATE_DB = "/org/pabk/application/emanager/resource/db/create-table.sql";
	public static final String DB_BATCH_CREATE_LOG_TABLES = "database.batch.createLogTables";
	public static final String DEFAULT_DB_BATCH_CREATE_LOG_TABLES = "/org/pabk/emanager/resource/db/mysql.sql";
	public static final String DB_BATCH_ENCODING = "database.batch.encoding";
	public static final String DEFAULT_DB_BATCH_ENCODING = "UTF-8";
	public static final String DB_SQL_SHOW_COLUMNS_COL = "database.sql.showColumns.column";
	public static final String DEFAULT_DB_SQL_SHOW_COLUMNS_COL = "Field";
	public static final String DB_SQL_SHOW_TABLES_COL = "database.sql.showTables.column";
	public static final String DEFAULT_DB_SQL_SHOW_TABLES_COL = "Tables_in_%s";
	public static final String DB_SQL_SHOW_TABLES = "database.sql.showTables";
	public static final String DEFAULT_DB_SQL_SHOW_TABLES = "SHOW TABLES FROM `%s`";
	public static final String DB_SQL_SHOW_COLUMNS = "database.sql.showColumns";
	public static final String DEFAULT_DB_SQL_SHOW_COLUMNS = "SHOW COLUMNS FROM `%s`.`%s`";
	public static final String DEFAULT_DATABASE_OBTAIN_DATASOURCE_METHOD = "getDataSource";

	/* Module state flags value */
	public static final int RUNNING_FLAG = 0x80;
	public static final int INITIALIZATION_FLAG = 0x40;
	public static final int INITIALIZED_FLAG = 0x20;
	public static final int SLEEPING_FLAG = 0x10;
	public static final int WORKING_STATE = 0x08;
	public static final int UNDER_SHUTDOWN_FLAG = 0x04;

	/* file suffixes */
	public static final String XML_SUFFIX = "xml";

	/* log properties */
	/* date format for log output */
	public static final String LOG_DATE_FORMAT = "HH:mm:ss.SSS";
	/* log severity definition */
	public static final int ALL		= 0;
	public static final int TRACE 	= 1;
	public static final int DEBUG	= 2;
	public static final int INFO 	= 3;
	public static final int WARN 	= 4;
	public static final int ERROR 	= 5;
	public static final int FATAL 	= 6;
	static final int OFF		 	= 7;
	/*	text representation of log severity */
	public static final String LOGGER_TRACE_TEXT = "TRACE:";
	public static final String LOGGER_DEBUG_TEXT = "DEBUG:";
	public static final String LOGGER_ERROR_TEXT = "ERROR:";
	public static final String LOGGER_INFO_TEXT = "INFO:";
	public static final String LOGGER_WARN_TEXT = "WARN:";
	/* character names definition */
	public static final char SPACE = ' ';				//32
	public static final char COMMA = ',';				//44
	public static final char SLASH = '/';				//47
	public static final char COLON = ':';				//58
	public static final char CHAR_SEMICOLON = ';';		//59
	public static final char OPENING_BRACKET = '[';		//91
	public static final char CLOSING_BRACKET = ']';		//93
	public static final char UNDERSCORE = '_';			//95
	public static final char GRAVE_ACCENT = '`';		//96

	/* XML Support tagnames */
	static final String MODULE_LIST_TAGNAME = "module-list";
	static final String MODULE_TAGNAME = "module";
	static final String MODULE_NAME_TAGNAME = "module-name";
	static final String MODULE_CLASS_TAGNAME = "module-class";
	static final String MODULE_PRIORITY_TAGNAME = "module-priority";
	static final String MODULE_DESCRIPTION_TAGNAME = "module-description";
	static final String MODULE_REQUIRED_MODULES_TAGNAME = "required-modules";
	static final String MODULE_CRITICAL_TAGNAME = "critical";
	public static final int DEFAULT_MAX_LOOPS_OF_CHECKING = 50;
	public static final long DEFAULT_WAIT_FOR_CHECKING_INTERVAL = 2000;
	public static final String MANAGER_LOGGER_LEVEL = "manager.logger.level";
	public static final String DEFAULTMANAGER_LOGGER_LEVEL = "INFO";
	public static final String LOGGER_LOADED = "Logger %s has been successfully loaded at the level %s";

	/* dateformats */
	public static final String COMMON_DATE_FORMAT = "dd.MM.yyyy 'at' HH:MM:ss";
	public static final String DEFAULT_GUI_RESOURCE = RESOURCE_PATH + Const.SLASH + RELATIVE_XML_RESOURCE_PATH + Const.SLASH + GUI_LIST_FILENAME;
	public static final String GUI_DEFAULT_LANGUAGE = "en";
	public static final String RESOURCE_BUNDLE_GET_BUNDLE_METHOD_NAME = "getBundle";
	public static final String DEFAULT_COUNTRY = "US";


	public static final String USER_MANAGEMENT_GUI_KEY = "GUIManager.userManagement.gui";
	public static final String USER_MANAGEMENT_GUI_DEFAAULT = "user-mgtm";















}
