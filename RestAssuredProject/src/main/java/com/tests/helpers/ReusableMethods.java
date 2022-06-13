package com.tests.helpers;


import java.util.concurrent.TimeUnit;

import com.test.models.AddUserPOJO;
import com.test.models.DeleteUserPOJO;
import com.test.models.UpdateUserPOJO;
import com.test.utils.Utils;


import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ReusableMethods {

	public static AddUserPOJO getUserDataToAdd() {
		AddUserPOJO user = new AddUserPOJO();
		user.setacctNo(Utils.getConfigProperty("to_add_accountno"));
		user.setdeptNo(Utils.getConfigProperty("to_add_departmentno"));
		user.setsalary(Utils.getConfigProperty("to_add_salary"));
		user.setpincode(Utils.getConfigProperty("to_add_pincode"));
		return user;
	}

	public static DeleteUserPOJO getUserDataToDelete() {
		DeleteUserPOJO user = new DeleteUserPOJO();
		user.setId(Utils.getConfigProperty("to_delete_id"));
		user.setUserid(Utils.getConfigProperty("to_delete_userid"));
		return user;
	}

	public static UpdateUserPOJO getUserDataToUpdate() {
		UpdateUserPOJO user = new UpdateUserPOJO();
		user.setAccountno(Utils.getConfigProperty("to_update_accountno"));
		user.setDepartmentno(Utils.getConfigProperty("to_update_departmentno"));
		user.setSalary(Utils.getConfigProperty("to_update_salary"));
		user.setPincode(Utils.getConfigProperty("to_update_pincode"));
		user.setUserid(Utils.getConfigProperty("to_update_userid"));
		user.setId(Utils.getConfigProperty("to_update_id"));
		return user;
	}


	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	public String getContentType(Response response) {
		return response.getContentType();
	}

	public long getTimeIn(Response response, TimeUnit unit) {
		return response.getTimeIn(unit);
	}

	public static void verifyStatusCodeis(Response response, int statuscode) {
		response.then().statusCode(statuscode);
	}

	public static String getJsonPathData(Response response, String name) {
		return response.jsonPath().get(name);
	}

	public static void validateJSONSchema(Response response, String jsonFileName){
		response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonFileName));

	}
}
