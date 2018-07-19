package org.pabk.application.emanager.module;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.xml.parsers.ParserConfigurationException;

import org.pabk.application.emanager.util.Const;
import org.pabk.application.emanager.util.GUIItem;
import org.pabk.application.emanager.util.Sys;
import org.pabk.application.emanager.util.XMLSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import ch.qos.logback.classic.Logger;
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

	public static void loadGUIList(String guiResource) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Document document = XMLSupport.loadXMLFile(guiResource);
		NamedNodeMap attributes = document.getDocumentElement().getAttributes();
		Node attribute = attributes.getNamedItem(Const.GUI_LANGUAGE_ATTRIBUTE_NAME);
		String language = attribute.getNodeValue();
		language = language == null || language.length() == 0 ? Const.GUI_DEFAULT_LANGUAGE: language;
		GUIManager.setLocale (Sys.getLocale(language));
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
		ArrayList<Element> items = XMLSupport.getChildrenElementsByTagName(document.getDocumentElement(), Const.GUI_ITEM_TAGNAME);
		for(int i = 0; i < items.size(); i ++) {
			ArrayList<Element> elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_NAME_TAGNAME);
			if(elements.size() == 0) {
				throw new IOException(Const.GUI_ITEM_NAME_NULL);
			}
			String name = elements.get(0).getTextContent();
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_FXML_TAGNAME);
			if(elements.size() == 0) {
				throw new IOException(Const.GUI_ITEM_FXML_NULL);
			}
			String fxml = elements.get(0).getTextContent();
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_STYLESHEET_TAGNAME);
			String stylesheet  = null;
			if(elements.size() != 0) {
				stylesheet = elements.get(0).getTextContent();
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_CONTROLLER_TAGNAME);
			String controLLer  = null;
			if(elements.size() != 0) {
				controLLer = elements.get(0).getTextContent();
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_DESCRIPTION_TAGNAME);
			String description  = null;
			if(elements.size() != 0) {
				description = elements.get(0).getTextContent();
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_TITLE_TAGNAME);
			String title  = null;
			if(elements.size() != 0) {
				title = elements.get(0).getTextContent();
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_STAGE_STYLE_TAGNAME);
			String stageStyle  = null;
			if(elements.size() != 0) {
				stageStyle = elements.get(0).getTextContent();
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_ICON_TAGNAME);
			String icon  = null;
			if(elements.size() != 0) {
				icon = elements.get(0).getTextContent();
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_WIDTH_TAGNAME);
			double width  = 0;
			if(elements.size() != 0) {
				width = Double.parseDouble(elements.get(0).getTextContent());
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_HIGHT_TAGNAME);
			double height  = 0;
			if(elements.size() != 0) {
				height = Double.parseDouble(elements.get(0).getTextContent());
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_SCENE_TAGNAME);
			boolean scene  = false;
			if(elements.size() != 0) {
				scene = Boolean.parseBoolean(elements.get(0).getTextContent());
			}
			else {
				throw new IOException(Const.GUI_ITEM_SCENE_NULL);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_PRIMARY_TAGNAME);
			boolean primary  = false;
			if(elements.size() != 0) {
				primary = Boolean.parseBoolean(elements.get(0).getTextContent());
			}
			else {
				throw new IOException(Const.GUI_ITEM_PRIMARY_NULL);
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_MODALITY_TAGNAME);
			String modality  = null;
			if(elements.size() != 0) {
				modality = elements.get(0).getTextContent();
			}
			elements = XMLSupport.getChildrenElementsByTagName(items.get(i), Const.GUI_ITEM_STARTUP_TAGNAME);
			String startup  = null;
			if(elements.size() != 0) {
				startup = elements.get(0).getTextContent();
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
			Sys.info(GUIManager.class, Const.GUI_ITEM_LOADED, item.getName());
		}

	}

	public static Hashtable<String, GUIItem> getGUIList() {
		return GUIList;
	}

	public static void setGUIList(Hashtable<String, GUIItem> gUIList) {
		GUIList = gUIList;
	}

	public static void loadStage(Object caller, Stage stage, String name, boolean setVisible) throws ClassCastException {
		//Object[] objs = (Object[]) conf;
		//System.err.println("XXX   " + objs.length);
		GUIItem item = GUIManager.getGUIList().get(name);
		//Logger logger = GUIManager.getManager().getLogger();
		//logger = logger == null ? Sys.getLogger() : logger;
		Stage assignedStage = item.getStage();
		//PrintStream ps = logger == null ? System.out : null;
		Scene scene = null;
		if(assignedStage != null) {
			stage = assignedStage;
		}
		else {
			if(!item.isScene()) {
				Sys.warn(caller, Const.GUI_ITEM_IS_NOT_CONTAINER, item.getName());
			}
			else {
				scene = new Scene ((Parent) item.getGUI(), item.getWidth(), item.getHeight());
				Sys.info(caller, Const.SCENE_CREATED, item.getName(), item.getWidth(), item.getHeight());
			}
			if (scene == null) {
				Sys.error(caller, Const.SCENE_NOT_CREATED, item.getName());
			}
			if(item.getStylesheetResource() != null) {
				try {
					scene.getStylesheets().add(GUIManager.class.getResource(item.getStylesheetResource()).toExternalForm());
					Sys.info(caller, Const.STYLESHEET_LOADED, item.getName(), item.getStylesheetResource());
				}
				catch (Exception e) {
					Sys.warn(caller, Const.FAILED_TO_LOAD_STYLESHEET, item.getName(), item.getStylesheetResource());
				}
			}

			stage = stage == null ? new Stage(item.getStageStyle()) : stage;
			/*TODO stage visibility problem */
			//stage.close();

			/*TODO stage visibility problem */
			if(item.getTitle() != null) {
				stage.setTitle(item.getTitle());
			}
			if(item.getIcon() != null) {
				try {
					stage.getIcons().add(new Image(GUIManager.class.getResourceAsStream(item.getIcon())));
					Sys.info(caller, Const.ICON_LOADED, item.getName(), item.getIcon());
				}
				catch(Exception e) {
					e.printStackTrace();
					Sys.warn(caller, Const.FAILED_TO_LOAD_ICON, item.getName(), item.getIcon());
				}
			}
			stage.setScene(scene);
		}
		item.setStage(stage);
		Object guiController = item.getLoader().getController();
		if(item.getModality() != null && !item.isPrimary() && !item.isVisible()) {
			stage.initModality(item.getModality());
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

}
