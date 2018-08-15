package org.pabk.application.emanager.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.DBConnector;
import org.pabk.emanager.sql.sap.Identifier;
import org.pabk.emanager.sql.sap.SchemaName;
import org.pabk.emanager.sql.sap.TableName;

public class EManagerResourceBundle extends ResourceBundle {
	private Properties properties;
	private Dictionary dictionary;
	private Throwable error;
	private String messageBoxMessage;

	public EManagerResourceBundle(BufferedInputStream stream, String bundleName) throws InvalidPropertiesFormatException, IOException {
		properties = new Properties();
		properties.loadFromXML(stream);
		try {
			String schema  = Sys.getProperty(DBConnector.class, Const.DATABASE_SCHEMA, Const.DEFAULT_DATABASE_SCHEMA);
			String obtainDatasource = Sys.getProperty(DBConnector.class, Const.DATABASE_OBTAIN_DATASOURCE_METHOD, Const.DEFAULT_DATABASE_OBTAIN_DATASOURCE_METHOD);
			dictionary = new Dictionary(DBConnector.class.getDeclaredMethod(obtainDatasource, new Class<?>[]{}), new TableName( new SchemaName(new Identifier(schema)), new Identifier(bundleName)));
			if(DBConnector.getDataSource() != null) {
				dictionary.setDataSource(DBConnector.getDataSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
			dictionary = null;
		}
	}

    @Override
	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object handleGetObject(String key) {
		try {
			Object value = dictionary.getProperty(key);
				 value = value == null ? properties.getProperty(key) : value;
				 if(value == null) {
					 /* TODO  remove suffix */
					 return Const.RESOURCE_BUNDLE_NO_SOURCE + "(" + key + ")";
				 }
				 return value;
		}
		catch (Exception e) {
			return Const.RESOURCE_BUNDLE_ERROR;
		}

	}

	/**
	 * @return the error
	 */
	public Throwable getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Throwable error) {
		this.error = error;
	}

	/**
	 * @return the messageBoxMessage
	 */
	public String getMessageBoxMessage() {
		return messageBoxMessage;
	}

	/**
	 * @param messageBoxMessage the messageBoxMessage to set
	 */
	public void setMessageBoxMessage(String messageBoxMessage) {
		this.messageBoxMessage = messageBoxMessage;
	}


}
