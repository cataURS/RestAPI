package HomeworkRest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent_Tema9;
import utils.DataBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Homew9 extends BaseComponent_Tema9{
	
	String id;
	String token;
	
	@Test(priority = 1)
	public void UsrPassAuth() {
		
		System.out.println("--------------POST Authenticate-----------------");
		Response resp = doRequest("AUTH", "", DataBuilder.buildToken().toJSONString(), "");
		System.out.println(resp.asPrettyString());
		token = resp.jsonPath().getString("token");
		id = resp.jsonPath().getString("id");
		assertThat(token, is(not(nullValue())));
		assertThat(id, is(not(nullValue())));
	}
	
	@Test(priority = 2)
	public void createTodoInvalid() {
		
		System.out.println("--------------POST Todo Invalid-----------------");
		Response resp = doRequest("POST", "", DataBuilder.buildTodo().toJSONString(), "");	
		System.out.println(resp.asPrettyString());
		assertThat(resp.asPrettyString(), containsStringIgnoringCase("Sorry, you are not authorized"));	
	}
	@Test(priority = 3)
	public void createTodoValid() {
		
		System.out.println("--------------POST Todo Valid-----------------");
		Response resp = doRequest("POSTV", "", DataBuilder.buildTodo().toJSONString(), token);
		System.out.println(resp.asPrettyString());
		id = resp.jsonPath().getString("id");
		System.out.println(resp.asPrettyString());
		System.out.println(id);
		assertThat(id, is(not(nullValue())));
	
	}
	
	@Test(priority = 4)
	public void deleteNoToken() {
		
		System.out.println("--------------DELETE No Body & No Token-----------------");
		Response resp = doRequest("DELETEno", id, "", "");	
		System.out.println(resp.asPrettyString());
		assertThat(resp.toString(), containsString("Sorry, you are not authorized"));	
	
	}
	
	@Test(priority = 5)
	public void deleteWrongToken() {
		
		System.out.println("--------------DELETE No Body & Wrong Token-----------------");
		Response resp = doRequest("DELETEtok", id, "", "63d0518d94b2f000169d6333");
		System.out.println(resp.asPrettyString());
		assertThat(resp.toString(), containsString("Wrong token"));
		
		
	}
	
	@Test(priority = 6)
	public void deleteValid() {
		
		System.out.println("--------------DELETE Valid-----------------");
		Response resp = doRequest("DELETEtok", id, "", token);	
		System.out.println(resp.asPrettyString());
		assertThat(resp.toString(), containsString("Event deleted"));
		
		
	}
	
}