package utils;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseComponent2 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		//use for Homework 6_2
		 requestSpec =  new RequestSpecBuilder()
				.setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
				
		
		//Use for Homework 6_1
		/*
		requestSpec =  new RequestSpecBuilder()
				.setBaseUri("https://fakerestapi.azurewebsites.net/")
				.setBasePath("api/v1/Books")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
				*/
	}
	
	@BeforeClass
	public static void createResponseSpecification() {
		
		responseSpec =  new ResponseSpecBuilder()
				.expectStatusCode(anyOf(is(200),is(201),is(204)))
				.build();	
	}
	
public static Response doPostRequest(String path, String todo) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(todo).
				when().
					post(path).
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
		
	}
	
	public static Response doPutRequest(String id, String body) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(body).
				when().
					put(id).
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
		
	}
	
	
	public static Response doGetOneRequest(String id) {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					get(id).
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
	}
	
	public static Response doGetAllRequest() {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					get().
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
	}
	
	
	public static Response doDeleteRequest(String path, String id) {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					delete(path + id).
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
	}
	

}