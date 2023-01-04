package HomeworkRest;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseComponent;

public class Homew4 extends BaseComponent{
	
	String id;
	
	@Test(priority=1)
	public void ppostUser() {
		
		JSONObject body =  new JSONObject();
		Faker faker = new Faker();
		
		body.put("name", faker.name().firstName());
		body.put("email", faker.internet().emailAddress());
		body.put("age", faker.number().numberBetween(5, 130));
		body.put("gender", "m");
		
		Response response = RestAssured
				.given()
					.contentType(ContentType.JSON)
					.body(body.toJSONString())
				.when()
					.post("api/users")
				.then()
					.assertThat().statusCode(201)
					.extract().response();
		
		assertEquals(response.jsonPath().getString("msg"), "Successfully added!");
		id = response.jsonPath().getString("result._id");
		
	}
	
	@Test(priority=2)
	public void ggetUser() {
		
		Response response = RestAssured
				.when()
					.get("api/users/"+id)
				.then()
					.assertThat().statusCode(200)
					.extract().response();
	}
	
	@Test(priority=3)
	public void ddeleteUser() {
		
		Response response = doDeleteRequest("api/users/", id, 200);
		assertEquals(response.jsonPath().get("result._id"), id);
		assertEquals(response.jsonPath().getString("msg"), "It has been deleted.");
	}
	
}
