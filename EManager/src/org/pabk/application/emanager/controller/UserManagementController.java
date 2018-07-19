package org.pabk.application.emanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.pabk.application.emanager.module.GUIManager;
import org.pabk.application.emanager.module.ModuleImpl;
import org.pabk.application.emanager.routing.Recipient;
import org.pabk.application.emanager.routing.Recipients;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.GUIItem;
import org.pabk.application.emanager.util.Sys;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserManagementController extends EmanagerControlerImpl implements Initializable {

	private static String userManagmentGuiName;
	private static String userManagmentLineGuiName;
	private static String userManagmentBodyId;
	private static String userManagmentLineId;
	private static String userManagmentLineNameId;
	private static String userManagmentLineEmailId;
	private static String userManagmentLineButtonStateId;
	private static String userManagmentLineButtonEnabledClass;
	private static String userManagmentLineButtonDisabledClass;
	private static String userManagementConfirmMessageEnable;
	private static String userManagementConfirmMessageDisable;
	private static String confirmMessageGuiName;

	private static boolean intialized = false;
	private static Recipients recipients;
	private static String userDisabledMessage;
	private static String userEnabledMessage;
	private static String messageBoxGuiName;


	private static boolean isIntialized() {
		return intialized;
	}

	private static void setIntialized(boolean intialized) {
		UserManagementController.intialized = intialized;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.err.println(this.getClass() + " IS UNDER INTIALIZATION");
		}

	@FXML
	private void changeState(ActionEvent event) {
		Button btn = (Button) event.getSource();
		long id = Long.parseLong(((Label)btn.getParent().getParent().getParent().lookup(userManagmentLineId)).getText());
		Recipient recipient = recipients.getRecipient(id);
		String confirmMessage = null;
		String successMessage = null;
		ObservableList<String> style = btn.getStyleClass();
		if(style.contains(userManagmentLineButtonDisabledClass)) {
			/*TODO */
			//style.remove(userManagmentLineButtonDisabledClass);
			//style.add(userManagmentLineButtonEnabledClass);
			recipient.setEnabled(true);
			confirmMessage = String.format(userManagementConfirmMessageEnable, recipient.getName());
			successMessage = String.format(userEnabledMessage, recipient.getName());
		}
		else {
			/*TODO */
			//style.remove(userManagmentLineButtonEnabledClass);
			//style.add(userManagmentLineButtonDisabledClass);
			recipient.setEnabled(false);
			confirmMessage = String.format(userManagementConfirmMessageDisable, recipient.getName());
			successMessage = String.format(userDisabledMessage, recipient.getName());
		}
		System.out.println("STATE BUTTON PRESSED FOR ID = " + id);
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//Sys.updateRecipient(recipient, confirmMessageGuiName, userManagmentGuiName, messageBoxGuiName, successMessage);
			}

		};
		System.out.println("I LOOSE CONTROL");
		GUIManager.loadStage(null, null, "confirm-message", true);
		System.out.println("I HAVE CONTROL AGAIN");
	}


	@Override
	public void initialize(ModuleImpl module, Object obj) {
		// TODO Auto-generated method stub
	/*	System.err.println(this.getClass() + " IS UNDER MODULE INTIALIZATION");
		super.initialize(module, obj);
		if(!UserManagementController.isIntialized()) {
			userManagmentGuiName = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_GUI_KEY, Const.USER_MANAGEMENT_GUI_DEFAAULT);
			userManagmentLineGuiName  = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_LINE_GUI_KEY, Const.USER_MANAGEMENT_LINE_GUI_DEFAAULT);
			userManagmentBodyId = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_BODY_ID_KEY, Const.USER_MANAGEMENT_BODY_ID_DEFAAULT);
			userManagmentLineId = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_LINE_ID_KEY, Const.USER_MANAGEMENT_LINE_ID_DEFAAULT);
			userManagmentLineNameId = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_LINE_NAME_ID_KEY, Const.USER_MANAGEMENT_LINE_NAME_ID_DEFAULT);
			userManagmentLineEmailId = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_LINE_EMAIL_ID_KEY, Const.USER_MANAGEMENT_LINE_EMAIL_ID_DEFAAULT);
			userManagmentLineButtonStateId = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_LINE_BUTTON_STATE_ID_KEY, Const.USER_MANAGEMENT_LINE_BUTTON_STATE_ID_DEFAAULT);
			userManagmentLineButtonEnabledClass = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_LINE_BUTTON_ENABLED_CLASS_KEY, Const.USER_MANAGEMENT_LINE_BUTTON_ENABLED_CLASS_DEFAAULT);
			userManagmentLineButtonDisabledClass = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_LINE_BUTTON_DISABLED_CLASS_KEY, Const.USER_MANAGEMENT_LINE_BUTTON_DISABLED_CLASS_DEFAAULT);
			userManagementConfirmMessageEnable = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_CONFIRM_MESSAGE_ENABLE_KEY, Const.USER_MANAGEMENT_CONFIRM_MESSAGE_ENABLE_DEFAULT);
			userManagementConfirmMessageDisable = Sys.getProperty(module.getProperties(), Const.USER_MANAGEMENT_CONFIRM_MESSAGE_DISABLE_KEY, Const.USER_MANAGEMENT_CONFIRM_MESSAGE_DISABLE_DEFAULT);

			UserManagementController.userEnabledMessage = Sys.getProperty(module.getProperties(), Const.USER_ENABLED_MESSAGE_KEY, Const.USER_ENABLED_MESSAGE_DEFAULT);
			UserManagementController.userDisabledMessage = Sys.getProperty(module.getProperties(), Const.USER_DISABLED_MESSAGE_KEY, Const.USER_DISABLED_MESSAGE_DEFAULT);
			confirmMessageGuiName = Sys.getProperty(module.getProperties(), Const.CONFIRM_MESSAGE_GUI_NAME_KEY, Const.CONFIRM_MESSAGE_GUI_NAME_DEFAULT);
			confirmMessageGuiName = Sys.getProperty(module.getProperties(), Const.CONFIRM_MESSAGE_GUI_NAME_KEY, Const.CONFIRM_MESSAGE_GUI_NAME_DEFAULT);
			UserManagementController.messageBoxGuiName = Sys.getProperty(module.getProperties(), Const.MESSAGE_BOX_GUI_NAME_KEY, Const.MESSAGEBOX__GUI_NAME_DEFAULT);
			UserManagementController.setIntialized(true);
		}
		GUIItem userManagementGui = GUIManager.getGUI(userManagmentGuiName);
		GUIItem line = GUIManager.getGUI(userManagmentLineGuiName);
		VBox body = ((VBox) ((Node) userManagementGui.getGUI()).lookup(userManagmentBodyId));
		body.getChildren().clear();
		Recipients recs = Routing.getRecipients();
		UserManagementController.recipients = recs;
		for(int i = 0; i < recs.size(); i ++) {
			Recipient rec = recs.get(i);
			long idr = rec.getId();
			Object clonedLine = Sys.duplicate(module.getLogger(), null, line.getGUI());
			Label l = (Label) ((Node) clonedLine).lookup(userManagmentLineId);
			String id = Long.toString(idr + 1);
			id = "00000000".substring(id.length()) + idr;
			l.setText(id);
			l = (Label) ((Node) clonedLine).lookup(userManagmentLineNameId);
			l.setText(rec.getName());
			l = (Label) ((Node) clonedLine).lookup(userManagmentLineEmailId);
			l.setText(rec.getEmail());
			body.getChildren().add((Node) clonedLine);
			Button btn = (Button) ((Node) clonedLine).lookup(userManagmentLineButtonStateId);
			btn.getStyleClass().remove(userManagmentLineButtonEnabledClass);
			btn.getStyleClass().remove(userManagmentLineButtonDisabledClass);
			btn.getStyleClass().add(rec.isEnabled() ? userManagmentLineButtonEnabledClass: userManagmentLineButtonDisabledClass);
*/
		}



}
