package baseApi;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import reader.PropertyReader;

public class BaseApi {
	protected static RequestSpecification requestSpec;

	public static void setRequestSpec() {

		requestSpec = RestAssured
				.given()
				.baseUri(PropertyReader.getProperty("BASE_URI"))
				.log()
				.all()
				.relaxedHTTPSValidation();// used to handle SSL Certifications

	}

}
