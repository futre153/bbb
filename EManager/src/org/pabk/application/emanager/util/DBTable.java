package org.pabk.application.emanager.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.sql.DataSource;

import org.pabk.application.emanager.db.Rows;
import org.pabk.application.emanager.module.DBConnector;
import org.pabk.emanager.sql.sap.CompPred;
import org.pabk.emanager.sql.sap.Identifier;
import org.pabk.emanager.sql.sap.Query;
import org.pabk.emanager.sql.sap.SchemaName;
import org.pabk.emanager.sql.sap.TableName;
import org.pabk.emanager.sql.sap.WhereClause;

public class DBTable extends Properties {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String COUNT_FUNCTION = "COUNT";
	private static final String DEFAULT_KEY_COLUMN = "key";
	private static final String DEFAULT_VALUE_COLUMN = "value";
	private static final String create = "CREATE TABLE IF NOT EXISTS %1$s (`%2$s` VARCHAR(256) NOT NULL, `%3$s` TEXT NOT NULL, PRIMARY KEY (`%2$s`), UNIQUE INDEX `%2$s_UNIQUE` (`%2$s` ASC))";
	private DataSource ds;
	private TableName tn;
	private Connection con;

	public DBTable() {}

	public DBTable(DataSource ds, TableName tn) throws SQLException {
		this.setDataSource(ds);
		this.setTableName(tn);
		if (tn != null && ds != null) {
			initProperties();
		}
	}

	public DBTable(Properties props) {
		super(props);
	}

	public void setTableName(String schema, String table) throws SQLException {
		tn = new TableName (new SchemaName (new Identifier(schema)), new Identifier(table));
		if (ds != null) {
			initProperties();
		}
	}
	public void setTableName(TableName tn) throws SQLException {
		this.tn = tn;
		if (tn != null && ds != null) {
			initProperties();
		}
	}

	public void setDataSource(DataSource ds) throws SQLException {
		this.ds = ds;
		if (tn != null && ds != null) {
			initProperties();
		}
	}

	private void initProperties() throws SQLException {
		Connection con = this.getConnection();
		Statement stmt = con.createStatement();
		System.out.println(tn.toSQLString(null));
		System.out.println(String.format(create, tn.toSQLString(null), DEFAULT_KEY_COLUMN, DEFAULT_VALUE_COLUMN));
		stmt.executeUpdate(String.format(create, tn.toSQLString(null), DEFAULT_KEY_COLUMN, DEFAULT_VALUE_COLUMN));
		stmt.close();
	}

	public void load(Reader reader) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void load(InputStream inStream) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public void save(OutputStream out, String comments) {
		throw new UnsupportedOperationException();
	}

	public void store(Writer writer, String comments) throws IOException {
		super.store(writer, comments);
	}

	public void store(OutputStream out, String comments) throws IOException {
		super.store(out, comments);
	}

