package test.test.test.test.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MrList {

	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(1);
		System.out.println(list.size());
		Set<Object> set = new HashSet<Object>(list);
		System.out.println(set.size());
		
		list.add("1");
		System.out.println(list.get(0));
	}
}
