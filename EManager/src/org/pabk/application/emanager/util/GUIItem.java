package org.pabk.application.emanager.util;

import java.io.IOException;

import org.pabk.application.EManager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUIItem {
	private static final String DECORATED_STAGE_STYLE = "DECORATED";
	private static final String UNDECORATED_STAGE_STYLE = "UNDECORATED";
	private static final String TRANSPARENT_STAGE_STYLE = "TRANSPARENT";
	private static final String UNIFIED_STAGE_STYLE = "UNIFIED";
	private static final String UTILITY_STAGE_STYLE = "UTILITY";
	private static final String NONE_MODAL = "NONE";
	private static final String WINDOW_MODAL = "WINDOW_MODAL";
	private static final String APPLICATION_MODAL = "APPLICATION_MODAL";
	public static final String SHOW_STARTUP = "SHOW";
	private static final String WAIT_STARTUP = "WAIT";
	private String name;
	private String FXMLResource;
	private String StylesheetResource;
	private String controller;
	private String description;
	private Object GUI;
	private double width;
	private double hight;
	private StageStyle stageStyle;
	private boolean scene;
	private boolean primary;
	private String icon;
	private String title;
	private Stage stage;
	private FXMLLoader loader;
	private Modality modality;
	private String startUp;
	private boolean visible;

	public double getWidth() {
		return width;
	}

	protected void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return hight;
	}

	protected void setHeight(double hight) {
		this.hight = hight;
	}

	public StageStyle getStageStyle() {
		return stageStyle;
	}

	protected void setStageStyle(String stageStyle) {

		if(stageStyle != null) {
			if(stageStyle.equalsIgnoreCase(DECORATED_STAGE_STYLE)) {
				this.stageStyle = StageStyle.DECORATED;
			}
			else if(stageStyle.equalsIgnoreCase(UNDECORATED_STAGE_STYLE)) {
				this.stageStyle = StageStyle.UNDECORATED;
			}
			else if(stageStyle.equalsIgnoreCase(TRANSPARENT_STAGE_STYLE)) {
				this.stageStyle = StageStyle.TRANSPARENT;
			}
			else if(stageStyle.equalsIgnoreCase(UNIFIED_STAGE_STYLE)) {
				this.stageStyle = StageStyle.UNIFIED;
			}
			else if(stageStyle.equalsIgnoreCase(UTILITY_STAGE_STYLE)) {
				this.stageStyle = StageStyle.UTILITY;
			}
			else {
				this.stageStyle = StageStyle.DECORATED;
			}
		}
		else {
			this.stageStyle = StageStyle.DECORATED;
		}

	}

	public GUIItem(String name, String FXMLResource, String stylesheetResource, String controller, String description, double width, double hight, String stageStyle, boolean scene, boolean primary, String icon,String title, String modality, String startup) throws IOException {
		this.setName(name);
		this.setFXMLResource(FXMLResource);
		this.setStylesheetResource(stylesheetResource);
		this.setController(controller);
		this.setDescription(description);
		this.setWidth(width);
		this.setHeight(hight);
		this.setStageStyle(stageStyle);
		this.setScene(scene);
		this.setPrimary(primary);
		this.setIcon(icon);
		this.setTitle(title);
		this.setModality(modality);
		this.setStartUp(startup);
	}

	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	public String getFXMLResource() {
		return FXMLResource;
	}
	protected void setFXMLResource(String FXMLResource) {
		this.FXMLResource = FXMLResource;
	}
	public String getStylesheetResource() {
		return StylesheetResource;
	}
	protected void setStylesheetResource(String stylesheetResource) {
		StylesheetResource = stylesheetResource;
	}
	protected String getController() {
		return controller;
	}
	protected void setController(String controller) {
		this.controller = controller;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	public Object getGUI() {
		return GUI;
	}
	public void setGUI(Object GUI) {
		this.GUI = GUI;
	}

	public boolean isScene() {
		return scene;
	}

	public void setScene(boolean scene) {
		this.scene = scene;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {

		this.primary = primary;
		if(this.isPrimary()) {
			EManager.setPrimaryStage(this.getName());
		}
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public FXMLLoader getLoader() {
		return loader;
	}

	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}

	public Modality getModality() {
		return modality;
	}

	public void setModality(String modality) {
		if(modality != null) {
			if(modality.equalsIgnoreCase(NONE_MODAL)) {
				this.modality = Modality.NONE;
			}
			else if(modality.equalsIgnoreCase(WINDOW_MODAL)) {
				this.modality = Modality.WINDOW_MODAL;
			}
			else if(modality.equalsIgnoreCase(APPLICATION_MODAL)) {
				this.modality = Modality.APPLICATION_MODAL;
			}
			else {
				this.modality = Modality.NONE;
			}
		}
		else {
			this.modality = Modality.NONE;
		}
	}

	public String getStartUp() {
		return startUp;
	}

	public void setStartUp(String startUp) {
		if(startUp != null) {
			if(startUp.equalsIgnoreCase(SHOW_STARTUP)) {
				this.startUp = SHOW_STARTUP;
			}
			else {
				this.startUp = WAIT_STARTUP;
			}
		}
		else {
			this.startUp = SHOW_STARTUP;
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
