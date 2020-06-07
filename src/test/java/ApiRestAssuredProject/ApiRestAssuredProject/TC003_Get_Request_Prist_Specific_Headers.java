package ApiRestAssuredProject.ApiRestAssuredProject;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Get_Request_Prist_Specific_Headers {
	@Test
	void googleMapTest() {
		
		//specify base URI
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Request Object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response Object
		Response response=httprequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
		
		//print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("ResponseBody is" + responseBody);
		
		//validating Headers
		String contentType = response.header("Content-Type"); //Capture detail of Content-Type Header from response 
		System.out.println("contentType is "+contentType);
		Assert.assertEquals(contentType, "application/xml; charset=UTF-8");
		
		String contentEncoding=response.header("Content-Encoding");// capture details of Content-Encoding  header
		System.out.println("Content Encoding is:"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
	}
}
