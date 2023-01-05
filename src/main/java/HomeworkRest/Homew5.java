package HomeworkRest;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import utils.BaseComponent;

public class Homew5 extends BaseComponent{

	String id;
	String Fake_name;
	
	JSONObject body =  new JSONObject();
	
	@Test(priority=1)
	public void postCustomer() {
		
		Faker faker = new Faker();
		
		Fake_name = faker.name().firstName();
		body.put("name", Fake_name);
		body.put("trips", 250);
		body.put("airline", 1981);
		
		Response response = doPostRequest("v1/passenger", body.toJSONString(), 200);
		assertEquals(response.jsonPath().getString("name"), Fake_name);
		id = response.jsonPath().getString("_id");
		
	}
	
	@Test(priority=2)
	public void getCustomer() {
		
		Response response = doGetRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().get("airline.name[0]"), "Tarom");
		
	}
	
	@Test(priority=3)
	public void putCustomer() {
		
		body.put("trips", 300);
		body.put("airline", 1);
		
		Response response = doPutRequest("v1/passenger/", id, body.toJSONString(), 200);
		System.out.println(id);
		assertEquals(response.jsonPath().getString("message"), "Passenger data put successfully completed.");
		
	}
	
	@Test(priority=4)
	public void assertUpdate() {
		
		Response response = doGetRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().get("airline.name[0]"), "Quatar Airways");
		assertEquals(response.jsonPath().get("trips"), 300);
		assertEquals(response.jsonPath().get("name"), Fake_name);
		
	}
	
	@Test(priority=5)
	public void deleteCustomer() {
		
		Response response = doDeleteRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().getString("message"), "Passenger data deleted successfully.");
	}
	
}
