package org.pabk.application.emanager.controller;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.GUIManager;
import org.pabk.application.emanager.module.ModuleImpl;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.EManagerResourceBundle;
import org.pabk.application.emanager.util.TextAreaOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorWithExceptionController extends EmanagerControlerImpl {

	@FXML
	private BorderPane ErrorWithException;
	@FXML
	private Text errorWithExceptionCaption;
	@FXML
	private Button errorDetailsBtn;
	@FXML
	private Button errorOKBtn;
	@FXML
	protected ArrayList<?>errorTexts;
	@FXML
	protected ArrayList<?>errorBtns;

	@FXML
	private TextArea errorWithExceptionDetails;

	@Override
	public void initialize (Object caller) {
		super.initialize(caller);
		Throwable error = ((EManagerResourceBundle) resourceBundle).getError();
		if(error != null) {
			String text = error.getMessage();
			text = text == null ? Const.UNKNOWN_ERROR : text;
			errorWithExceptionCaption.setText(text);
			errorWithExceptionDetails.setText(Const.EMPTY);
			PrintStream ps = null;
			try {
				ps = new PrintStream(new TextAreaOutputStream(errorWithExceptionDetails, Charset.defaultCharset().displayName()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			errorWithExceptionDetails.positionCaret(errorWithExceptionDetails.getText().length());
			error.printStackTrace(ps);
			ps.flush();
			errorWithExceptionDetails.positionCaret(0);
		}
	}

	@FXML
	private void showDetails(ActionEvent action) {
		//System.out.println("SHOW/HIDE ERROR");
		//System.out.println(errorWithExceptionDetails.isVisible());
		errorDetailsBtn.setText(GUIManager.getResourceBundle().getString(errorWithExceptionDetails.isVisible() ? Const.SHOW_EXCEPTION_DETAILS_TEXT : Const.HIDE_EXCEPTION_DETAILS_TEXT));
		errorWithExceptionDetails.setVisible(!errorWithExceptionDetails.isVisible());

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		super.initialize(url, rb);
	}

	@FXML
	private void close(ActionEvent event) {
		((Stage) ErrorWithException.getScene().getWindow()).hide();
	}
}
