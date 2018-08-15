package org.pabk.application.emanager.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.pabk.application.EManager;
import org.pabk.application.emanager.db.Row;
import org.pabk.application.emanager.db.Rows;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.EManagerResourceBundle;
import org.pabk.application.emanager.util.GUIItem;
import org.pabk.application.emanager.util.Sleeper;
import org.pabk.application.emanager.util.Sys;
import org.pabk.application.emanager.util.Test;
import org.pabk.emanager.sql.sap.Delete;
import org.pabk.emanager.sql.sap.Identifier;
import org.pabk.emanager.sql.sap.Insert;
import org.pabk.emanager.sql.sap.Query;
import org.pabk.emanager.sql.sap.QueryExp;
import org.pabk.emanager.sql.sap.QueryPrimary;
import org.pabk.emanager.sql.sap.QuerySpec;
import org.pabk.emanager.sql.sap.QueryTerm;
import org.pabk.emanager.sql.sap.SQLSyntaxImpl;
import org.pabk.emanager.sql.sap.SchemaName;
import org.pabk.emanager.sql.sap.Select;
import org.pabk.emanager.sql.sap.SelectColumn;
import org.pabk.emanager.sql.sap.TableExp;
import org.pabk.emanager.sql.sap.TableName;
import org.pabk.emanager.sql.sap.Update;
import org.pabk.emanager.sql.sap.WhereClause;
import org.pabk.util.Huffman;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnector extends ModuleImpl {

	private static DataSource dataSource;
	private static SchemaName schema;
	private static int port;
	private static String driverClassName;
	private static String urlPattern;
	private static String server;
	private static String checkInSQL;

	private static boolean autoCommit;
	private static boolean loaded = false;

	public static TableName tn_conds;
	public static TableName tn_citems;
	public static TableName tn_emsg_body;
	public static TableName tn_emsg_recs;
	public static TableName tn_emsg_subj;
	public static TableName tn_emsg;
	public static TableName tn_recipients;
	public static TableName tn_rgroups;
	public static TableName tn_trgroups;
	public static TableName tn_fin_sources;
	public static TableName tn_fin_msgs;
	public static TableName tn_email_msgs;
	public static TableName tn_emsg_ata;
	public static TableName tn_emsg_ats;
	public static TableName tn_emsg_line;
	public static TableName tn_fin_print_parser;
	public static TableName tn_fin_rje_parser;
	public static TableName tn_logging_event;
	public static TableName tn_logging_event_exception;
	public static TableName tn_logging_event_property;
	public static TableName tn_properties;
	public static TableName tn_props;
	public static TableName tn_syntax;
	public static TableName tn_dictionary_en;

	public static String col_id;
	public static String col_name;
	public static String col_priority;
	public static String col_ci;
	public static String col_regexp;
	public static String col_rg;
	public static String col_at;
	public static String col_text;
	public static String col_condition;
	public static String col_type;
	public static String col_tg;
	public static String col_class;
	public static String col_recs_id;
	public static String col_subj_id;
	public static String col_body_id;
	public static String col_surname;
	public static String col_email;
	public static String col_desc;
	public static String col_enabled;
	public static String col_main;
	public static String col_u;
	public static String col_format;
	public static String col_source;
	public static String col_session;
	public static String col_fin;
	public static String col_source_id;
	public static String col_serial_id;
	public static String col_swift;
	public static String col_status;
	public static String col_creation_mp;
	public static String col_creation_time;
	public static String col_mir;
	public static String col_reference;
	public static String col_service_code;
	public static String col_cond_id;
	public static String col_to;
	public static String col_cc;
	public static String col_subject;
	public static String col_ready_to_send;
	public static String col_msg_a_id;
	public static String col_msg_b_id;
	public static String col_bcc;
	public static String col_cor;
	public static String col_insert_time;
	public static String col_first_time;
	public static String col_last_time;
	public static String col_counter;
	public static String col_last_sender_id;
	public static String col_sender;
	public static String col_receiver;
	public static String col_cir;
	public static String col_mur;
	public static String col_mor;
	public static String col_uds;
	public static String col_msg_status;
	public static String col_pris;
	public static String col_timestmp;
	public static String col_formatted_message;
	public static String col_logger_name;
	public static String col_level_string;
	public static String col_thread_name;
	public static String col_reference_flag;
	public static String col_arg;
	public static String col_caller_filename;
	public static String col_caller_class;
	public static String col_caller_method;
	public static String col_caller_line;
	public static String col_event_id;
	public static String col_i;
	public static String col_trace_line;
	public static String col_mapped_key;
	public static String col_mapped_value;
	public static String col_key;
	public static String col_value;
	private static boolean debug;
	private static String countFunction;
	private static final String TABLE_PREFIX = "tn_";
	private static final String COLUMN_PREFIX = "col_";
	private static final String DIGIT_MASK = "\\d+";
	//private static final long WAIT_INTERVAL = 1000L;


	@Override
	public void initialize() {
		state ^= Const.INITIALIZATION_FLAG;
		this.startTime = new Date().getTime();
		Sys.infof(this, Const.INITIALIZATION_MODULE_START, this.getIdm(), new SimpleDateFormat(Const.COMMON_DATE_FORMAT).format(this.startTime));
		connectToDB();
		commonInitialize();
		state ^= Const.INITIALIZATION_FLAG;
		state ^= Const.INITIALIZED_FLAG;
		Sys.infof(this, Const.INITIALIZATION_MODULE_END, this.getIdm(), new SimpleDateFormat(Const.COMMON_DATE_FORMAT).format(new Date()));
	}


	private void connectToDB() {
		String dbLoginGui = Sys.getProperty(DBConnector.class, Const.DBCONNECTOR_LOGIN_GUI, Const.DEFAULT_DBCONNECTOR_LOGIN_GUI);
		String msgBoxGui = Sys.getProperty(DBConnector.class, Const.MESSAGE_BOX_GUI, Const.DEFAULT_MESSAGE_BOX_GUI);
		this.sleeper = new Sleeper();
		//GUIItem item = GUIManager.getGUI(dbLoginGui);

		//Test test = new Test(item);
		//test.start();
		//sleeper.sleep(10000);

		//item.setVisible(false);
		while(DBConnector.getDataSource() == null) {
			GUIManager.operationAborted(true);
			GUIManager.loadStage(this, null, dbLoginGui, true);
			/*while(!item.isVisible()) {
				sleeper.sleep(WAIT_INTERVAL);
				System.err.println("Item is not visible");
			}*/
			/*
			while(item.isVisible()) {
				System.out.println("Item is visible");
				sleeper.sleep(WAIT_INTERVAL);
			}*/
			if(GUIManager.operationAborted(true)) {
				EManagerResourceBundle erb = (EManagerResourceBundle) GUIManager.getResourceBundle();
				erb.setMessageBoxMessage(Sys.format(Const.DB_LOGIN_ABORTED, EManager.class.getSimpleName()));
				GUIManager.loadStage(DBConnector.class, null, msgBoxGui, true);
				break;
			}
			try {
				DBConnector.createDataSource(DBConnector.class, EManager.getStartUpProperties());
			}
			catch(Exception e) {
				((EManagerResourceBundle) GUIManager.getResourceBundle()).setError(e);

				//GUIItem errorMessage = GUIManager.getGUI(Const.ERROR_WITH_EXCEPTION_GUI);
				((EManagerResourceBundle) GUIManager.getResourceBundle()).setError(e);
				//((EManagerResourceBundle) GUIManager.getResourceBundle()).getError();
				//System.out.println(((EManagerResourceBundle) GUIManager.getResourceBundle()).getError());
				GUIManager.loadStage(EManager.class, null, Const.ERROR_WITH_EXCEPTION_GUI, true);
			}
			if(DBConnector.getDataSource() == null) {
				Sys.errorf(EManager.class, Const.DBCONNECTOR_FAILED_ASK);
			}
			else {
				Sys.infof(EManager.class, Const.DBCONNECTOR_ESTABLISHED);
				EManagerResourceBundle erb = (EManagerResourceBundle) GUIManager.getResourceBundle();
				erb.setMessageBoxMessage(Sys.format(Const.DBCONNECTOR_ESTABLISHED, EManager.class.getSimpleName()));
				GUIManager.loadStage(EManager.class, null, msgBoxGui, true);

				DBConnector.init();
			}
		}
		GUIManager.loadStage(EManager.class, null, EManager.getPrimaryStage(), true);

	}



















	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		DBConnector.dataSource = dataSource;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		DBConnector.port = port;
	}

	public static String getUrlPattern() {
		return urlPattern;
	}

	public static void setUrlPattern(String urlPattern) {
		DBConnector.urlPattern = urlPattern;
	}

	public static String getServer() {
		return server;
	}

	public static void setServer(String server) {
		DBConnector.server = server;
	}

	public static void setPassword(String password) {
	}


	public static boolean init() {
		if(DBConnector.isLoaded()) {
			return DBConnector.isLoaded();
		}


		//String stringPresentstion = Sys.getProperty(DBConnector.class, prefix + Const.DATABASE_AUTOCOMMIT_SUFFIX, Const.DEFAULT_JDBC_AUTOCOMMIT);






		try {
			executeBatch (DBConnector.class, DBConnector.getConnection(DBConnector.class), Sys.getProperty(DBConnector.class, Const.DB_BATCH_CREATE_DB, Const.DEFAULT_DB_BATCH_CREATE_DB), Sys.getProperty(DBConnector.class, Const.DB_BATCH_ENCODING, Const.DEFAULT_DB_BATCH_ENCODING));
		} catch (IOException | SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		/* TODO only if you want to initialize log tables. All old logs are deleted!!!!!*/
		/*try {
			executeBatch (this, DBConnector.getConnection(this), Sys.getProperty(this.getProperties(), Const.DB_BATCH_CREATE_LOG_TABLES, Const.DEFAULT_DB_BATCH_CREATE_LOG_TABLES), Sys.getProperty(this.getProperties(), Const.DB_BATCH_ENCODING, Const.DEFAULT_DB_BATCH_ENCODING));
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			DBConnector.loadColumnValues (DBConnector.class, schema.toSQLString(null), DBConnector.loadTableValues (DBConnector.class, schema.toSQLString(null)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		DBConnector.setLoaded(true);
		return DBConnector.isLoaded();

	}



	private static String[] loadTableValues(Object caller, String schema) throws Exception {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Rows rows = getRows (rs = (stat = (con = DBConnector.getConnection(caller)).createStatement()).executeQuery((String.format(Sys.getProperty(DBConnector.class, Const.DB_SQL_SHOW_TABLES, Const.DEFAULT_DB_SQL_SHOW_TABLES), schema))));
			String[] tables = new String[rows.size()];
			String col = String.format(Sys.getProperty(DBConnector.class, Const.DB_SQL_SHOW_TABLES_COL, Const.DEFAULT_DB_SQL_SHOW_TABLES_COL), schema);
			for(int i = 0; i < rows.size(); i ++) {
				String table = (String) rows.get(i).get(col);
				Field field = null;
				try {
					field = DBConnector.class.getDeclaredField(TABLE_PREFIX + table);
					field.set(null, new TableName (DBConnector.getSchema(), new Identifier(table)));
					tables[i] = table;
				}
				catch (Exception e) {
					//Sys.log(null, null, Const.WARN, e.getMessage());
					Sys.warnn(caller, e);
				}
			}
			return tables;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(stat != null) try {stat.close();} catch (SQLException e) {}
			if(con != null) try {con.close();} catch (SQLException e) {}
		}
	}

	private static void loadColumnValues(Object caller, String schema, String[] tables) throws Exception {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			con = DBConnector.getConnection(caller);
			String col = String.format(Sys.getProperty(DBConnector.class, Const.DB_SQL_SHOW_COLUMNS_COL,  Const.DEFAULT_DB_SQL_SHOW_COLUMNS_COL), schema);
			for(int i = 0; i < tables.length; i ++) {
				if(tables[i] != null) {
					Rows rows = getRows (rs = (stat = con.createStatement()).executeQuery((String.format(Sys.getProperty(DBConnector.class, Const.DB_SQL_SHOW_COLUMNS, Const.DEFAULT_DB_SQL_SHOW_COLUMNS), schema, tables[i]))));
					for(int j = 0; j < rows.size(); j ++) {
						String cName = (String) rows.get(j).get(col);
						Field field = null;
						try {
							field = DBConnector.class.getDeclaredField(COLUMN_PREFIX + cName.replaceAll(DIGIT_MASK, Const.EMPTY));
							if(field.get(null) == null) {
								field.set(null, cName.replaceAll(DIGIT_MASK, Const.EMPTY));
							}
						}
						catch (Exception e) {
							//Sys.log(null, null, Const.WARN, e.getMessage());
							Sys.warnn(caller, e.getMessage());
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(stat != null) try {stat.close();} catch (SQLException e) {}
			if(con != null) try {con.close();} catch (SQLException e) {}
		}
	}

	public static void createDataSource(Object caller, Properties properties) throws Exception {
		try {
			String username = Sys.getProperty(DBConnector.class, Const.DATABASE_USERNAME, null);
			try {
				if(username != null) {
					username = Huffman.decode(username, null);
				}
			}
			catch (Exception e) {
				Sys.warnf(caller, Const.USERNAME_DECODE_FAIL);
				throw new UnsupportedOperationException(Sys.format(Const.USERNAME_DECODE_FAIL));
			}



			if(username == null || username.length() == 0) {
				Sys.warnf(caller, Const.USERNAME_NULL);
				throw new NullPointerException (Sys.format(Const.USERNAME_NULL));
			}
			else {
				Sys.infof(caller, Const.USERNAME_SET);
			}


			String password = Sys.getProperty(DBConnector.class, Const.DATABASE_PASSWORD, null);
			try {
				if(password != null) {
					password = Huffman.decode(password, null);
				}
			}
			catch (Exception e) {
				Sys.warnf(caller, Const.PASSWORD_DECODE_FAIL);
				throw new UnsupportedOperationException(Sys.format(Const.PASSWORD_DECODE_FAIL));
			}
			if(password == null || password.length() == 0) {
				Sys.warnf(caller, Const.PASSWORD_NULL);
				throw new NullPointerException (Sys.format(Const.PASSWORD_NULL));
			}
			else {
				Sys.infof(caller, Const.PASSWORD_SET);
			}
			String prefix = null;
			String className = Sys.getProperty(DBConnector.class, Const.DATABASE_DRIVER_CLASS_NAME, null);
			String type = Sys.getDatabaseTypeFromClass(DBConnector.class, className);
			Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, type);
			String url = null;
			if(type == null) {
				Sys.warnf(caller, Const.DB_TYPE_NULL);
				throw new NullPointerException (Sys.format(Const.DB_TYPE_NULL));
			}
			else {
				prefix = DBConnector.class.getSimpleName() + Const.DOT + Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, type) + Const.DOT;
				//String stringPresentstion = Sys.getProperty(caller, prefix + Const.DATABASE_STRING_PRESENTATION_SUFFIX, null);
				Sys.infof(DBConnector.class, Const.DB_TYPE_SET, type);
				className = Sys.getProperty(caller, prefix + Const.DATABASE_DRIVER_CLASSNAME_SUFFIX, null);
				url = Sys.getProperty(caller, prefix + Const.SAMPLE_URL_SUFFIX, null);
			}
			if(className == null || className.length() == 0) {
				Sys.warnf(caller, Const.DRIVER_NULL);
				throw new NullPointerException (Sys.format(Const.DRIVER_NULL));
			}
			else {
				Sys.infof(caller, Const.DRIVER_SET, className);
			}
			String server = Sys.getProperty(DBConnector.class, Const.DATABASE_SERVER, null);
			if(server == null || server.length() == 0) {
				Sys.warnf(caller, Const.SERVER_NULL);
				throw new NullPointerException (Sys.format(Const.SERVER_NULL));
			}
			else {
				Sys.infof(caller, Const.SERVER_SET, server);
			}

			String port = Sys.getProperty(DBConnector.class, Const.DATABASE_PORT, null);
			int intPort = 0;
			if(port == null || port.length() == 0) {
				Sys.warnf(caller, Const.PORT_NULL);
				throw new NullPointerException (Sys.format(Const.PORT_NULL));
			}
			else {
				try {
					intPort = Integer.parseInt(port);
					Sys.infof(caller, Const.PORT_SET, port);
				}
				catch(Exception e) {
					Sys.warnf(DBConnector.class, Const.FAILED_TO_RETREIVE_PORT, port);
					throw new NullPointerException (Sys.format(Const.FAILED_TO_RETREIVE_PORT, port));
				}

			}

			String schema = Sys.getProperty(DBConnector.class, Const.DATABASE_SCHEMA, null);
			if(schema == null || schema.length() == 0) {
				Sys.warnf(caller, Const.SCHEMA_NULL);
				throw new NullPointerException (Sys.format(Const.SCHEMA_NULL));
			}
			else {
				Sys.infof(caller, Const.SCHEMA_SET, schema);
			}




			if(url == null || url.length() == 0) {
				Sys.warnf(caller, Const.URL_NULL);
				throw new NullPointerException (Sys.format(Const.URL_NULL));
			}
			else {
				Sys.infof(caller, Const.URL_SET, url);
			}

			DBConnector.setSchema(new SchemaName(new Identifier (schema)));
			//String className = Sys.getProperty(DBConnector.class, Const.DATABASE_DRIVER_CLASS_NAME, null);
			//String type = Sys.getDatabaseTypeFromClass(DBConnector.class, className);

			//String prefix = DBConnector.class.getSimpleName() + Const.DOT + Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, Sys.getDatabaseTypeFromClass(DBConnector.class, className))) + Const.DOT;

			DBConnector.autoCommit = Boolean.parseBoolean(Sys.getProperty(DBConnector.class, prefix + Const.DATABASE_AUTOCOMMIT_SUFFIX, Const.DEFAULT_JDBC_AUTOCOMMIT));
			DBConnector.setCountFunction(Sys.getProperty(DBConnector.class, prefix + Const.SQL_FUNCTION_COUNT_SUFFIX, Const.DEFAULT_FUNCTION_COUNT));
			Identifier.setQuoteCharacter(Sys.getProperty(DBConnector.class, prefix + Const.SQL_IDENTIFIER_QUOTE_SUFFIX, new String(new char[]{Const.GRAVE_ACCENT})).charAt(0));
			Identifier.setRestrictedWords(Sys.getProperty(DBConnector.class, prefix + Const.SQL_RESTRICTED_SUFFIX, Const.SQL_RESTRICTED_DEFAULT).split(Sys.getProperty(DBConnector.class, Const.DATABASE_RESTRICTED_SEPARATOR, new String(new char[]{Const.COMMA}))));
			DBConnector.setDebug(Boolean.parseBoolean(Sys.getProperty(DBConnector.class, Const.JDBC_DEBUG, Const.DEFAULT_JDBC_DEBUG)));

			//DBConnector.setUsername(username != null ? : Const.EMPTY);
			//DBConnector.setPassword(password != null ? Huffman.decode(username, null): Const.EMPTY););
			//DBConnector.setPort(Integer.parseInt(port));
			//DBConnector.setServer(server);
			//DBConnector.setUrlPattern(url);
			//DBConnector.setDriverClassName(className);
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass(DBConnector.getDriverClassName());
			String other = Sys.getOtherJDBCSettingsString(Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, Sys.getDatabaseTypeFromClass(DBConnector.class, className)));
			url = String.format(url, server, intPort, schema ,username, password, other);
			cpds.setJdbcUrl(url);
			cpds.setUser(username);
			cpds.setPassword(password);
			DBConnector.setDataSource(cpds);
			DBConnector.checkInSQL = Sys.getPropertyNoLog(DBConnector.class, Const.DATABASE_CHECK_IN_SQL, Const.DEFAULT_DATABASE_CHECK_IN_SQL);
			Connection conn = null;
			Statement stmt = null;
			try {
				conn = DBConnector.getDataSource().getConnection();
				stmt = conn.createStatement();
				stmt.execute(DBConnector.checkInSQL);
			} catch (SQLException e) {
				DBConnector.setDataSource(null);
				throw e;
				//e.printStackTrace();
			}
			finally {
				if(stmt != null) try {stmt.close();} catch (SQLException e) {}
				if(conn != null) try {conn.close();} catch (SQLException e) {}
			}
		}
		catch(Exception e) {

			DBConnector.setDataSource(null);
			//e.printStackTrace();
			throw (e);
		}
	}

	public static SchemaName getSchema() {
		return schema;
	}

	public static void setSchema(SchemaName schema) throws SQLException {
		System.out.println(schema.toSQLString(null));
		DBConnector.schema = schema;
	}

	public static String getDriverClassName() {
		return driverClassName;
	}

	public static void setDriverClassName(String driverClassName) {
		DBConnector.driverClassName = driverClassName;
	}

	public static Connection getConnection(Object caller) throws SQLException {
		try {
			Connection con = DBConnector.getDataSource().getConnection();
			con.setAutoCommit(autoCommit);
			return con;
		}
		catch(Exception e) {
			Sys.errorn(caller, Const.ERROR_DBCONNECTOR_DATASOURCE_FAILS);
			throw new SQLException (Sys.format(Const.ERROR_DBCONNECTOR_DATASOURCE_FAILS));
		}
	}
	public static Rows select(Object caller, Connection con, Query select) throws SQLException {
		String sql = select.toSQLString(null);
		 System.err.println(sql);
		Sys.debugn(caller, sql);
		PreparedStatement ps = con.prepareStatement(sql);
		if(SQLSyntaxImpl.isPrepared()) {
			select.getPreparedBuffer().setAll(ps);
		}
		ResultSet rs = ps.executeQuery();
		Rows rows = getRows(rs);
		/*Rows rows = new Rows();
		Row row;
		while((row = nextRow(rs)) != null) {
			rows.add(row);
		};*/
		ps.close();
		rs.close();
		return rows;
	}
	private static Rows getRows(ResultSet rs) throws SQLException {
		Rows rows = new Rows();
		Row row;
		while((row = nextRow(rs)) != null) {
			rows.add(row);
		};
		return rows;
	}
	private static Row nextRow (ResultSet rs) throws SQLException {
		if(next(rs)) {
			Row row = new Row();
			ResultSetMetaData rsmd = rs.getMetaData();
			int j = rsmd.getColumnCount();
			String[] cols = new String[j];
			for(int i = 0; i < j; i ++) {
				cols[i] = rsmd.getColumnLabel(i + 1);
			}
			for(int i = 0; i < cols.length; i ++) {
				row.put(cols[i], rs.getObject(cols[i]));
			}
			return row;
		}
		else {
			return null;
		}
	}
		private static boolean next(ResultSet rs) {
			boolean status = false;
			if(rs != null) {
				try {
					status  = rs.next();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				if(!status) {
					close(rs);
				}
			}
			return status;
		}

		private static final void close(ResultSet rs) {
			try {
				rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				rs = null;
			}
		}
		public static Query createSelect() throws SQLException {
			Query q = new Query(new Select(new QueryExp(new QueryTerm(new QueryPrimary(new QuerySpec(new SelectColumn(), new TableExp()))))));
			return q;
		}

		public static Object getDebug() {
			return debug;
		}

		public static void setDebug(boolean debug) {
			DBConnector.debug = debug;
		}

		public static String getCountFunction() {
			return countFunction;
		}

		public static void setCountFunction(String countFunction) {
			DBConnector.countFunction = countFunction;
		}
		private static synchronized void executeBatch (Object caller, Connection con, String batch, String charset) throws IOException, SQLException {
			InputStream stream = null;
	    	Statement statement = null;
	    	try {
	    		stream = DBConnector.class.getResourceAsStream(batch);
	    		statement = con.createStatement();

	    		BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
	    		if(loadBatch(caller, reader, statement)) {
	    			statement.executeBatch();
	    			con.commit();
	    		}
	    	}
	    	catch (Exception e) {
	    		if(con != null) {
	    			con.rollback();
	    		}
	    		throw new IOException (e);
	    	}
			finally {
				if(stream != null) try {stream.close();} catch (Exception e){};
				if(statement != null) try {statement.close();} catch (Exception e){};
				if(con != null) try {con.close();} catch (Exception e){};
			}
		}
		private static boolean loadBatch (Object caller, BufferedReader reader, Statement statement) throws IOException, SQLException {
			boolean notNull = false;
			String query = null;
			while ((query = loadLine(reader)) != null) {
				//if(debug) System.out.println(query);
				Sys.debugn(caller, query);
				statement.addBatch(query);
				notNull = true;
			}
			return notNull;
		}
		private static String loadLine (BufferedReader reader) throws IOException {
			StringBuffer sb = new StringBuffer();
			while (true) {
				String line = reader.readLine();
				if(line != null) {
					line = line.trim();
					if(line.length() == 0) {
						continue;
					}
					else {
						if(line.charAt(line.length() - 1) != Const.SEMICOLON) {
							sb.append(Const.SPACE);
	    					sb.append(line);
	    					continue;
						}
						else {
							line = line.substring(0, line.length() - 1);
							sb.append(Const.SPACE);
							sb.append(line);
							break;
						}
					}
				}
				if(line == null) {
					break;
				}
			}
			String query = sb.toString().trim();
			return query.length() == 0 ? null : query;
		}
		public static int update(ModuleImpl caller, Connection con, Update update) throws SQLException {
			return DBConnector.update(caller, con, update, true);
		}
		public static int update(ModuleImpl caller, Connection con, Update update, boolean commit) throws SQLException {
			String sql = update.toSQLString(null);
			//if(debug) System.out.println(sql);
			Sys.debugn(caller, sql);
			PreparedStatement ps = con.prepareStatement(sql);
			if(SQLSyntaxImpl.isPrepared()) {
				update.getPreparedBuffer().setAll(ps);
			}
			int status = ps.executeUpdate();
			if(commit) {
				con.commit();
			}
			ps.close();
			return status;
		}
		public static Update createUpdate(TableName tableName, String[] cols, Object[] values, WhereClause whereClause) throws SQLException {
			Update update = new Update(tableName, values, cols);
			update.setWhereClause(whereClause);
			return update;
		}
		public static Delete createDelete(TableName tableName, String referenceName, WhereClause where) throws SQLException {
			Delete delete;
			if(referenceName == null) {
				delete = new Delete(tableName, where);
			}
			else {
				delete = new Delete(tableName, new Identifier(referenceName), where);
			}
			return delete;
		}
		public static int delete(Object caller, Connection con, Delete delete) throws SQLException {
			return delete(caller, con, delete, true);
		}

		public static int delete(Object caller, Connection con, Delete delete, boolean commit) throws SQLException {
			String sql = delete.toSQLString(null);
			Sys.debugn(caller, sql);
			//if(debug) System.out.println(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			if(SQLSyntaxImpl.isPrepared()) {
				delete.getPreparedBuffer().setAll(ps);
			}
			int status = ps.executeUpdate();
			if(commit) {
				con.commit();
			}
			ps.close();
			return status;
		}
		public static Insert createInsert(TableName tableName, Object values, String ...strings) throws SQLException {
			Insert insert = new Insert(tableName, values, strings);
			return insert;
		}
		public static int insert (Object caller, Connection con, Insert insert) throws SQLException {
			return DBConnector.insert(caller, con, insert, true);
		}

		public static int insert (Object caller, Connection con, Insert insert, boolean commit) throws SQLException {
			String sql = insert.toSQLString(null);
			//if(debug) System.out.println(sql);
			Sys.debugn(caller, sql);
			PreparedStatement ps = con.prepareStatement(sql);
			if(SQLSyntaxImpl.isPrepared()) {
				insert.getPreparedBuffer().setAll(ps);
			}
			int status = ps.executeUpdate();
			if(commit) {
				con.commit();
			}
			ps.close();
			return status;
		}

		/**
		 * @return the initialized
		 */
		public static boolean isLoaded() {
			return loaded;
		}

		/**
		 * @param initialized the initialized to set
		 */
		public static void setLoaded(boolean loaded) {
			DBConnector.loaded = loaded;
		}

}

