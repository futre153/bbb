package org.pabk.application.emanager.routing;

import org.pabk.application.emanager.db.Row;
import org.pabk.application.emanager.module.DBConnector;
import org.pabk.application.emanager.util.Const;

public class Recipient {
	private static Recipient mainRecipient;
	private long id;
	private String name;
	private String surname;
	private String email;
	private boolean enabled;
	private String description;

	private Recipient(){}

	public static Recipient getInstance(Row row) {
		Recipient rec = new Recipient();
		rec.id = (long) row.get(DBConnector.col_id);
		rec.name = (String) row.get(DBConnector.col_name);
		rec.surname = (String) row.get(DBConnector.col_surname);
		rec.email = (String) row.get(DBConnector.col_email);
		rec.description = (String) row.get(DBConnector.col_desc);
		rec.enabled = (int) row.get(DBConnector.col_enabled) != 0;
		if((int) row.get(DBConnector.col_main) != 0) {
			Recipient.mainRecipient = rec;
		}
		return rec;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}
	public String getName () {
		return name + Const.SPACE + surname;
	}
	public String getEmail () {
		return email;
	}
	public String getDescription() {
		return description;
	}
	public boolean isEnabled () {
		return enabled;
	}
	public static Recipient getMainRecipient () {
		return Recipient.mainRecipient;
	}
	public String toString() {
		return this.getEmail();
	}
	public boolean equals (Object rec) {
		return rec instanceof Recipient ? this.getId() == ((Recipient) rec).getId() : false;
	}
	public String getFirstName () {
		return name;
	}
	public String getSurname () {
		return surname;
	}
}
