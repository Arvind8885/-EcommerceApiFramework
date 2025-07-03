package reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	private static String path = System.getProperty("user.dir") + "/src/main/resources/config.properties";

	public static String getProperty(String keyName) {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(keyName);
	}

}
