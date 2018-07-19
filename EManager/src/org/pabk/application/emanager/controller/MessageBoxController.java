package org.pabk.application.emanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.ModuleImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageBoxController extends EmanagerControlerImpl implements Initializable {

	@FXML
	private BorderPane messageBox;
	@FXML
	private Text messageBoxText;

	@Override
	public void initialize(ModuleImpl module, Object obj) {
		// TODO Auto-generated method stub
		super.initialize(module, obj);
		Object[] objs = (Object[]) obj;
		messageBoxText.setText((String) objs[0]);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	@FXML
	private void close(ActionEvent event) {
		((Stage) messageBox.getScene().getWindow()).hide();
	}
}
