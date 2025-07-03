package steps;

import java.io.File;
import java.util.HashMap;

import org.hamcrest.Matchers;

import baseApi.BaseApi;
import endpoints.Category;
import endpoints.SignUpAndSignIn;
import helper.AddToCartPayload;
import helper.CategoryPayload;
import helper.ProductPayload;
import helper.SignUpAndSignInPayload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.CartItem;
import model.Products;
import reader.JsonReader;

public class MedicareApiSteps extends BaseApi {

	static long productId;
	static long userId;
	public static Long categoryid;
	static long cartItemId;

	public static HashMap<String, Long> categoryNameAndId = new HashMap<String, Long>();

	static HashMap<String, String> allAccessToken = new HashMap<String, String>();

	Response response;
	ValidatableResponse validateResponse;

	@Given("set a base uri")
	public void set_a_base_uri() {
		setRequestSpec();
	}

	@Given("add content type header")
	public void add_content_type_header() {
		requestSpec.contentType(ContentType.JSON);
	}

	@Given("user add request payload from {string} file and from {string} json payload")
	public void user_add_request_payload_from_file_and_from_json_payload(String fileName, String nodeName) {

		String requestPayload = SignUpAndSignInPayload.getSignUpPayload(fileName, nodeName);

		requestSpec.body(requestPayload);

	}

	@When("user select sign up post request")
	public void user_select_sign_up_post_request() {

		response = requestSpec.post(SignUpAndSignIn.signUp);

	}

	@Then("user get validatable response object")
	public void user_get_validatable_response_object() {

		validateResponse = response.then().assertThat();

	}

	@Then("user verify status code as {int}")
	public void user_verify_status_code_as(Integer code) {

		validateResponse.statusCode(Matchers.equalTo(code));

	}

	@Then("user verify status line contains {string}")
	public void user_verify_status_line_contains(String line) {

		validateResponse.statusLine(Matchers.containsString(line));
	}

	@Then("user verify email value should not be null")
	public void user_verify_email_value_should_not_be_null() {
		validateResponse.body("email", Matchers.notNullValue());

	}

	@Then("user verify role value should not be null")
	public void user_verify_role_value_should_not_be_null() {
		validateResponse.body("role", Matchers.notNullValue());

	}

	@Then("user verify first should not be null")
	public void user_verify_first_should_not_be_null() {
		validateResponse.body("firstName", Matchers.notNullValue());

	}

	@Then("user verify content type header")
	public void user_verify_content_type_header() {
		validateResponse.header("Content-Type", Matchers.containsString("application/json"));
	}

	@Then("user generate response logs")
	public void user_generate_response_logs() {
		validateResponse.log().all();
	}

	@Given("user add sign payfrom from {string} file and from {string} json payload")
	public void user_add_sign_payfrom_from_file_and_from_json_payload(String filename, String jsonNodeName) {

		String requestPayload = SignUpAndSignInPayload.getSignUpPayload(filename, jsonNodeName);

		requestSpec.body(requestPayload);
	}

	@When("user select sign in post request")
	public void user_select_sign_in_post_request() {

		response = requestSpec.post(SignUpAndSignIn.signIn);

	}

	@Then("user verify token should not be null")
	public void user_verify_token_should_not_be_null() {

		validateResponse.body("token", Matchers.notNullValue());
	}

	@Then("user capture {string} access token from response payload")
	public void user_capture_access_token_from_response_payload(String roleName) {

		String token = response.jsonPath().getString("token");

		allAccessToken.put(roleName, token);
	}

	@Given("user add {string} authentication token")
	public void user_add_authentication_token(String roleName) {

		requestSpec.header("Authorization", "Bearer " + allAccessToken.get(roleName));

	}

	@Given("user add category request payload from {string} file and from {string} json payload")
	public void user_add_category_request_payload_from_file_and_from_json_payload(String fileName,
			String jsonNodename) {

		String requestPayload = CategoryPayload.getCategoryPayload(fileName, jsonNodename);

		requestSpec.body(requestPayload);

	}

	@When("user select category post request")
	public void user_select_category_post_request() {

		response = requestSpec.post(endpoints.Category.addCategory);

	}

	@Then("user verify categoryName value should not be null")
	public void user_verify_category_name_value_should_not_be_null() {

		validateResponse.body("categoryName", Matchers.notNullValue());

	}

	@Then("user verify description value should not be null")
	public void user_verify_description_value_should_not_be_null() {

		validateResponse.body("description", Matchers.notNullValue());

	}

	@When("user select category get request")
	public void user_select_category_get_request() {

		response = requestSpec.get(Category.getCategory);

	}

	@Then("user capture category id")
	public void user_capture_category_id() {

		categoryid = response.jsonPath().getLong("id");
	}

