package utils;

import org.testng.annotations.BeforeClass;
import org.testng.reporters.Files;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.File;

public class BaseComponent {
	
	
	@BeforeClass
	public void setup() {
		
		//RestAssured.baseURI = "https://keytrcrud.herokuapp.com/";
		
		//Needed for Homework3
		//RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/";
		
		//Needed for Homework5
		//RestAssured.baseURI = "https://api.instantwebtools.net/";
		
		//needed for Homework6
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/";

	}
	
	
	public static Response doPostRequest(String path, String body, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
					body(body).
				when().
					post(path).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
		
	}
	
	public static Response doPostRequest2(String path, File fisier, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
					body(fisier).
				when().
					post(path).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
		
	}
	
	public static Response doGetRequest(String path, String id, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
				when().
					get(path + id).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
	}
	
	public static Response doPutRequest(String path, String id, String body, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
					body(body).
				when().
					put(path + id).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
		
	}
	
	public static Response doDeleteRequest(String path, String id, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
				when().
					delete(path + id).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
	}

}