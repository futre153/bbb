package org.pabk.application.emanager.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

public class EManagerResourceBundleControl extends Control {

	@Override
	public String toBundleName(String baseName, Locale locale) {
		// TODO Auto-generated method stub
		String language = locale.getLanguage();
		baseName += ((language != null && language.length() > 0 ? new String(new char[]{Const.UNDERSCORE}) : Const.EMPTY) + language);
		return baseName.toLowerCase();
	}

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
		// TODO Auto-generated method stub
		 if (baseName == null || locale == null|| format == null || loader == null) {
			 throw new NullPointerException();
		 }
		 //System.out.println(locale);
		 ResourceBundle bundle = null;
		 String bundleName = toBundleName(baseName, locale);

		 //System.out.println(bundleName);
		 String resourceName = toResourceName(Const.ABSOLUTE_XML_RESOURCE_PATH + Const.SLASH + super.toBundleName(baseName, locale), Const.XML_SUFFIX);
		 //System.out.println(resourceName);

		 InputStream stream = null;
         if (reload) {
             URL url = loader.getResource(resourceName);
             if (url != null) {
                 URLConnection connection = url.openConnection();
                 if (connection != null) {
                     connection.setUseCaches(false);
                     stream = connection.getInputStream();
                 }
             }
         } else {
        	 System.out.println(resourceName);



             stream = EManagerResourceBundleControl.class.getResourceAsStream(resourceName);
         }
         if (stream != null) {
             BufferedInputStream bis = new BufferedInputStream(stream);
             bundle = new EManagerResourceBundle(bis, bundleName);
             bis.close();
         }
         return bundle;
	}

	@Override
	public List<String> getFormats(String baseName) {
		// TODO Auto-generated method stub
		if (baseName == null) {
			throw new NullPointerException();
		}
		return Arrays.asList(Const.EMANAGER_RESOURCE_BUNDLE_FORMAT);
	}

}
