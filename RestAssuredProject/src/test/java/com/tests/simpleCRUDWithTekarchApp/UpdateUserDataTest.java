package com.tests.simpleCRUDWithTekarchApp;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;
import com.test.models.UpdateUserPOJO;
import com.tests.helpers.ReusableMethods;
import com.tests.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UpdateUserDataTest extends UserServiceHelper {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = getBaseURI();
	}
	
	public static String loginToTekarchApi() {
		String token = getToken();
		return token;
	}
	
	@Test
	public static void testUpdateUserData() throws IOException {
		// convert json to string online https://tools.knowledgewalls.com/json-to-string
		UpdateUserPOJO uu = ReusableMethods.getUserDataToUpdate();
		String token = getToken();
		Header header =new Header("token",token);
		
		Response response = RestAssured.given()
				.contentType("application/json")
				.header(header)
				.body(uu)
//				.when().log().all()
				.when()
				.put(Endpoints.UPDATE_DATA);
		
		response.body().prettyPrint();
		
		ReusableMethods.verifyStatusCodeis(response,200);
		System.out.println("Data has been updated");
		String status = ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status, "success");
	}
}
