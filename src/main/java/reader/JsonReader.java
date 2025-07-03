package reader;

import java.io.FileReader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	public static <T> T deserialization(String fileName, String jsonNodeName, Class<T> className) {
		String path = System.getProperty("user.dir") + "/src/test/resources/requestPayload/" + fileName + ".json";

		ObjectMapper mapper = new ObjectMapper();

		T t = null;
		try {

			FileReader file = new FileReader(path);

			JsonNode jsonNode = mapper.readTree(file);

			t = mapper.treeToValue(jsonNode.get(jsonNodeName), className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static String serialization(Object className) {
		ObjectMapper mapper = new ObjectMapper();
		String requestPayload = null;

		try {
			requestPayload = mapper.writeValueAsString(className);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestPayload;
	}

}