	@Then("user capture category name")
	public void user_capture_category_name() {

		String categoryName = response.jsonPath().getString("categoryName");

		categoryNameAndId.put(categoryName, categoryid);

	}

	@Given("user add product request payload from {string} file and from {string} json payload")
	public void user_add_product_request_payload_from_file_and_from_json_payload(String fileName, String nodename) {

		Products products = ProductPayload.getProductPayload(fileName, nodename);

		products.setCategoryId(categoryNameAndId.get("Mobile"));

		String requestPayload = JsonReader.serialization(products);

		requestSpec.body(requestPayload);

	}

	@When("user select products post request")
	public void user_select_products_post_request() {

		response = requestSpec.post(endpoints.Products.addProducts);

	}

	@Then("user verify name value should not be null")
	public void user_verify_name_value_should_not_be_null() {

		validateResponse.body("name", Matchers.notNullValue());

	}

	@Then("user capture product id")
	public void user_capture_product_id() {

		productId = response.jsonPath().getInt("id");
		System.out.println(productId);
	}

	@Given("user add path parameter to get a single created products")
	public void user_add_path_parameter_to_get_a_single_created_products() {

		requestSpec.pathParam("productId", productId);

	}

	@When("user get single products get request")
	public void user_get_single_products_get_request() {

		response = requestSpec.get(endpoints.Products.getProductsAsPerPId);

	}

	@When("user select put request to update products")
	public void user_select_put_request_to_update_products() {

		response = requestSpec.put(endpoints.Products.updateProductsAsPerPId);

	}

	@Given("user upload image")
	public void user_upload_image() {

		File file = new File(System.getProperty("user.dir") + "/Images/Iphone 14 Pro.jpeg");

		System.out.println(file.exists());

		// requestSpec.multiPart(file);

		requestSpec.multiPart("imageFile", file);

//		requestSpec.body(ProductPayload.uplodImage(fileName, nodeName));

	}

	@When("user select post request to upload products image")
	public void user_select_post_request_to_upload_products_image() {

		response = requestSpec.post(endpoints.Products.addProductIdImage);

	}

	@When("user select get request to get products image")
	public void user_select_get_request_to_get_products_image() {

		response = requestSpec.get(endpoints.Products.getProductIdImage);

	}

	@Given("user add path parameter to get products as per category id")
	public void user_add_path_parameter_to_get_products_as_per_category_id() {

		requestSpec.pathParam("categoryId", categoryid);
	}

	@When("user select get request to get products from category")
	public void user_select_get_request_to_get_products_from_category() {

		response = requestSpec.get(endpoints.Products.getProductAsPerCategoryId);
	}

	@Then("user capture customer user id")
	public void user_capture_customer_user_id() {
		userId = response.jsonPath().getLong("id");

	}

	@Given("customer add product to cart payfrom from {string} file and from {string} json payload")
	public void customer_add_product_to_cart_payfrom_from_file_and_from_json_payload(String fileName, String nodeName) {

		CartItem cartItem = AddToCartPayload.getRequestPayload(fileName, nodeName);

		cartItem.setProductId(productId);

		cartItem.setUserId(userId);

		String requestPayload = JsonReader.serialization(cartItem);

		requestSpec.body(requestPayload);

	}

	@When("customer add product to cart using post request")
	public void customer_add_product_to_cart_using_post_request() {

		response = requestSpec.post(endpoints.CartItem.addProductToCart);

	}

	@Then("user verify quantity value should not be null")
	public void user_verify_quantity_value_should_not_be_null() {

		validateResponse.body("quantity", Matchers.notNullValue());
	}

	@Then("user verify productId value should not be null")
	public void user_verify_product_id_value_should_not_be_null() {

		validateResponse.body("productId.id", Matchers.notNullValue());

	}

	@Then("user get add cart item id")
	public void user_get_cart_item_id() {
		cartItemId = response.jsonPath().getLong("id");

	}

	@Given("user add customer add to cart path parameter")
	public void user_add_customer_add_to_cart_path_parameter() {

		requestSpec.pathParam("cartItemId", cartItemId);
	}

	@When("customer get product from cart using get request")
	public void customer_get_product_from_cart_using_get_request() {

		response = requestSpec.get(endpoints.CartItem.getCartItemProduct);

	}

	@When("customer delete product from cart using delete request")
	public void customer_delete_product_from_cart_using_delete_request() {

		response = requestSpec.delete(endpoints.CartItem.deleteCartItemProduct);

	}

	@Given("customer add user id as path parameter")
	public void customer_add_user_id_as_path_parameter() {

		requestSpec.pathParam("userId", userId );
	}

	@When("customer select get request to get cart item")
	public void customer_select_get_request_to_get_cart_item() {

		response = requestSpec.get(endpoints.AdminCart.getCartItemAsPerUserId);
	}

}
