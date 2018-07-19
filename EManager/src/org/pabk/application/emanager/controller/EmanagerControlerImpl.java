package org.pabk.application.emanager.controller;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.ModuleImpl;
import org.pabk.application.emanager.util.Sys;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EmanagerControlerImpl implements EmanagerControler {

	@SuppressWarnings("unused")
	private static final String MODULE_NULL = "Module cannot have a null value or object is not instance of ModuleImpl";
	protected ModuleImpl module;
	@Override
	public void initialize(ModuleImpl module, Object obj) {
		Object[] objs = (Object[]) obj;
		System.err.println("CCCCCC " + objs.length);
		System.err.println(module + " CONTROLER " + this + "IS UNDER INITIALIZATION");
		this.module = module;

	}
	@Override
	public void apply(ResourceBundle rb) {
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field : fields) {
			try {
				Object fieldValue = field.get(this);
				if (fieldValue instanceof ArrayList<?>) {
			        Class<?> _class = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
			        if(_class.equals(Button.class)) {
			        	Sys.applyResourceBundleForLabeled((ArrayList<?>) fieldValue, rb);
			        }
			        else if(_class.equals(MenuItem.class)) {
			        	Sys.applyResourceBundleForMenuItem((ArrayList<?>) fieldValue, rb);
			        }
			        else if(_class.equals(Menu.class)) {
			        	Sys.applyResourceBundleForMenu((ArrayList<?>) fieldValue, rb);
			        }

				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