	public void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
		throw new UnsupportedOperationException();
	}

	public void storeToXML(OutputStream os, String comment) throws IOException {
		super.storeToXML(os, comment);
	}

	public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
		super.storeToXML(os, comment, encoding);
	}

	public String getProperty(String key) {
		//System.out.println(key);
        Object oval = get(key);
        String sval = (oval instanceof String) ? (String)oval : null;
        return ((sval == null) && (defaults != null)) ? defaults.getProperty(key) : sval;
    }

	public int size() {
		try {
			return (int) (long) DBConnector.select(DBTable.class, getConnection(), DBConnector.createSelect().addFromClause(tn).addSelectColumnFunction(COUNT_FUNCTION).setDerived(COUNT_FUNCTION)).get(0).get(COUNT_FUNCTION);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Enumeration<Object> keys() {
		Query select;
		try {
			select = DBConnector.createSelect().addFromClause(tn).addColumns(DEFAULT_KEY_COLUMN);
			Rows rows = DBConnector.select(DBTable.class, con, select);
			return new DBEnumerator(rows, DEFAULT_KEY_COLUMN);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public Enumeration<Object> elements() {
		Query select;
		try {
			select = DBConnector.createSelect().addFromClause(tn).addColumns(DEFAULT_VALUE_COLUMN);
			Rows rows = DBConnector.select(DBTable.class, con, select);
			return new DBEnumerator(rows, DEFAULT_VALUE_COLUMN);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean contains (Object v) {
		if(v == null) {
			throw new NullPointerException();
		}
		try {
			return DBConnector.select(DBTable.class, getConnection(), DBConnector.createSelect().addFromClause(tn).addTableSpec(new WhereClause(new CompPred(new Object[]{new Identifier(DEFAULT_VALUE_COLUMN)}, new Object[]{v}, CompPred.EQUAL)))).size() > 0;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean containsValue(Object v) {
		return contains(v);
	}

	public boolean containsKey(Object k) {
		if(k == null) {
			throw new NullPointerException();
		}
		try {
			return DBConnector.select(DBTable.class, getConnection(), DBConnector.createSelect().addFromClause(tn).addTableSpec(new WhereClause(new CompPred(new Object[]{new Identifier(DEFAULT_KEY_COLUMN)}, new Object[]{k}, CompPred.EQUAL)))).size() == 1;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String get(Object key) {
		if(key == null) {
			throw new NullPointerException();
		}
		try {
			Rows rows = DBConnector.select(DBTable.class, getConnection(), DBConnector.createSelect().addFromClause(tn).addTableSpec(new WhereClause(new CompPred(new Object[]{new Identifier(DEFAULT_KEY_COLUMN)}, new Object[]{key}, CompPred.EQUAL))));
			return rows.size() == 1 ? (String) rows.get(0).get(DEFAULT_VALUE_COLUMN) : null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException();
		}

	}


	protected void rehash() {
		throw new UnsupportedOperationException();
	}

	public Object put(Object key, Object value) {
		String s = get(key);
		if(s != null) {
			remove(key);
		}
		putIfAbsent(key, value);
		return s;
	}

	public String remove(Object key) {
		String s = get(key);
		if(s != null) {
			try {
				DBConnector.delete(DBTable.class, this.getConnection(), DBConnector.createDelete(tn, null, new WhereClause(new CompPred(new Object[]{new Identifier(DEFAULT_KEY_COLUMN)}, new Object[]{key}, CompPred.EQUAL))));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UnsupportedOperationException();
			}
		}
		return s;
	}

	public void putAll(Map<? extends Object, ? extends Object> t) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public Object clone() {
		throw new UnsupportedOperationException();
	}

	public Set<Object> keySet() {
		throw new UnsupportedOperationException();
	}

	public Set<Map.Entry<Object, Object>> entrySet() {
		throw new UnsupportedOperationException();
	}

	public Collection<Object> values() {
		throw new UnsupportedOperationException();
	}

	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}

	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	public Object getOrDefault(Object key, Object defaultValue) {
		String v = get (key);
		return v == null ? defaultValue : v;
	}

	public void forEach(BiConsumer<? super Object, ? super Object> action) {
		throw new UnsupportedOperationException();
	}

	public void replaceAll(BiFunction<? super Object, ? super Object, ? extends Object> function) {
		throw new UnsupportedOperationException();
	}

	public String putIfAbsent(Object key, Object value) {
		if(key == null || value == null) {
			throw new NullPointerException();
		}
		String v = get(key);
		if (v == null) {
			try {
				DBConnector.insert(DBTable.class, this.getConnection(), DBConnector.createInsert(tn, new Object[]{key, value}, DEFAULT_KEY_COLUMN, DEFAULT_VALUE_COLUMN));
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw new UnsupportedOperationException(e);
			}
		}
		return v;
	}

	public boolean remove(Object key, Object value) {
		String v = get(key);
		return v.equals(value) ? remove(key) != null : false;
	}

	public boolean replace(Object key, Object oldValue, Object newValue) {
		return remove(key, oldValue) ? putIfAbsent (key, newValue) == null : false;
	}

	public String replace(Object key, Object value) {
		String v = get(key);
		if(v != null) {
			remove (key);
			put (key, value);
		}
		return v;
	}


	public String compute (Object k, BiFunction<? super Object, ? super Object, ? extends Object> f) {
		throw new UnsupportedOperationException();
	}

	public String computeIfPresent (Object k, BiFunction<? super Object, ? super Object, ? extends Object> f) {
		throw new UnsupportedOperationException();
	}

	public String computeIfAbsent(Object key, Function<? super Object,? extends Object> f) {
		throw new UnsupportedOperationException();
	}


	private Connection getConnection() throws SQLException {
		if(this.con == null || con.isClosed()) {
			this.con = ds.getConnection();
			this.con.setAutoCommit(false);
		}
		return con;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		Enumeration <?> keys = this.propertyNames();
		for(int i = 0; keys.hasMoreElements(); i ++) {
			if(i > 0) {
				sb.append(',');
				sb.append(' ');
			}
			String key = (String) keys.nextElement();
			sb.append(key);
			sb.append('=');
			sb.append(this.getProperty(key));
		}
		sb.append(']');
		return sb.toString();
	}

	private class DBEnumerator implements Enumeration<Object> {

		private Rows rows;
		private String col;

		public DBEnumerator(Rows rows2, String col) {
			rows = rows2;
			this.col = col;
		}

		@Override
		public boolean hasMoreElements() {
			return rows.size() > 0;
		}

		@Override
		public String nextElement() {
			String k = (String) rows.get(0).get(col);
			rows.remove(0);
			return k;
		}

	}

}
