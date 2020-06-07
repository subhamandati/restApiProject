package datadriventesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployee {

	private static final String String = null;
	@Test(dataProvider="empadataprovider")
	void postNewEmployees(String ename, String esal, String eage) {

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();

		//We are recreated data which we can send along with the post request
		JSONObject requestParams = new JSONObject();

		requestParams.put("name",ename);
		requestParams.put("salary",esal);
		requestParams.put("age",eage);

		//add header stating the request body is JSON
		httpRequest.header("Content-Type","application/json");

		//Add the json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		//POST Request
		Response response = httpRequest.request(Method.POST,"/create");

		//capture the response body to perform validations
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);

		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		Assert.assertEquals(responseBody.contains(eage), true);


		//		int statusCode = response.getStatusCode();

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);	

	}
	@DataProvider(name="empadataprovider")
	String[][] getEmpData() throws Exception{
		//Read data from Excel
		String path = System.getProperty("user.dir")+"/src/test/java/datadriventesting/empData.xlsx";
		ExcelApi ExcelApi = new ExcelApi(path);
		int rowCount = ExcelApi.getRowCount("Sheet1");
		int colCount = ExcelApi.getColumnCount("Sheet1");
		
		String empData[][] = new String[rowCount][colCount];
		for(int i=0;i<rowCount;i++)
		{
			for(int j=0;j<colCount;j++) {
				empData[i-1][j]=ExcelApi.getCellData("Sheet1",i,j);
			}
		}
		
		//String empData[][]= {{"abc","1000","23"},{"abc1","2000","24"},{"abc2","1500","26"}};
		return(empData);
	}
}
