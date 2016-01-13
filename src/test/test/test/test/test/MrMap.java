package test.test.test.test.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MrMap {

    public static void main(String[] args) {

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("pageSize", 1);
        list.add(map1);

        map1.put("pageSize", 2);
        list.add(map1);

//        System.out.println(map1.get("22") == null);
//        System.out.println(map1);

        Iterator<Map<String, Object>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> next = iterator.next();
            System.out.println(next.get("pageSize"));
        }

        System.out.println(list.get(0).get("pageSize").toString());
    }
}
