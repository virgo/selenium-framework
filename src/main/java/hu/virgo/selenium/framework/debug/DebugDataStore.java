package hu.virgo.selenium.framework.debug;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class DebugDataStore {

	private Map<String, Object> store = new HashMap<String, Object>();

	public void put(String key, Object value) {
		store.put(key, value);
	}

	public String flush() {
		Gson gson = new Gson();
		return gson.toJson(store);
	}

}
