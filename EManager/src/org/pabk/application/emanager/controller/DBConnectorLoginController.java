package org.pabk.application.emanager.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.DBConnector;
import org.pabk.application.emanager.module.GUIManager;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.Sys;
import org.pabk.emanager.sql.sap.Identifier;
import org.pabk.emanager.sql.sap.SchemaName;
import org.pabk.util.Huffman;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class DBConnectorLoginController extends EmanagerControlerImpl {

@FXML
protected ArrayList<?> DBLoginTabs;
@FXML
protected ArrayList<?> DBLoginButtons;
@FXML
protected ArrayList<?> DBLoginLabels;
@FXML
protected ArrayList<?> DBLoginChkBoxes;
@FXML
private ComboBox<String> DBLoginComboBoxDBType;

/*
 * 0 - User Name TextField
 * 1 - Password PasswordField
 * 2 - DB Type ComboBox
 * 3 - JDBC Driver TextField
 * 4 - DB Server TextField
 * 5 - DB Port TextField
 * 6 - DB Name TextField
 * 7 - Sample URL TextField
 */

@FXML
protected ArrayList<?> DBLoginPproperties;

@FXML
private void loginDB(ActionEvent event) {
	//System.err.println("DB LOGIN ACTION");
	String username = ((TextField) DBLoginPproperties.get(0)).getText();
	String password = ((TextField) DBLoginPproperties.get(1)).getText();
	String value = (String) ((ComboBox<?>) DBLoginPproperties.get(2)).getValue();
	String type = Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, value);
	String className = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.DATABASE_DRIVER_CLASSNAME_SUFFIX, Const.EMPTY);
	//String port = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.DEFAULT_PORT_SUFFIX, Const.EMPTY);
	//String server = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.DEFAULT_SERVER_SUFFIX, Const.EMPTY);
	String url = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.SAMPLE_URL_SUFFIX, Const.EMPTY);
	String server = ((TextField) DBLoginPproperties.get(4)).getText();
	String port = ((TextField) DBLoginPproperties.get(5)).getText();
	String schema = ((TextField) DBLoginPproperties.get(6)).getText();

	Sys.setProperty(DBConnector.class, Const.DATABASE_SCHEMA, schema);
	if(username != null && username.length() > 0) {try {Sys.setValuableProperty(DBConnector.class, Const.DATABASE_USERNAME, Huffman.encode(username, null));} catch (Exception e) {}}

		if(password != null && password.length() > 0) {try {Sys.setValuableProperty(DBConnector.class,  Const.DATABASE_PASSWORD, Huffman.encode(password, null));} catch (Exception e) {}}
		System.err.println("PASSWORD SAVED");

	Sys.setProperty(DBConnector.class, Const.DATABASE_PORT, port);
	Sys.setProperty(DBConnector.class, Const.DATABASE_SERVER, server);
	Sys.setProperty(DBConnector.class, Const.DATABASE_URL_PATTERN, url);
	Sys.setProperty(DBConnector.class, Const.DATABASE_DRIVER_CLASS_NAME, className);
	String dbLoginGui = Sys.getProperty(DBConnector.class, Const.DBCONNECTOR_LOGIN_GUI, Const.DEFAULT_DBCONNECTOR_LOGIN_GUI);
	GUIManager.operationAborted(false);
	GUIManager.hideStage(DBConnector.class, dbLoginGui);
}


@FXML
private void DBLoginInputKeyReleaded (KeyEvent event) {
	String username = ((TextField) DBLoginPproperties.get(0)).getText();
	//System.err.println("USERNAME KEY TYPED username = " + username);
	String value = (String) ((ComboBox<?>) DBLoginPproperties.get(2)).getValue();
	String type = Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, value);
	String schema = ((TextField) DBLoginPproperties.get(6)).getText();
	String port = ((TextField) DBLoginPproperties.get(5)).getText();
	String server = ((TextField) DBLoginPproperties.get(4)).getText();
	String url = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.SAMPLE_URL_SUFFIX, Const.EMPTY);
	try {
		String other = Sys.getOtherJDBCSettingsString(type);
		((TextField) DBLoginPproperties.get(7)).setText(String.format(url, server, Integer.parseInt(port), schema , username, Const.HIDDEN_PASSWORD, other));
	}
	catch (Exception e) {
		((TextField) DBLoginPproperties.get(7)).setText(Const.EMPTY);
	}
}


