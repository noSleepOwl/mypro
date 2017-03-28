package myproTools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class readConfig {
	private readConfig() {

	}

	private static readConfig rc = null;
	private static Document doc = null;
	public static final String CONFIG = "O:\\orcaldriver\\myproTools\\config\\myproTools\\url.xml";

	public static readConfig getConfig() {
		if (rc == null) {
			rc = new readConfig();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				doc = documentBuilder.parse(new FileInputStream(CONFIG));

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rc;
	}

	public Document getDoc() {
		return doc;
	}

	public String getAllUrl() {
		NodeList nodelist = doc.getElementsByTagName("url");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Element element = (Element) nodelist.item(i);
			String name = element.getAttribute("name");
			String id = element.getAttribute("id");
			String talk = element.getAttribute("talk");
			String content = element.getTextContent();
			buffer.append("名称:\t" + name.trim() + "\n");
			buffer.append("指令:\t" + id.trim() + "\n");
			buffer.append("说明:\t" + talk.trim() + "\n");
			buffer.append("地址:\t" + content.trim() + "\n");
			buffer.append("------------------------------------\n");
		}
		return buffer.toString();
	}

	public String getBaseJspUrl() {
		Element ele = doc.getElementById("ojb_1_url_3");
		System.out.println(ele);
		return ele.getTextContent().trim();
	}

}
