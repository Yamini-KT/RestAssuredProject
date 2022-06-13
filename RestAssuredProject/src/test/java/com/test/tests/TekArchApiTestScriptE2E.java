package com.test.tests;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.constants.SourcePath;
import com.test.models.UserPOJO;
import com.tests.helpers.ReusableMethods;
import com.tests.helpers.UserServiceHelper;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TekArchApiTestScriptE2E extends UserServiceHelper {
	
	@BeforeMethod
	public static void baseUri() {
		RestAssured.baseURI = getBaseURI();
	}
	
	@Test(priority =1, enabled = true)
	public static void TC_001_validLogin() {
		Response response = LoginToApplication();
		ReusableMethods.validateJSONSchema(response, SourcePath.Tekarch_Login_Json_Path);
		ReusableMethods.verifyStatusCodeis(response, 201);
		System.out.println("Successfully Logged in.");
		String token = response.jsonPath().get("[0].token");
		System.out.println("token= " + token);
	}
	
	@Test(priority=2, enabled =true)
	public static void TC_002_addUserData() throws IOException {
		Response res = addUserData();
		ReusableMethods.verifyStatusCodeis(res,201);
		System.out.println("Data has been added");
		String status=ReusableMethods.getJsonPathData(res, "status");
		Assert.assertEquals(status, "success");
	}
	

	@Test(priority =3, enabled =true)
	public static void TC_003_getUserData() {
		List<UserPOJO> listofUsers = getUserData();
		System.out.println("Data has been retrieved.");
        System.out.println("first account number= " + listofUsers.get(0).getAccountno());
        System.out.println("second account number= " + listofUsers.get(1).getAccountno());
        //return listofUsers
	} 
	
	@Test(priority=4, enabled =true)
	public static void TC_004_updateUserData() throws IOException {
		Response response = updateUserData();
		ReusableMethods.verifyStatusCodeis(response,200);
		System.out.println("Data has been updated");
		String status = ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status, "success");
	}
	
	@Test(priority=5, enabled =true)
	public static void TC_005_deleteUserData() throws IOException {
		Response response = deleteUserData();
		ReusableMethods.verifyStatusCodeis(response,200);
		System.out.println("Data has been deleted");
		String status = ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status, "success");
	}
}
