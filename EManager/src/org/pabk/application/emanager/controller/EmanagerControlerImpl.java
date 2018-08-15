package org.pabk.application.emanager.controller;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.ModuleImpl;

import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;

public class EmanagerControlerImpl implements EmanagerControler {

	@SuppressWarnings("unused")
	private static final String MODULE_NULL = "Module cannot have a null value or object is not instance of ModuleImpl";
	protected ModuleImpl module;
	protected ResourceBundle resourceBundle;

	@Override
	public void initialize(Object caller) {
		System.err.println(module + " CONTROLER " + this + "IS UNDER SUBJECT INITIALIZATION");
		if(caller instanceof ModuleImpl)
		this.module = (ModuleImpl) caller;

	}
	@Override
	public void apply(ResourceBundle rb) {
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field : fields) {
			Object fieldValue;
			try {
				fieldValue = field.get(this);
				if (fieldValue instanceof ArrayList<?>) {
					for (Object object : (ArrayList<?>) fieldValue) {
						apply(rb, object);
					}
				}
			} catch (Exception  e) {}
			}
		}


	public void apply(ResourceBundle rb, Object node) {
		/*if(node instanceof Button) {
			((Button) node).setText(rb.getString(((Button) node).getText()));
		}*/
		if(node instanceof Labeled) {
			((Labeled) node).setText(rb.getString(((Labeled) node).getText()));
		}
		else if(node instanceof MenuItem) {
			((MenuItem) node).setText(rb.getString(((MenuItem) node).getText()));
		}
		else if(node instanceof Menu) {
			((Menu) node).setText(rb.getString(((Menu) node).getText()));
		}
		else if(node instanceof Tab) {
			((Tab) node).setText(rb.getString(((Tab) node).getText()));
		}
		else if(node instanceof Text) {
			((Text) node).setText(rb.getString(((Text) node).getText()));
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle rb) {

		System.err.println(this.getClass()+ " IS UNDER INTIALIZATION");
		this.resourceBundle = rb;
		this.apply(rb);
	}
}
