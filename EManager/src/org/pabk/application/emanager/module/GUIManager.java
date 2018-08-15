package org.pabk.application.emanager.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.pabk.application.EManager;
import org.pabk.application.emanager.controller.EmanagerControler;
import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.GUIItem;
import org.pabk.application.emanager.util.Sys;
import org.pabk.application.emanager.util.XMLSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUIManager extends ModuleImpl {








	private static Hashtable<String, GUIItem> GUIList= new Hashtable<String, GUIItem>();
	private static GUIManager manager;
	private static Locale locale;
	private static ResourceBundle resourceBundle;
	private static String baseName;
	private static String resourceBundleClassName;
	private static String resourceBundleConttrolClassName;

	public static Locale getLocale() {
		return locale;
	}

	public static void setLocale(Locale locale) {
		GUIManager.locale = locale;
	}

	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public static void setResourceBundle(ResourceBundle resourceBundle) {
		GUIManager.resourceBundle = resourceBundle;
	}

	public static ResourceBundle.Control getResourceBundleControl() {
		return resourceBundleControl;
	}

	public static void setResourceBundleControl(ResourceBundle.Control resourceBundleControl) {
		GUIManager.resourceBundleControl = resourceBundleControl;
	}

	private static ResourceBundle.Control resourceBundleControl;
	private static boolean operationAborted;


	/*public void init(ModuleX modulex) {
		// TODO Auto-generated method stub
		super.init(modulex);
		String GUIResource = Sys.getProperty(properties, GUI_LIST_RESOURCE_KEY, DEFAULT_GUI_RESOURCE_PATH);
		try {
			GUIManager.loadGUIList(GUIResource, this);
		} catch (SAXException | IOException | ParserConfigurationException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GUIManager.setManager(this);

	}*/

	public static void loadGUIList(String guiResource) throws IOException, SAXException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Document document = null;
		try {
			document  = XMLSupport.loadXMLFile(guiResource);
			Sys.infof(GUIManager.class, Const.GUI_LIST_LOADED_FROM_SOURCE, guiResource);
		} catch (Exception e) {
			Sys.errorf(GUIManager.class, Const.FAILED_TO_LOAD_GUI_LIST_LOADED_FROM_SOURCE, guiResource);
			throw new IOException (e);
		}
		NamedNodeMap attributes = document.getDocumentElement().getAttributes();
		Node attribute = attributes.getNamedItem(Const.GUI_LANGUAGE_ATTRIBUTE_NAME);
		String language = attribute.getNodeValue();
		language = language == null || language.length() == 0 ? Const.GUI_DEFAULT_LANGUAGE: language;
		GUIManager.setLocale (Sys.getLocale(language));
		Sys.infof(GUIManager.class, Const.DEFAULT_LANGUAGE_SET, GUIManager.getLocale().getDisplayLanguage(), EManager.class.getSimpleName());
		attribute = attributes.getNamedItem(Const.GUI_RESOURCE_BUNDLE_CLASS_NAME_ATTRIBUTE_NAME);
		String rbClassName = attribute.getNodeValue();
		GUIManager.setResourceBundleClassName(rbClassName);
		attribute = attributes.getNamedItem(Const.GUI_RESOURCE_BUNDLE_BASE_NAME_ATTRIBUTE_NAME);
		String baseName = attribute.getNodeValue();
		GUIManager.setBaseName(baseName);
		attribute = attributes.getNamedItem(Const.GUI_RECOURCE_BUNDLE_CONTROL_CLASS_NAME_ATTRIBUTE_NAME);
		String rbcClassName = attribute.getNodeValue();
		GUIManager.setResourceBundleConttrolClassName(rbcClassName);
		ResourceBundle rb = Sys.getBundle(GUIManager.getResourceBundleClassName(), GUIManager.getBaseName(), GUIManager.getLocale(), GUIManager.getResourceBundleConttrolClassName());
		Sys.infof(GUIManager.class, Const.RESOURCE_BUNDLE_SET, GUIManager.getBaseName());
		ArrayList<Element> items = XMLSupport.getChildrenElementsByTagName(document.getDocumentElement(), Const.GUI_ITEM_TAGNAME);
		GUIManager.setResourceBundle(rb);
		Sys.infof(GUIManager.class, Const.GUI_ITEMS_COUNT, items.size());
		for(int i = 0; i < items.size(); i ++) {
			ArrayList<Element> elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_NAME_TAGNAME);

			if(elements.size() == 0) {
				Sys.errorf(GUIManager.class, Const.GUI_ITEM_NAME_NULL);
				throw new IOException(Sys.format(Const.GUI_ITEM_NAME_NULL));
			}
			String name = elements.get(0).getTextContent();
			if(name == null|| name.length() == 0) {
				Sys.errorf(GUIManager.class, Const.GUI_ITEM_NAME_NULL);
				throw new IOException(Sys.format(Const.GUI_ITEM_NAME_NULL));
			}
			Sys.infof(GUIManager.class, Const.GUI_ITEM_FOUND, name);

			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_FXML_TAGNAME);
			if(elements.size() == 0) {
				Sys.errorf(GUIManager.class, Const.GUI_ITEM_FXML_NULL, name);
				throw new IOException(Sys.format(Const.GUI_ITEM_FXML_NULL, name));
			}
			String fxml = elements.get(0).getTextContent();
			if(fxml == null || fxml.length() == 0) {
				Sys.errorf(GUIManager.class, Const.GUI_ITEM_FXML_NULL, name);
				throw new IOException(Sys.format(Const.GUI_ITEM_FXML_NULL, name));
			}
			Sys.infof(GUIManager.class, Const.FXML_FOUND_FOR_GUI_ITEM, fxml, name);
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_STYLESHEET_TAGNAME);
			String stylesheet  = null;
			if(elements.size() != 0) {
				stylesheet = elements.get(0).getTextContent();
			}
			if((stylesheet = stylesheet != null && stylesheet.length() == 0 ? null : stylesheet) != null) {
				Sys.infof(GUIManager.class, Const.STYLESHEET_FOUND, stylesheet, name);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_STYLESHEET_FOUND, name);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_CONTROLLER_TAGNAME);
			String controLLer  = null;
			if(elements.size() != 0) {
				controLLer = elements.get(0).getTextContent();
			}
			if((controLLer = controLLer != null && controLLer.length() == 0 ? null : controLLer) != null) {
				Sys.infof(GUIManager.class, Const.CONTROLLER_FOUND, controLLer, name);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_CONTROLLER_FOUND, name);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_DESCRIPTION_TAGNAME);
			String description  = null;
			if(elements.size() != 0) {
				description = elements.get(0).getTextContent();
			}
			if((description = description != null && description.length() == 0 ? null : description) != null) {
				Sys.infof(GUIManager.class, Const.DESCRIPTION_FOUND, description, name);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_DESCRIPTION_FOUND, name);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_TITLE_TAGNAME);
			String title  = null;
			if(elements.size() != 0) {
				title = elements.get(0).getTextContent();
			}
			if((title = title != null && title.length() == 0 ? null : title) != null) {
				Sys.infof(GUIManager.class, Const.TITLE_FOUND, title, name);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_TITLE_FOUND, name);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_STAGE_STYLE_TAGNAME);
			String stageStyle  = null;
			if(elements.size() != 0) {
				stageStyle = elements.get(0).getTextContent();
			}
			if((stageStyle = stageStyle != null && stageStyle.length() == 0 ? null : stageStyle) != null) {
				Sys.infof(GUIManager.class, Const.STAGE_STYLE_FOUND, stageStyle, name);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_STAGE_STYLE_FOUND, name);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_ICON_TAGNAME);
			String icon  = null;
			if(elements.size() != 0) {
				icon = elements.get(0).getTextContent();
			}
			if((icon = icon != null && icon.length() == 0 ? null : icon) != null) {
				Sys.infof(GUIManager.class, Const.ICON_FOUND, icon, name);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_ICON_FOUND, name);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_WIDTH_TAGNAME);
			double width  = 0;
			if(elements.size() != 0) {
				try {
					width = Double.parseDouble(elements.get(0).getTextContent());
					Sys.infof(GUIManager.class, Const.WIDTH_FOUND, name, width);
				}
				catch (Exception e) {
					width = 0;
					Sys.warnf (GUIManager.class, Const.WIDTH_NOT_RETREIVE, name, width);
				}
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_HIGHT_TAGNAME);
			double height  = 0;
			if(elements.size() != 0) {
				try {
					height = Double.parseDouble(elements.get(0).getTextContent());
					Sys.infof (GUIManager.class, Const.HEIGHT_FOUND, name, height);
				}
				catch (Exception e) {
					height = 0;
					Sys.warnf (GUIManager.class, Const.HEIGHT_NOT_RETREIVE, name, height);
				}
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_SCENE_TAGNAME);
			boolean scene  = false;
			if(elements.size() != 0) {
				try {
					scene = Boolean.parseBoolean(elements.get(0).getTextContent());
					Sys.infof(GUIManager.class, scene ? Const.SCENE_INDICATOR_LOADED_TRUE: Const.SCENE_INDICATOR_LOADED_FALSE, name);
				}
				catch(Exception e) {
					Sys.errorf(GUIManager.class, Const.SCENE_INDICATOR_NOT_RETREIVED, name);
					throw new IOException(Sys.format(Const.SCENE_INDICATOR_NOT_RETREIVED, name));
				}
			}
			else {
				Sys.errorf(GUIManager.class, Const.GUI_ITEM_SCENE_INDICATOR_NULL, name);
				throw new IOException(Sys.format (Const.GUI_ITEM_SCENE_INDICATOR_NULL, name));
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_PRIMARY_TAGNAME);
			boolean primary  = false;
			if(elements.size() != 0) {
				try {
					primary = Boolean.parseBoolean(elements.get(0).getTextContent());
					Sys.infof(GUIManager.class, primary ? Const.PRIMARY_INDICATOR_LOADED_TRUE: Const.PRIMARY_INDICATOR_LOADED_FALSE, name);
				}
				catch (Exception e) {
					Sys.errorf(GUIManager.class, Const.PRIMARY_INDICATOR_NOT_RETREIVED, name);
					throw new IOException(Sys.format(Const.PRIMARY_INDICATOR_NOT_RETREIVED, name));
				}
			}
			else {
				Sys.errorf(GUIManager.class, Const.GUI_ITEM_PRIMARY_NULL, name);
				throw new IOException(Sys.format(Const.GUI_ITEM_PRIMARY_NULL, name));
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_MODALITY_TAGNAME);
			String modality  = null;
			if(elements.size() != 0) {
				modality = elements.get(0).getTextContent();
			}
			if((modality = modality != null && modality.length() == 0 ? null : modality) != null) {
				Sys.infof(GUIManager.class, Const.MODALITY_FOUND, modality, name);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_MODALITY_FOUND, name);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_STARTUP_TAGNAME);
			String startup  = null;
			if(elements.size() != 0) {
				startup = elements.get(0).getTextContent();
			}
			if((startup = startup != null && startup.length() == 0 ? null : startup) != null) {
				Sys.infof(GUIManager.class, Const.STARTUP_FOUND, name, startup);
			}
			else {
				Sys.infof(GUIManager.class, Const.NO_STARTUP_FOUND, name);
			}
			GUIItem item = new GUIItem(name, fxml, stylesheet, controLLer, description, width, height, stageStyle, scene, primary, icon, title, modality, startup);
			FXMLLoader loader = new FXMLLoader(GUIManager.class.getResource(item.getFXMLResource()));
			if(controLLer != null) {
				Object guiController = Class.forName(controLLer).newInstance();
				if(rb != null) {
					loader.setResources(rb);
				}
				loader.setController(guiController);
				if(GUIManager.getGUIList() == null) {
					GUIManager.setGUIList(new Hashtable<String, GUIItem>());
				}
				item.setLoader(loader);
			}
			item.setGUI(loader.load());
			GUIManager.getGUIList().put(item.getName(), item);
			Sys.infof(GUIManager.class, Const.GUI_ITEM_LOADED, item.getName());
		}

	}

	public static Hashtable<String, GUIItem> getGUIList() {
		return GUIList;
	}

	public static void setGUIList(Hashtable<String, GUIItem> gUIList) {
		GUIList = gUIList;
	}

	public static void loadStage(Object caller, Stage stage, String name, boolean setVisible) throws ClassCastException {
		GUIItem item = GUIManager.getGUIList().get(name);
		Stage assignedStage = item.getStage();
		Scene scene = null;
		if(assignedStage != null) {
			stage = assignedStage;
		}
		else {
			if(!item.isScene()) {
				Sys.warnf(caller, Const.GUI_ITEM_IS_NOT_CONTAINER, item.getName());
				//throw new UnsupportedOperationException (Sys.format(Const.GUI_ITEM_IS_NOT_CONTAINER, item.getName()));
			}
			else {
				scene = new Scene ((Parent) item.getGUI(), item.getWidth(), item.getHeight());
				Sys.infof(caller, Const.SCENE_CREATED, item.getName(), item.getWidth(), item.getHeight());
			}
			if (scene == null) {
				Sys.errorf(caller, Const.SCENE_NOT_CREATED, item.getName());
			}
			if(item.getStylesheetResource() != null) {
				try {
					scene.getStylesheets().add(GUIManager.class.getResource(item.getStylesheetResource()).toExternalForm());
					Sys.infof(caller, Const.STYLESHEET_LOADED, item.getName(), item.getStylesheetResource());
				}
				catch (Exception e) {
					Sys.warnf(caller, Const.FAILED_TO_LOAD_STYLESHEET, item.getName(), item.getStylesheetResource());
				}
			}

			stage = stage == null ? new Stage(item.getStageStyle()) : stage;
			/*TODO stage visibility problem */
			//stage.close();

			/*TODO stage visibility problem */
			if(item.getTitle() != null && item.getLoader().getResources() != null) {
				stage.setTitle(item.getLoader().getResources().getString(item.getTitle()));
			}
			if(item.getIcon() != null) {
				try {
					stage.getIcons().add(new Image(GUIManager.class.getResourceAsStream(item.getIcon())));
					Sys.infof(caller, Const.ICON_LOADED, item.getName(), item.getIcon());
				}
				catch(Exception e) {
					Sys.warnf(caller, Const.FAILED_TO_LOAD_ICON, item.getName(), item.getIcon());
				}
			}
			stage.setScene(scene);
		}
		item.setStage(stage);
		Object guiController = item.getLoader().getController();
		if(guiController instanceof EmanagerControler) {
			((EmanagerControler) guiController).initialize(caller);
		}
		if(item.getModality() != null && !item.isPrimary() && !item.isVisible()) {
			stage.initModality(item.getModality());
			Sys.infof(caller, Const.MODALITY_SET, item.getName(), item.getModality());
		}
		if(item.isPrimary() && setVisible) {
			stage.show();
		}
		else if(setVisible) {
			if(item.getStartUp().equalsIgnoreCase(GUIItem.SHOW_STARTUP)) {
				stage.show();
			}
			else {
				stage.showAndWait();;
			}
			Sys.infof(caller, Const.STAGE_LOADED_AND_SHOWED, item.getName(), item.getStartUp().toUpperCase());
		}
		else {
			Sys.warnf(caller, Const.STAGE_LOADED_ONLY, item.getName());
		}
		/*if(item.isPrimary()) {
			stage.show();
		}
		else {
			stage.showAndWait();;
		}*/
		/*
		if(guiController instanceof EmanagerControler) {
			((EmanagerControler) guiController).initialize(GUIManager.getManager(), objs);
		}*/
		item.setVisible(setVisible);
		//stage.show();

	}
	public static GUIItem getGUI(String name) {
		return GUIManager.getGUIList().get(name);
	}
	public static GUIManager getManager() {
		return manager;
	}

	public static void setManager(GUIManager manager) {
		GUIManager.manager = manager;
	}

	public static void hideStage(Object caller, String name) {
		GUIManager.getGUI(name).getStage().hide();
		GUIManager.getGUI(name).setVisible(false);


	}

	public static void reloadStage(Object caller, String name) {
		GUIManager.getGUI(name).getStage().hide();
		GUIManager.loadStage(caller, null, name, true);;

	}

	/**
	 * @return the baseName
	 */
	public static String getBaseName() {
		return baseName;
	}

	/**
	 * @param baseName the baseName to set
	 */
	public static void setBaseName(String baseName) {
		GUIManager.baseName = baseName;
	}

	/**
	 * @return the resourceBundleClassName
	 */
	public static String getResourceBundleClassName() {
		return resourceBundleClassName;
	}

	/**
	 * @param resourceBundleClassName the resourceBundleClassName to set
	 */
	public static void setResourceBundleClassName(String resourceBundleClassName) {
		GUIManager.resourceBundleClassName = resourceBundleClassName;
	}

	/**
	 * @return the resourceBundleConttrolClassName
	 */
	public static String getResourceBundleConttrolClassName() {
		return resourceBundleConttrolClassName;
	}

	/**
	 * @param resourceBundleConttrolClassName the resourceBundleConttrolClassName to set
	 */
	public static void setResourceBundleConttrolClassName(String resourceBundleConttrolClassName) {
		GUIManager.resourceBundleConttrolClassName = resourceBundleConttrolClassName;
	}

	public static boolean operationAborted(boolean b) {
		boolean r =  GUIManager.operationAborted;
		GUIManager.operationAborted = b;
		return r;

	}

}
