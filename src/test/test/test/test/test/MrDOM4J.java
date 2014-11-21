package test.test.test.test.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.xml.sax.SAXException;

public class MrDOM4J {

	public static void main(String[] args) throws DocumentException,
			SAXException, IOException {
		String filePath = "C:\\Users\\Administrator\\Desktop\\icbc_response.xml";
		// File responseFile = new File(filePath);
		System.out.println(read(filePath).getName());
		write(read(filePath),
				"C:\\Users\\Administrator\\Desktop\\icbc_response2.xml");
		System.out.println(getRootElement(read(filePath)).getName());

		// Iterator<Element> iterator = getRootElement(read(filePath))
		// .elementIterator();

		List<Element> list = getAllElement(read(filePath));
		Iterator<Element> iterator = list.iterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			System.out.println("node: " + e.getName() + ", value: "
					+ e.getText());
		}
	}

	public static List<Element> getAllElement(Document document) {
		if (document == null) {
			return null;
		}
		List<Element> list = new ArrayList<>();
		Element root = getRootElement(document);
		list.add(root);
		for (int i = 0; i < root.nodeCount(); i++) {
			Node node = root.node(i);
			System.out.println(node.getClass().getName());
			System.out.println(node.asXML());
		}
		recursionNode(root, list);
		return list;
	}

	private static void recursionNode(Element element, List<Element> list) {
		if (element.nodeCount() > 0) {
			for (int i = 0; i < element.nodeCount(); i++) {
				if(element.node(i) instanceof DefaultElement) {
					recursionNode((Element) element.node(i), list);
				}
			}
		} else {
			list.add(element);
		}

		// Node node = element.node(i);
		// if (node.hasContent()) {
		// recursionNode((Element) node, list);
		// } else {
		// list.add((Element) node);
		// }

		// if (node instanceof Element) {
		// recursionNode((Element) node, list);
		// } else {
		// list.add((Element) node);
		// }
	}

	/**
	 * get XML document
	 * 
	 * @author ma_qz
	 * @date 2014年11月11日 下午4:33:33
	 */
	public static Document read(String fileName) throws DocumentException {
		// DOMReader reader = new DOMReader();
		// Stream API for XML
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(fileName));
		return document;
	}

	/**
	 * get root element of XML
	 * 
	 * @author ma_qz
	 * @date 2014年11月11日 下午4:34:47
	 */
	public static Element getRootElement(Document document) {
		return document.getRootElement();
	}

	/**
	 * write XML
	 * 
	 * @author ma_qz
	 * @date 2014年11月11日 下午5:12:59
	 */
	public static void write(Document document, String filePath)
			throws SAXException, IOException {
		FileWriter out = new FileWriter(filePath);
		document.write(out);
	}
}
