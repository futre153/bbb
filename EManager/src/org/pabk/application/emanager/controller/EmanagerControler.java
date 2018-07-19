package org.pabk.application.emanager.controller;

import java.util.ResourceBundle;

import org.pabk.application.emanager.module.ModuleImpl;

public interface EmanagerControler {
	void initialize(ModuleImpl module, Object objs);
	void apply(ResourceBundle rb);
}
