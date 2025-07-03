package helper;

import model.SignIn;
import model.SignUp;
import reader.JsonReader;

public class SignUpAndSignInPayload {

	public static String getSignUpPayload(String fileName, String jsonNodeName) 
	{

		SignUp signUp = JsonReader.deserialization(fileName, jsonNodeName, SignUp.class);

		return JsonReader.serialization(signUp);
	}

	
	public static String getSignInPayload(String fileName, String jsonNodeName) 
	{
		SignUp signUp = JsonReader.deserialization(fileName, jsonNodeName, SignUp.class);
		
		//capture email and password
		String email = signUp.getEmail();
		String password = signUp.getPassword();
		
		SignIn signIn=new SignIn();
		
		signIn.setEmail(email);
		signIn.setPassword(password);
		
		return JsonReader.serialization(signIn);
	}
}