@FXML
private void DBTypeAction(ActionEvent event) {

	if(DBLoginPproperties.get(2) instanceof ComboBox<?>) {

		String value = (String) ((ComboBox<?>) DBLoginPproperties.get(2)).getValue();
		String type = Sys.getDatabaseTypeFromComboBoxValue(DBConnector.class, value);
		//Sys.selectComboBox(DBLoginComboBoxDBType, type);
		String className = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.DATABASE_DRIVER_CLASSNAME_SUFFIX, Const.EMPTY);
		String port = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.DEFAULT_PORT_SUFFIX, Const.EMPTY);
		String server = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.DEFAULT_SERVER_SUFFIX, Const.EMPTY);
		String url = Sys.getProperty(DBConnector.class, DBConnector.class.getSimpleName() + Const.PERIOD + type + Const.PERIOD + Const.SAMPLE_URL_SUFFIX, Const.EMPTY);
		((TextField) DBLoginPproperties.get(3)).setText(className);
		((TextField) DBLoginPproperties.get(5)).setText(port);
		((TextField) DBLoginPproperties.get(4)).setText(server);
		String schema = ((TextField) DBLoginPproperties.get(6)).getText();
		String username = ((TextField) DBLoginPproperties.get(0)).getText();
		try {
			String other = Sys.getOtherJDBCSettingsString(type);
			((TextField) DBLoginPproperties.get(7)).setText(String.format(url, server, Integer.parseInt(port), schema ,username, Const.HIDDEN_PASSWORD, other));
		}
		catch (Exception e) {
			((TextField) DBLoginPproperties.get(7)).setText(Const.EMPTY);
		}

		//System.out.println(type);
	}
}

@Override
public void initialize(URL arg0, ResourceBundle rb) {
	// TODO Auto-generated method stub
	super.initialize(arg0, rb);
	String schema = Sys.getProperty(DBConnector.class, Const.DATABASE_SCHEMA, Const.EMPTY);
	String username = Sys.getProperty(DBConnector.class, Const.DATABASE_USERNAME, Const.EMPTY);
	String password = Sys.getProperty(DBConnector.class, Const.DATABASE_PASSWORD, Const.EMPTY);
	String port = Sys.getProperty(DBConnector.class, Const.DATABASE_PORT, Const.EMPTY);
	String server = Sys.getProperty(DBConnector.class, Const.DATABASE_SERVER, Const.EMPTY);
	String url = Sys.getProperty(DBConnector.class, Const.DATABASE_URL_PATTERN, Const.EMPTY);
	String className = Sys.getProperty(DBConnector.class, Const.DATABASE_DRIVER_CLASS_NAME, Const.EMPTY);
	String type = Sys.getDatabaseTypeFromClass(DBConnector.class, className);
	try {username = Huffman.decode(username, null);} catch (Exception e) {}
	try {password = Huffman.decode(password, null);} catch (Exception e) {}
	//password = "123456";
	 ObservableList<String> items = Sys.getSupportedDatabasesToComboBoxList(DBConnector.class);


	 DBLoginComboBoxDBType.setItems(items);

	((TextField) DBLoginPproperties.get(0)).setText(username);
	((PasswordField) DBLoginPproperties.get(1)).setText(password);
	((TextField) DBLoginPproperties.get(3)).setText(className);
	((TextField) DBLoginPproperties.get(4)).setText(server);
	((TextField) DBLoginPproperties.get(5)).setText(port);
	((TextField) DBLoginPproperties.get(6)).setText(schema);
	try {
		((TextField) DBLoginPproperties.get(7)).setText(String.format(url, server, Integer.parseInt(port), schema, Const.USERNAME, Const.HIDDEN_PASSWORD, Sys.getOtherJDBCSettingsString(type)));
	}
	catch (Exception e) {
		//e.printStackTrace();
		((TextField) DBLoginPproperties.get(7)).setText(Const.EMPTY);
	}

	Sys.selectComboBox(DBLoginComboBoxDBType, type);








	//System.err.println(DBLoginButtons);
	//System.err.println(DBLoginLabels);
	//System.err.println(DBLoginTabs);
	//System.err.println(DBLoginChkBoxes);
	//System.err.println(DBLoginPproperties);

}

}
