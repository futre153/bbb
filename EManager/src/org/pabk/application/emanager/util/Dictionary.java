package org.pabk.application.emanager.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.sql.DataSource;

import org.pabk.emanager.sql.sap.TableName;

public class Dictionary extends DBTable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Hashtable <String, String> dic = new Hashtable<String, String>();

	private Method obtainDatasource;

	public Dictionary(DataSource ds, TableName table) throws SQLException {
		super(ds, table);
	}

	public Dictionary() {
		super();
	}

	public Dictionary(Method method, TableName table) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		super((DataSource) method.invoke(null, new Object[]{}), table);
		this.obtainDatasource = method;
	}

	public String getProperty (String key, String defaultValue) {
		try {this.setDataSource((DataSource) obtainDatasource.invoke(null, new Object[]{}));} catch (Exception e) {}
		if(key != null && this != null) {
			String value = this.dic.get(key);
			if(value == null) {
				try {value = super.getProperty(key, defaultValue);} catch(NullPointerException e) {value = null;}
				if(value != null && !value.equals(defaultValue)) {
					dic.put(key, value);
				}
			}
			return value;
		}
		throw new NullPointerException();
	}

	public String getProperty(String key) {
		try {this.setDataSource((DataSource) obtainDatasource.invoke(null, new Object[]{}));} catch (Exception e) {}
		if(key != null) {
			String value = this.dic.get(key);
			if(value == null) {
				//value = super.getProperty(key);
				try {value = super.getProperty(key);} catch(NullPointerException e) {value = null;}
				if(value != null) {
					dic.put(key, value);
				}
			}
			return value;
		}
		return this.getProperty(key, null);
	}

	public String setProperty (String key, String value) {
		try {this.setDataSource((DataSource) obtainDatasource.invoke(null, new Object[]{}));} catch (Exception e) {}
		if(key != null || value != null) {
			this.dic.put(key, value);
			return (String) super.setProperty(key, value);
		}
		throw new NullPointerException();
	}

}
