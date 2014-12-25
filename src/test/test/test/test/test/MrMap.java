package test.test.test.test.test;

import java.util.HashMap;
import java.util.Map;

public class MrMap {

	public static void main(String[] args) {
		Map<String, Object> session = new HashMap<>();
		session.put("", "");
		System.out.println(session.get("12") == null);
		System.out.println((String)session.get("2"));
	}
}
