Feature: Test End to End Medicare Apis

  Background: 
    Given set a base uri

  # Register ADMIN Account
  Scenario: register Admin user using Post Request
    Given add content type header
    And user add request payload from "adminPayload" file and from "adminAccount" json payload
    When user select sign up post request
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    And user verify email value should not be null
    And user verify role value should not be null
    And user verify first should not be null
    And user verify content type header
    And user generate response logs

  #Login Using ADMIN Credentails
  Scenario: Login admin user using post request
    Given add content type header
    And user add sign payfrom from "adminPayload" file and from "adminAccount" json payload
    When user select sign in post request
    Then user get validatable response object
    And user verify status code as 200
    And user verify status line contains "200"
    And user verify email value should not be null
    And user verify role value should not be null
    And user verify first should not be null
    And user verify token should not be null
    And user verify content type header
    And user generate response logs
    And user capture "ADMIN" access token from response payload

  # Add Category using POST Request
  Scenario: add category using Admin account and using Post Request
    Given add content type header
    And user add "ADMIN" authentication token
    And user add category request payload from "adminPayload" file and from "addCategory" json payload
    When user select category post request
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    And user verify categoryName value should not be null
    And user verify description value should not be null
    And user verify content type header
    And user generate response logs
    And user capture category id
    And user capture category name

  # GET Category Using GET Request
  Scenario: get category using Admin account and using GET Request
    And user add "ADMIN" authentication token
    When user select category get request
    Then user get validatable response object
    And user verify status code as 200
    And user verify status line contains "200"
    And user verify categoryName value should not be null
    And user verify description value should not be null
    And user verify content type header
    And user generate response logs

  # Add Product Using POST Request
  Scenario: add product using Admin account and using Post Request
    Given add content type header
    And user add "ADMIN" authentication token
    And user add product request payload from "adminPayload" file and from "addProducts" json payload
    When user select products post request
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    And user verify name value should not be null
    And user verify content type header
    And user generate response logs
    And user capture product id

  # GET Product using GET Request
  Scenario: get product using Admin account and using get Request
    And user add "ADMIN" authentication token
    And user add path parameter to get a single created products
    When user get single products get request
    Then user get validatable response object
    And user verify status code as 200
    And user verify status line contains "200"
    And user verify name value should not be null
    And user verify content type header
    And user generate response logs

  # Update Product using PUT Request
  Scenario: add product using Admin account and using Post Request
    Given add content type header
    And user add path parameter to get a single created products
    And user add "ADMIN" authentication token
    And user add product request payload from "adminPayload" file and from "updateProducts" json payload
    When user select put request to update products
    Then user get validatable response object
    And user verify status code as 200
    And user verify status line contains "200"
    # And user verify name value should not be null
    #And user verify content type header
    And user generate response logs

  # Upload Product image Using POST Request
  Scenario: Upload Product image using POST Request
    Given user add path parameter to get a single created products
    And user add "ADMIN" authentication token
    And user upload image
    When user select post request to upload products image
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    #And user verify name value should not be null
    #And user verify content type header
    And user generate response logs

  # GET Product image Using GET Request
  Scenario: get Product image using GET Request
    Given user add path parameter to get a single created products
    And user add "ADMIN" authentication token
    When user select get request to get products image
    Then user get validatable response object
    And user verify status code as 200
    And user verify status line contains "200"
    #And user verify name value should not be null
    #And user verify content type header
    And user generate response logs

  # GET Product from Categort Using GET Request
  Scenario: get Product from catgory using GET Request
    And user add path parameter to get products as per category id
    And user add "ADMIN" authentication token
    When user select get request to get products from category
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    #And user verify name value should not be null
    #And user verify content type header
    And user generate response logs

  ################################################# CUSTOMER APIs ############################################
  # Register Customer Account
  Scenario: Register Admin user using Post Request
    Given add content type header
    And user add request payload from "customerPayload" file and from "customerAccount" json payload
    When user select sign up post request
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    And user verify email value should not be null
    And user verify role value should not be null
    And user verify first should not be null
    And user verify content type header
    And user generate response logs

  #Login Using ADMIN Credentails
  Scenario: Login CUSTOMER user using post request
    Given add content type header
    And user add sign payfrom from "customerPayload" file and from "customerAccount" json payload
    When user select sign in post request
    Then user get validatable response object
    And user verify status code as 200
    And user verify status line contains "200"
    And user verify email value should not be null
    And user verify role value should not be null
    And user verify first should not be null
    And user verify token should not be null
    And user verify content type header
    And user generate response logs
    And user capture customer user id
    And user capture "CUSTOMER" access token from response payload

  # Add to Cart using POST Request
  Scenario: Customer add product to add to cart using POST Request
    Given add content type header
    And user add "CUSTOMER" authentication token
    And customer add product to cart payfrom from "customerPayload" file and from "addToCart" json payload
    When customer add product to cart using post request
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    And user verify quantity value should not be null
    #And user verify userId value should not be null
    And user verify productId value should not be null
    And user verify content type header
    And user generate response logs
    And user get add cart item id

  # get add to Cart Item using GET Request
  Scenario: Customer get product from add to cart using GET Request
    And user add "CUSTOMER" authentication token
    And user add customer add to cart path parameter
    When customer get product from cart using get request
    Then user get validatable response object
    And user verify status code as 201
    And user verify status line contains "201"
    #And user verify quantity value should not be null
    #And user verify userId value should not be null
    # And user verify productId value should not be null
    #And user verify content type header
    And user generate response logs

  # delete  Cart Item using DELETE Request
  @DeleteCartItem
  Scenario: Customer delete product from add to cart using DELETE Request
    And user add "CUSTOMER" authentication token
    And user add customer add to cart path parameter
    When customer delete product from cart using delete request
    Then user get validatable response object
    And user verify status code as 204
    And user verify status line contains "204"
    #And user verify quantity value should not be null
    #And user verify userId value should not be null
    # And user verify productId value should not be null
    #And user verify content type header
    And user generate response logs

  # GET Cart Item as per User Id
  Scenario: custome get cart item as per user id
    And user add "CUSTOMER" authentication token
    And customer add user id as path parameter
    When customer select get request to get cart item
    Then user get validatable response object
    And user verify status code as 404
    And user verify status line contains "404"
    And user generate response logs

    
    
    
    
    
    
    
    
    
    
    
    