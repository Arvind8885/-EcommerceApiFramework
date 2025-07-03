package helper;

import model.Image;
import model.Products;
import reader.JsonReader;

public class ProductPayload {

	public static Products getProductPayload(String fileName, String jsonNodeName) {

		Products products = JsonReader.deserialization(fileName, jsonNodeName, Products.class);

		return products;
	}

	public static String uplodImage(String fileName, String jsonNodeName) {
		Image image = JsonReader.deserialization(fileName, jsonNodeName, Image.class);

		return JsonReader.serialization(image);
	}

}
