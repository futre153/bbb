package org.pabk.application.emanager.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.GUIManager;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.Sys;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EManAppController extends EmanagerControlerImpl implements Initializable {

	@FXML
	ArrayList<Button> EMBtns;
	@FXML
	ArrayList<Menu> EMMenus;
	@FXML
	ArrayList<MenuItem> EMMenuItems;


	@FXML
	private void closeApplication() {
		System.out.println("Appl close");
	}

	@FXML
	private void openUserNamagement() {
		try {
			/*
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../resource/fxml/UserManagement.fxml"));
			Scene scene = new Scene(root,512,512);
			scene.getStylesheets().add(getClass().getResource("../resource/css/user-management.css").toExternalForm());
			//primaryStage.setScene(scene);
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle("User Management");
			//System.out.println(EManAppController.class.getResource("../resource/image/User-icon64x64.png"));
			;

			stage.getIcons().add(new Image(EManAppController.class.getResourceAsStream("../resource/image/Users-icon64x64.png")));
			stage.setScene(scene);
			stage.show();
			*/
			GUIManager.loadStage(EManAppController.class, null, Sys.getProperty(GUIManager.getManager(), Const.USER_MANAGEMENT_GUI_KEY, Const.USER_MANAGEMENT_GUI_DEFAAULT), true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle rb) {

		System.err.println(this.getClass()+ " IS UNDER INTIALIZATION");
		System.out.println(EMBtns);
		this.apply(rb);

		// TODO Auto-generated method stub

	}
}