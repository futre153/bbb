package org.pabk.application.emanager.controller;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.ModuleImpl;
import org.pabk.application.emanager.util.Const;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorWithExceptionController extends EmanagerControlerImpl implements Initializable {

	@FXML
	private BorderPane ErrorWithException;
	@FXML
	private Text errorWithExceptionCaption;
	@FXML
	private TextArea errorWithExceptionDetails;

	@Override
	public void initialize(ModuleImpl module, Object obj) {
		// TODO Auto-generated method stub
/*		super.initialize(module, obj);
		Object[] objs = (Object[]) obj;
		Exception e = (Exception) objs[0];
		String text = e.getMessage();
		text = text == null ? Const.UNKNOWN_ERROR : text;
		errorWithExceptionCaption.setText(e.getMessage());
		errorWithExceptionDetails.setText(Const.EMPTY);
		PrintStream ps = new PrintStream(new TextAreaOutputStream(errorWithExceptionDetails));
		errorWithExceptionDetails.positionCaret(errorWithExceptionDetails.getText().length());
		e.printStackTrace(ps);
		errorWithExceptionDetails.positionCaret(0);*/
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void close(ActionEvent event) {
		((Stage) ErrorWithException.getScene().getWindow()).hide();
	}
}
