package com.david.hashmap;

/**
 * @author David
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 1; i < 50; i++) {
            map.put("0" + i + "号", "0" + i);
        }
        map.print();
        System.out.println("--->" + map.get("01号"));

    }
}
