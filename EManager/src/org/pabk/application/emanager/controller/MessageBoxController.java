package org.pabk.application.emanager.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.GUIManager;
import org.pabk.application.emanager.module.ModuleImpl;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.EManagerResourceBundle;

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
	@FXML
	protected ArrayList<?> msgBoxBtns;



	@Override
	public void initialize(URL url, ResourceBundle rb) {
		super.initialize(url, rb);
	}
	@Override
	public void initialize(Object caller) {
		super.initialize(module);
		// TODO Auto-generated method stub
		EManagerResourceBundle erb = (EManagerResourceBundle) GUIManager.getResourceBundle();
		super.initialize(module);
		String message = erb.getMessageBoxMessage();
			erb.setMessageBoxMessage(null);
			messageBoxText.setText(message == null ? Const.EMPTY : message);
	}
	@FXML
	private void close(ActionEvent event) {
		((Stage) messageBox.getScene().getWindow()).hide();
	}
}
