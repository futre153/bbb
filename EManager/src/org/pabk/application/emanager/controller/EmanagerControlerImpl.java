package org.pabk.application.emanager.controller;

import org.pabk.application.emanager.module.ModuleImpl;

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
}
