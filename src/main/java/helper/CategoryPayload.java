package helper;

import model.Category;
import reader.JsonReader;

public class CategoryPayload {

	public static String getCategoryPayload(String fileName, String jsonNodeName) {
		Category category = JsonReader.deserialization(fileName, jsonNodeName, Category.class);
		return JsonReader.serialization(category);
	}

}
