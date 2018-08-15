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


	public static final String ERROR_MESSAGE_NULL_VALUE = "Error message has null value";
	public static final String UNKNOWN_ERROR = "Unknown error or error message is null.";
	public static final String PROPERTIES_STORED = "Properties %s was successfully stored to file %s.";
	public static final String FAILED_STORE_PROPERTIES = "Failed to store properties %s to file %s.";





	public static final String FILE_NOT_CREATED_UNKNOWN = "Failed to create file %s. Reason unknown.";
	public static final String DBCONNECTOR_FAILED = "Failed to establish connection to database. Application will be terminated";


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
	public static final String USERNAME_NULL = 										"em-00000001";
	public static final String USERNAME_SET = 										"em-00000002";
	public static final String USERNAME_DECODE_FAIL = 								"em-00000003";
	public static final String PASSWORD_NULL = 										"em-00000004";
	public static final String PASSWORD_SET = 										"em-00000005";
	public static final String PASSWORD_DECODE_FAIL = 								"em-00000006";
	public static final String INITIAL_RESOURCE_BUNDLE_LOADED = 					"em-00000007";
	public static final String TEMPORARY_LOGGER_LOADED = 							"em-00000008";
	public static final String PROPERTIES_LOADED_FROM_XML_RESOURCE = 				"em-00000009";
	public static final String FAILED_TO_LOAD_PROPERTIES_XML_RESOURCE = 			"em-00000010";
	public static final String PROPERTIES_LOADED_FROM_XML_FILE = 					"em-00000011";
	public static final String FAILED_TO_LOAD_PROPERTIES_FROM_XML_FILE = 			"em-00000012";
	public static final String DEFAULT_LOGGER_LOADED =								"em-00000013";
	public static final String DEFAULT_LOGGER_NOT_LOADED =							"em-00000014";
	public static final String GUI_LIST_LOADED_FROM_SOURCE = 						"em-00000015";
	public static final String FAILED_TO_LOAD_GUI_LIST_LOADED_FROM_SOURCE = 		"em-00000016";
	public static final String DEFAULT_LANGUAGE_SET = 								"em-00000017";
	public static final String RESOURCE_BUNDLE_SET = 								"em-00000018";
	public static final String FAILED_TO_LOAD_GUI_LIST =							"em-00000019";
	public static final String GUI_ITEMS_COUNT = 									"em-00000020";
	public static final String GUI_ITEM_NAME_NULL = 								"em-00000021";
	public static final String GUI_ITEM_FXML_NULL = 								"em-00000022";
	public static final String GUI_ITEM_FOUND =										"em-00000023";
	public static final String FXML_FOUND_FOR_GUI_ITEM = 							"em-00000024";
	public static final String STYLESHEET_FOUND = 									"em-00000025";
	public static final String NO_STYLESHEET_FOUND =								"em-00000026";
	public static final String CONTROLLER_FOUND = 									"em-00000027";
	public static final String NO_CONTROLLER_FOUND = 								"em-00000028";
	public static final String DESCRIPTION_FOUND = 									"em-00000029";
	public static final String NO_DESCRIPTION_FOUND = 								"em-00000030";
	public static final String TITLE_FOUND = 										"em-00000031";
	public static final String NO_TITLE_FOUND = 									"em-00000032";
	public static final String STAGE_STYLE_FOUND = 									"em-00000033";
	public static final String NO_STAGE_STYLE_FOUND = 								"em-00000034";
	public static final String ICON_FOUND = 										"em-00000035";
	public static final String NO_ICON_FOUND = 										"em-00000036";
	public static final String WIDTH_FOUND = 										"em-00000037";
	public static final String WIDTH_NOT_RETREIVE = 								"em-00000038";
	public static final String HEIGHT_FOUND = 										"em-00000039";
	public static final String HEIGHT_NOT_RETREIVE = 								"em-00000040";
	public static final String SCENE_INDICATOR_NOT_RETREIVED = 						"em-00000041";
	public static final String GUI_ITEM_SCENE_INDICATOR_NULL = 						"em-00000042";
	public static final String SCENE_INDICATOR_LOADED_TRUE = 						"em-00000043";
	public static final String SCENE_INDICATOR_LOADED_FALSE = 						"em-00000044";
	public static final String PRIMARY_INDICATOR_LOADED_TRUE = 						"em-00000045";
	public static final String PRIMARY_INDICATOR_LOADED_FALSE = 					"em-00000046";
	public static final String PRIMARY_INDICATOR_NOT_RETREIVED = 					"em-00000047";
	public static final String GUI_ITEM_PRIMARY_NULL = 								"em-00000048";
	public static final String MODALITY_FOUND = 									"em-00000049";
	public static final String NO_MODALITY_FOUND = 									"em-00000050";
	public static final String STARTUP_FOUND = 										"em-00000051";
	public static final String NO_STARTUP_FOUND = 									"em-00000052";
	public static final String GUI_ITEM_LOADED = 									"em-00000053";
	public static final String GUI_LIST_LOADED =									"em-00000054";
	public static final String DB_TYPE_NULL = 										"em-00000055";
	public static final String DB_TYPE_SET = 										"em-00000056";
	public static final String DRIVER_NULL = 										"em-00000057";
	public static final String DRIVER_SET = 										"em-00000058";
	public static final String SCHEMA_NULL = 										"em-00000059";
	public static final String SCHEMA_SET = 										"em-00000060";
	public static final String PORT_NULL = 											"em-00000061";
	public static final String FAILED_TO_RETREIVE_PORT = 							"em-00000062";
	public static final String PORT_SET = 											"em-00000063";
	public static final String SERVER_NULL = 										"em-00000064";
	public static final String SERVER_SET = 										"em-00000065";
	public static final String URL_NULL = 											"em-00000066";
	public static final String URL_SET = 											"em-00000067";
	public static final String DBCONNECTOR_FAILED_ASK = 							"em-00000068";
	public static final String DBCONNECTOR_ESTABLISHED = 							"em-00000069";
	public static final String APP_STARTUP_OK = 									"em-00000070";
	public static final String APP_STARTUP_FAILED = 									"em-00000071";
	public static final String ERROR_DBCONNECTOR_DATASOURCE_FAILS = 				"em-00000072";
	public static final String GUI_ITEM_IS_NOT_CONTAINER = 							"em-00000073";
	public static final String SCENE_CREATED = 										"em-00000074";
	public static final String SCENE_NOT_CREATED = 									"em-00000075";
	public static final String STYLESHEET_LOADED = 									"em-00000076";
	public static final String FAILED_TO_LOAD_STYLESHEET = 							"em-00000077";
	public static final String ICON_LOADED = 										"em-00000078";
	public static final String FAILED_TO_LOAD_ICON = 								"em-00000079";
	public static final String STAGE_LOADED_AND_SHOWED = 							"em-00000080";
	public static final String STAGE_LOADED_ONLY = 									"em-00000081";
	public static final String MODALITY_SET = 										"em-00000082";
	public static final String INITIALIZATION_MODULE_START = 						"em-00000083";
	public static final String INITIALIZATION_MODULE_END = 							"em-00000084";
	static final String PROPERTIES_LOADED_FROM_FILE = 								"em-00000085";
	static final String FAILED_TO_LOAD_PROPERTIES_FROM_FILE = 						"em-00000086";
	static final String FILE_NOT_EXIST_IN_CONF = 									"em-00000087";
	static final String PROPERTIES_LOADED_FROM_RESOURCE = 							"em-00000088";
	static final String FAILED_TO_LOAD_PROPERTIES_FROM_RESOURCE = 					"em-00000089";
	public static final String PROPERTIES_LOADED = 									"em-00000090";
	public static final String PROPERTIES_FAILED = 									"em-00000091";
	public static final String FILE_DELETED = 										"em-00000092";
	public static final String FAILED_DELETE_FILE_UNKNOWN = 						"em-00000093";
	public static final String FAILED_DELETE_FILE_EXISTS = 							"em-00000094";
	public static final String FILE_NOT_CREATED_EXISTS = 							"em-00000095";
	public static final String FILE_CREATED = 										"em-00000096";
	public static final String PROPERTY_LOADED = 									"em-00000097";
	public static final String MODULE_LOGGER_FAILED = "Logger for module %s failed to start. The default logger will be used instead.";

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




	public static final String GUI_ITEM_SCENE_NULL = "Flag scene for GUI cannot hsve a null value.";










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
	public static final String DATABASE_SERVER = "DBConnector.server";
	public static final String DATABASE_USERNAME = "DBConnector.username";
	public static final String DATABASE_PASSWORD = "DBConnector.password";
	public static final String DATABASE_PORT = "DBConnector.port";
	public static final String DATABASE_SCHEMA = "DBConnector.schema";
	public static final String DATABASE_URL_PATTERN = "DBConnector.URLPattern";
	public static final String DATABASE_DRIVER_CLASS_NAME = "DBConnector.driver.className";
	public static final String DATABASE_CHECK_IN_SQL = "database.checkInSQL";
	public static final String DBCONNECTOR_SUPPORTED_DATABASES = "DBConnector.supportedDatabases";
	public static final String DATABASE_OBTAIN_DATASOURCE_METHOD = "database.obtainDataSourceMethod";



	public static final String JDBC_MYSQL_IDENTIFIER_QUOTE = "jdbc.mysql.identifier.quote";
	public static final String DATABASE_RESTRICTED_SEPARATOR = "DBConnector.restricted.separator";
	public static final String SQL_RESTRICTED_SUFFIX = "restricted";
	public static final String SQL_RESTRICTED_DEFAULT = "key,to,desc";
	public static final String JDBC_AUTOCOMMIT = "jdbc.autoCommit";
	public static final String DEFAULT_JDBC_AUTOCOMMIT = "false";

	public static final String JDBC_DEBUG = "jdbc.debug";
	public static final String DEFAULT_JDBC_DEBUG = "false";
	public static final String JDBC_MYSQL_FUNCTION_COUNT = "jdbc.mysql.function.count";
	public static final String DEFAULT_FUNCTION_COUNT = "COUNT";
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
	public static final char AMPERSAND = '&';			//38
	public static final char COMMA = ',';				//44
	public static final char PERIOD = '.';				//46
	public static final char DOT = '.';					//46
	public static final char SLASH = '/';				//47
	public static final char COLON = ':';				//58
	public static final char SEMICOLON = ';';			//59
	public static final char EQUAL_SIGN = '=';			//61
	public static final char QUESTION_MARK = '?';		//63
	public static final char OPENING_BRACKET = '[';		//91
	public static final char BACKSLASH = '\\';			//92
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


	/* dateformats */
	public static final String COMMON_DATE_FORMAT = "dd.MM.yyyy 'at' HH:MM:ss";
	public static final String DEFAULT_GUI_RESOURCE = RESOURCE_PATH + Const.SLASH + RELATIVE_XML_RESOURCE_PATH + Const.SLASH + GUI_LIST_FILENAME;
	public static final String GUI_DEFAULT_LANGUAGE = "en";
	public static final String RESOURCE_BUNDLE_GET_BUNDLE_METHOD_NAME = "getBundle";
	public static final String DEFAULT_COUNTRY = "US";


	public static final String USER_MANAGEMENT_GUI_KEY = "GUIManager.userManagement.gui";
	public static final String USER_MANAGEMENT_GUI_DEFAAULT = "user-mgtm";
	public static final Object RESOURCE_BUNDLE_ERROR = "ERROR";
	public static final Object RESOURCE_BUNDLE_NO_SOURCE = "NO SOURCE";
	public static final String DBCONNECTOR_LOGIN_GUI = "DBConnector.gui.login";
	public static final String DEFAULTT_DBCONNECTOR_LOGIN_GUI = "dbconnector-login";
















	public static final String DEFAULT_DBCONNECTOR_LOGIN_GUI = null;
	public static final CharSequence MySQL_DRIVER_IDENTIFIER = "mysql";
	public static final String MYSQL_STRING_REPRESENTATION = "MySQL";
	public static final CharSequence MAXDB_DRIVER_IDENTIFIER = "sap";
	public static final String MAXDB_STRING_REPRESENTATION = "Max DB";
	public static final CharSequence MICROSOFT_DRIVER_IDENTIFIER = "microsoft";
	public static final String MICROSOFT_STRING_REPRESENTATION = "Microsoft SQL";
	public static final String DEFAULT_SEPARATOR = new String (new char[]{Const.COMMA});
	public static final String DATABASE_STRING_PRESENTATION_SUFFIX = "stringPresentation";
	public static final String DOT_REGEXP_STRING = new String(new char[]{Const.BACKSLASH, Const.PERIOD});
	public static final String DATABASE_DRIVER_CLASSNAME_SUFFIX = "driver";
	public static final String DATABASE_AUTOCOMMIT_SUFFIX = "autocommit";
	public static final String SQL_FUNCTION_COUNT_SUFFIX = "countFunction";
	public static final String SQL_IDENTIFIER_QUOTE_SUFFIX = "identifierQuote";
	public static final String DEFAULT_PORT_SUFFIX = "defaultPort";
	public static final String DEFAULT_SERVER_SUFFIX = "defaultServer";
	public static final String SAMPLE_URL_SUFFIX = "sampleUrl";
	public static final Object HIDDEN_PASSWORD = "********";
	public static final String GET_OTHER_JDBC_SETTINGS_STRING = "getOtherJDBCSettingsStringFor%s";
	public static final Object USERNAME = "someusername";
	public static final String JDBC_STRING = "jdbc";
	public static final String ERROR_WITH_EXCEPTION_GUI = "error-with-exception";
	public static final String BASIC_RESOURCE_BUNDLE_BASE_NAME = "basic-rb-data";
	public static final String INITIAL_LANGUAGE = "sk_SK";
	public static final String NAME_OF_TEMPORATY_LOGGER = "tmp-log";
	private static final String TMP_LOG_FILE_FOLDER = "log";
	private static final String TMP_LOG_FILENAME = "temp.log";
	public static final String TEMPORARY_LOGGER_OUTPUT_FILE = TMP_LOG_FILE_FOLDER + FS + TMP_LOG_FILENAME;
	public static final String TEMPORARY_LOG_PATTERN = "%date %level [%thread] %logger{10} [%file:%line] %msg%n";
	public static final String UTF_8_ENCODING = "UTF-8";
	public static final int UTF_8_BYTE_1_MASK = 0x80;
	public static final int UTF_8_BYTE_2_MASK = 0xE0;
	public static final int UTF_8_BYTE_2_INDICATOR = 0xC0;
	public static final int UTF_8_BYTE_3_MASK = 0xF0;
	public static final int UTF_8_BYTE_3_INDICATOR = 0xE0;
	public static final int UTF_8_BYTE_4_MASK = 0xF8;
	public static final int UTF_8_BYTE_4_INDICATOR = 0xF0;
	public static final int UTF_8_BYTE_X_MASK = 0xC0;
	public static final int UTF_8_BYTE_X_INDICATOR = 0x80;
	public static final String HIDE_EXCEPTION_DETAILS_TEXT = "ew-00000003";
	public static final String SHOW_EXCEPTION_DETAILS_TEXT = "ew-00000002";
	public static final String DB_LOGIN_ABORTED = "mb-00000001";
	public static final String MESSAGE_BOX_GUI = "EventManager.messageBox.gui";
	public static final String DEFAULT_MESSAGE_BOX_GUI = "message-box";
	public static final String DEFAULT_MANAGER_MODULE_NAME = "manager";
	public static final String DEFAULT_SHUTDOWN_HOOK_NAME = "ShutdownHook";
	public static final String SHUTDOWN_HOOK_NAME = "EventManager.shutdownHook";
	public static final String SHUTDOWN_HOOK_START = "sh-00000001";
	public static final String SHUTDOWN_HOOK_END = "sh-00000002";
	public static final String GRACEFULL_SHUTDOWN_START = "sh-00000003";
	public static final String STARTUP_PROPERTIES_SAVED = "sh-00000004";
	public static final String STARTUP_PROPERTIES_FAILED_TO_SAVED = "sh-00000005";





















}
