package app;

import java.util.HashMap;
import java.util.Map;

/**
 * An example class to show HashMaps usage.
 * 
 * @author pahlz
 *
 */
public class PlayMap {

	public static void main(String[] args) {
		Map<Integer, Integer> integerMap = new HashMap<Integer, Integer>();
		integerMap.put(1, 1);
		integerMap.put(2, 2);

		Map<Integer, String> stringMap = new HashMap<Integer, String>();
		stringMap.put(1, "One");
		stringMap.put(2, "Two");

		Map<String, String> namegMap = new HashMap<String, String>();
		namegMap.put("FirstName", "Zach");
		namegMap.put("LastName", "Pahl");

		System.out.printf("Name Map Tests: size is %d and is empty %b\n", namegMap.size(), namegMap.isEmpty());

		for (Map.Entry<String, String> m : namegMap.entrySet()) {
			System.out.printf("Key: %s Value: %s\n", m.getKey(), m.getValue());
		}

		integerMap.clear();
		stringMap.remove(1);
		stringMap.clear();
		namegMap.clear();
	}

}
