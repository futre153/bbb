package org.pabk.application.emanager.controller;

import java.util.ResourceBundle;

import org.pabk.application.emanager.module.ModuleImpl;

import javafx.fxml.Initializable;

public interface EmanagerControler extends Initializable {
	void initialize(Object caller);
	void apply(ResourceBundle rb);
}
