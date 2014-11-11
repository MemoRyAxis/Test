package test.test.test.test.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @TODO
 * @author ma_qz
 */
public class MrXML {

	private String xmlStr;
	private Map<String, Object> xmlMap;

	public MrXML() {
		xmlStr = "<?xml version=\"1.0\" encoding=\"gbk\"?>";
		xmlMap = new HashMap<>();
	}

	public String getXml() {
		return xmlStr;
	}

	public String getFormatXml() {
		for (int i = 0; i < xmlMap.size(); i++) {
			
		}
		return null;
	}

	public void addNode(String node, Object value) {
		// TODO stack
		String[] family = node.split("/");
		int deep = family.length;
		for (int i = 1; i < deep; i++) {
			xmlStr += "<" + family[i] + ">";
			xmlMap.put(family[i], "");
		}
		xmlStr += value;
		for (int i = 1; i < deep; i++) {
			xmlStr += "</" + family[deep - i] + ">";
		}

		xmlMap.put(node, value);
	}

	/**
	 * @TODO
	 * @author ma_qz
	 */
	class XmlNode {
		private String node;
		private Object value;
		private Map<String, Object> attr;
		public String getNode() {
			return node;
		}
		public void setNode(String node) {
			this.node = node;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public Map<String, Object> getAttr() {
			return attr;
		}
		public void setAttr(Map<String, Object> attr) {
			this.attr = attr;
		}
		
	}

	/**
	 * @param args
	 * @author ma_qz
	 * @date 2014年11月11日 上午10:52:29
	 */
	public static void main(String[] args) {
		String xmlString = "<a><content>content</content>";
		String result = xmlString;
		result = result.replace("<content>", "<0.0>");
		System.out.println(xmlString);
		System.out.println(result);
		result = result.replace("</content>", "<0.0>");
		System.out.println(xmlString);
		System.out.println(result);
		System.out.println(result.split("<0.0>")[1] + "\n\n");

		MrXML xml = new MrXML();
		xml.addNode("/MsgText/GrpHdr/Version", "1.0.0");
		xml.addNode("/MsgText/GrpHdr/Version2", "2.0.0");
		System.out.println(xml.getXml());
	}
}
