package com.test.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"accountno",
	"departmentno",
	"salary",
	"pincode"
})

//@Generated("jsonschema2pojo")
public class AddUserPOJO {

	@JsonProperty("accountno")
	private String accountno;
	@JsonProperty("departmentno")
	private String departmentno;
	@JsonProperty("salary")
	private String salary;
	@JsonProperty("pincode")
	private String pincode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("accountno")	
	public String getacctNo() {
		return accountno;
	}

	@JsonProperty("accountno")	
	public void setacctNo(String accountno) {
		this.accountno=accountno;
	}

	@JsonProperty("departmentno")	
	public String getdeptNo() {
		return departmentno;
	}

	@JsonProperty("departmentno")	
	public void setdeptNo(String departmentno) {
		this.departmentno=departmentno;
	}

	@JsonProperty("salary")	
	public String getsalary() {
		return salary;
	}

	@JsonProperty("salary")	
	public void setsalary(String salary) {
		this.salary=salary;
	}

	@JsonProperty("pincode")	
	public String getpincode() {
		return pincode;
	}

	@JsonProperty("pincode")	
	public void setpincode(String pincode) {
		this.pincode=pincode;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}



