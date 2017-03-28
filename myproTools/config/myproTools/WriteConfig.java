package myproTools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WriteConfig {
	Document doc = null;

	public String configHistoryResultWrite(String id, String time, String command, String content) {
		String a = "";
		doc = readConfig.getConfig().getDoc();
		NodeList nds = doc.getElementsByTagName("object");
		for (int i = 0; i < nds.getLength(); i++) {
			if (nds.item(i).getNodeType() == 1) {
				Element rootNode = (Element) nds.item(i);

				Element newElement = doc.createElement("result");
				newElement.setAttribute("id", id);
				newElement.setAttribute("command", time);
				newElement.setAttribute("time", command);
				newElement.setTextContent(content);
				rootNode.appendChild(newElement);

				TransformerFactory tff = TransformerFactory.newInstance();
				Transformer transformer = null;
				try {
					transformer = tff.newTransformer();
				} catch (TransformerConfigurationException e1) {
					e1.printStackTrace();
				}
				DOMSource source = new DOMSource(doc);

				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new FileOutputStream(readConfig.CONFIG));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				StreamResult result = new StreamResult(pw);
				try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				pw.close();
			}
		}
		return a;

	}
}
