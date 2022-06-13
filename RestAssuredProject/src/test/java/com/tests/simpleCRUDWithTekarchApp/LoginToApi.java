package com.tests.simpleCRUDWithTekarchApp;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tests.helpers.ReusableMethods;
import com.tests.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginToApi extends UserServiceHelper {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = getBaseURI();
	}
	
	@Test
	public static void testLoginToTekarchApi() throws IOException {
		Response response=LoginToApplication();
		response.then().log().all();
		response.body().prettyPrint();
		ReusableMethods.verifyStatusCodeis(response, HttpStatus.SC_CREATED);
		String token = response.jsonPath().get("[0].token");
		System.out.println("TOKEN ="+token);	
		}

}
