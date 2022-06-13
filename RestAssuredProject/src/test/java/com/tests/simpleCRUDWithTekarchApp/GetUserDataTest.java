package com.tests.simpleCRUDWithTekarchApp;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;
import com.test.models.UserPOJO;
import com.tests.helpers.ReusableMethods;
import com.tests.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetUserDataTest extends UserServiceHelper {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = getBaseURI();
	}
	
	public static String loginToTekarchApi() {
		String token = getToken();
		return token;
	}
	
	@Test
	public static void testGetUserData() throws IOException {
		// convert json to string online https://tools.knowledgewalls.com/json-to-string
		String token = getToken();
		Header header =new Header("token",token);
		
		Response response = RestAssured.given().header(header)
//				.when().log().all()
				.when()
				.get(Endpoints.GET_DATA);
		
		response.body().prettyPrint();
	
		ReusableMethods.verifyStatusCodeis(response, 200);
		UserPOJO[] listOfUsers= response.as(UserPOJO[].class);//deserialization
		System.out.println("number of records="+ listOfUsers.length);
//		System.out.println("number of records="+response.jsonPath().get("$.size()"));
		String accountno = response.jsonPath().get("[0].accountno");
		String departmentno=response.jsonPath().get("[0].departmentno");
		String userId = response.jsonPath().get("[0].userid");
		String id=response.jsonPath().get("[0].id");
        System.out.println("first record account number= " + accountno);
        System.out.println("first record deartpment number= " + departmentno);
        System.out.println("first record userID= " + userId);
        System.out.println("first record Id= " + id);
	}
	
}
