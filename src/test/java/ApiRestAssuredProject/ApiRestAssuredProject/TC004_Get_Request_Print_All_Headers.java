package ApiRestAssuredProject.ApiRestAssuredProject;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Get_Request_Print_All_Headers {
	
	@Test
	public void GetWeatherDetails() {
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
				Headers allheaders = response.headers();  //capture all headers from response
				
				
				//Assert.assertEquals(headers, "application/xml; charset=UTF-8");
				for(Header header:allheaders) {
					System.out.println("Headers are "+header.getName() +"   " +header.getValue());
					
				}
	}

}
