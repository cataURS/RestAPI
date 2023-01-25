package utils;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class BaseComponent_Tema9 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec =  new RequestSpecBuilder()
				.setBaseUri("https://dev-todo.herokuapp.com/")
				.setBasePath("api/")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
		
	}
	
	@BeforeClass
	public static void createResponseSpecification() {
		
		responseSpec =  new ResponseSpecBuilder()
				//.expectStatusCode(anyOf(is(200),is(201),is(204)))
				.build();	
	}
	
	public static Response doRequest(String method, String id, String  body, String token) {
		
		Response result = null;
		
		switch(method.toUpperCase()) {
		
		case "GET" : result = given().spec(requestSpec).get(id);
			break;
		case "POST": result = given().spec(requestSpec).body(body).post("auth/save");
			break;
		case "POSTV": result = given().spec(requestSpec.header("Token", token)).body(body).post("auth/save");
			break;
		case "AUTH": result = given().spec(requestSpec).body(body).post("auth");
			break;
		case "PUT" : result = given().spec(requestSpec).body(body).put("todos/"+id);
			break;
		case "DELETEtok" : result = given().spec(requestSpec.header("Token", token)).delete("auth/delete/"+id);	
			break;
		case "DELETEno" : result = given().spec(requestSpec).delete("auth/delete/"+id);
		
		}
		if(result != null) {
			result = result.then().spec(responseSpec).extract().response();
		}
		
		return result;
		
	}
	

}