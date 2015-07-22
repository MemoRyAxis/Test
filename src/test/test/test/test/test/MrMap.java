package test.test.test.test.test;

import java.util.HashMap;
import java.util.Map;

public class MrMap {

	public static void main(String[] args) {
		
		Map<String, Object> map1 = new HashMap<>();
		
		map1.put("pageSize", 1);
		System.out.println(map1.get("22") == null);
		System.out.println(map1);
	}
}
