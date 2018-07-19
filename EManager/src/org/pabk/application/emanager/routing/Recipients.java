package org.pabk.application.emanager.routing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.pabk.application.emanager.db.Rows;
import org.pabk.application.emanager.module.DBConnector;
import org.pabk.application.emanager.module.ModuleImpl;
import org.pabk.application.emanager.util.Sys;

public class Recipients extends ArrayList <Recipient> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static Recipients init(ModuleImpl caller) throws IOException {
		Connection con = null;
		Recipients recs = new Recipients();
		try {
			con = DBConnector.getConnection(caller);
			Rows rows = DBConnector.select(caller, con, DBConnector.createSelect().addFromClause(DBConnector.tn_recipients));
			for (int i = 0; i < rows.size(); i ++) {
				Recipient rec = Recipient.getInstance(rows.get(i));
				Sys.addItemOnPosition (recs, rec, (int) rec.getId() - 1);
			}
			return recs;
		}
		catch (Exception e) {
			throw new IOException (e);
		}
		finally {
			if(con != null) try {con.close();} catch (SQLException e){}
		}
	}
	public Recipient getRecipient(long id) {
		for (int i = 0; i < this.size(); i ++) {
			Recipient r = this.get(i);
			if(r.getId() == id) {
				return r;
			}
		}
		throw new IndexOutOfBoundsException();
	}
}
