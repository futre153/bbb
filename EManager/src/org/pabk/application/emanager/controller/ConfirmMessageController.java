package org.pabk.application.emanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfirmMessageController extends EmanagerControlerImpl implements Initializable {

	@FXML
	private BorderPane ConfirmMessage;
	@FXML
	private Button confirmButton;
	@FXML
	private Text confirmMessageText;


	@Override
	public void initialize(Object module) {
		//System.out.println(ConfirmMessage);
		//System.out.println(confirmButton);
		//System.out.println(confirmMessageText);

		// TODO Auto-generated method stub
		super.initialize(module);
		//System.out.println (confirmButton);
		/*Object[] args = ((Object[]) obj);
		EventHandler<ActionEvent> handler = (EventHandler<ActionEvent>) args[0];
		confirmMessageText.setText((String) args[1]);
		confirmButton.setOnAction((EventHandler<ActionEvent>) handler);
*/
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	@FXML
	private void close(ActionEvent event) {
		System.out.println("confirm message cancel action");
		System.out.println(ConfirmMessage);
		System.out.println(confirmButton);
		System.out.println(confirmMessageText);
		((Stage) ConfirmMessage.getScene().getWindow()).hide();
	}

}
