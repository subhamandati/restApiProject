package ApiRestAssuredProject.ApiRestAssuredProject;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Post_Request {

	@Test
	void RegistrationSuccessful() {
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		
		//Request Object
		RequestSpecification httprequest=RestAssured.given();
		
		//Request Payload sending along with POST request
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName","11JohnXYZafd");
		requestParams.put("LastName","11XYZJohnaffd");
		requestParams.put("UserName","11JohnXYZafd");
		requestParams.put("Password","11JohnXYZxyzafd");
		requestParams.put("Email","11JohnXYZafd@gmail.com");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(requestParams.toJSONString()); //Attach above data to the request
		
		
		//Response Object
		Response response=httprequest.request(Method.POST,"/register");
		
		//print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("ResponseBody is" + responseBody);
		
		//Assert Status Code
		int statusCode = response.getStatusCode();
		System.out.println("StatusCode is "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//Assert Status Line 
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("successCode is "+successCode);
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
	}
}
