package ApiRestAssuredProject.ApiRestAssuredProject;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_Validationg_JSON_Response {
	@Test
	void getWeatherDetails() {
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response Object
		Response response=httprequest.request(Method.GET,"/Delhi");
		
		//print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("ResponseBody is" + responseBody);
		
		
		Assert.assertEquals(responseBody.contains("Delhi"), true);
	}


}
