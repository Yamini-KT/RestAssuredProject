package com.tests.helpers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.test.constants.Endpoints;
import com.test.constants.SourcePath;
import com.test.models.AddUserPOJO;
import com.test.models.DeleteUserPOJO;
import com.test.models.LoginObjectPOJO;
import com.test.models.UpdateUserPOJO;
import com.test.models.UserPOJO;
import com.test.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UserServiceHelper {

	private static Response response;
	private static String token;

	public static String getBaseURI(){
		String baseURI = Utils.getConfigProperty("baseURI");;
		return baseURI;
	}

	public static Response LoginToApplication(){
		String username = Utils.getConfigProperty("username");
		String password = Utils.getConfigProperty("password");
		LoginObjectPOJO lo = new LoginObjectPOJO(username, password);
		response = RestAssured.given().contentType("application/json")
				.body(lo)
//				.when().log().all()
				.when()
				.post(Endpoints.LOGIN);
//		response.body().prettyPrint();
		return response;
	}

	public static String getToken(){
		response = LoginToApplication();
		ReusableMethods.verifyStatusCodeis(response, 201);
		token = response.jsonPath().get("[0].token");
		return token;
	}

	public static List<UserPOJO> getUserData(){
		String token = getToken();
		Header header =new Header("token",token);
		Response response = RestAssured.given().header(header)
//				.when().log().all()
				.when()
				.get(Endpoints.GET_DATA);
//		response.body().prettyPrint();
		ReusableMethods.validateJSONSchema(response, SourcePath.Tekarch_GetUser_Json_Path);
		ReusableMethods.verifyStatusCodeis(response, 200);
		UserPOJO[] listOfUsers= response.as(UserPOJO[].class);//deserialization
		return Arrays.asList(listOfUsers);
	}

	public static Response addUserData() throws IOException {
		AddUserPOJO ad = ReusableMethods.getUserDataToAdd();
		String token = getToken();
		Header header =new Header("token",token);
		Response response = RestAssured.given()
				.contentType("application/json")
				.header(header)
				.body(ad)
//				.when().log().all()
				.when()
				.post(Endpoints.ADD_DATA);
//		response.body().prettyPrint();
		return response;
	}


	public static Response updateUserData() throws IOException {
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
//		response.body().prettyPrint();
		return response;
	}

	public static Response deleteUserData() throws IOException {
		DeleteUserPOJO du = ReusableMethods.getUserDataToDelete();
		String token = getToken();
		Header header =new Header("token",token);
		Response response = RestAssured.given()
				.contentType("application/json")
				.header(header)
				.body(du)
//				.when().log().all()
				.when()
				.delete(Endpoints.DELETE_DATA);
//		response.body().prettyPrint();
		return response;
	}
}
