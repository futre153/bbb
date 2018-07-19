package org.pabk.application.emanager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLSupport {

	private static final String NODE_NOT_ELEMENT = "Node %s is not an element.";

	public static Document loadXMLFile(String xmlPath) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		File f = new File (xmlPath);
		System.out.println(XMLSupport.class.getResource(xmlPath));
		InputStream in = f.exists() ? new FileInputStream(f) : XMLSupport.class.getResourceAsStream(xmlPath);
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
	}

	public static ArrayList<Element> getChildrenElementsByTagName(Node node, String tagName) throws SAXException {
		ArrayList<Element> list = new ArrayList<Element>();
		if(node.getNodeType() != Node.ELEMENT_NODE) {
			throw new SAXException(String.format(NODE_NOT_ELEMENT,node));
		}
	NodeList nodelist = node.getChildNodes();
	for  (int i = 0; i < nodelist.getLength(); i ++) {
		Node child = nodelist.item(i);
		if (child.getNodeType() == Node.ELEMENT_NODE && ((Element) child).getTagName().equals(tagName)) {
			list.add((Element) child);
		}
	}
	return list;
	}

}
