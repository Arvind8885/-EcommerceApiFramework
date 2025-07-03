package helper;

import model.CartItem;
import reader.JsonReader;

public class AddToCartPayload {

	public static CartItem getRequestPayload(String fileName, String jsonNodename) {
		return JsonReader.deserialization(fileName, jsonNodename, CartItem.class);

	}

}
