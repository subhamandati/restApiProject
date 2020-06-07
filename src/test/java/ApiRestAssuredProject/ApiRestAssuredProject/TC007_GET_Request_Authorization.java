package ApiRestAssuredProject.ApiRestAssuredProject;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Authorization {
	@Test
	public void Authorization()
	{
		//specify base URI
				RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
				
				PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
				authScheme.setUserName("ToolsQA");
				authScheme.setPassword("TestPassword");
				RestAssured.authentication=authScheme;
				
				
				//Request Object
				RequestSpecification httprequest=RestAssured.given();
				
				//Response Object
				Response response=httprequest.request(Method.GET,"/");
				
				//Assert Status Code
				int statusCode = response.getStatusCode();
				System.out.println("StatusCode is "+statusCode);
				Assert.assertEquals(statusCode, 200);
				
				//Assert Status Line 
				String statusLine = response.getStatusLine();
				System.out.println("StatusLine is "+statusLine);
				Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
				
				//print response in console window
				String responseBody = response.getBody().asString();
				System.out.println("ResponseBody is" + responseBody);
	}

}
