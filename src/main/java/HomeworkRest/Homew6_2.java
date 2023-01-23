package HomeworkRest;

import java.io.File;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder2;

public class Homew6_2 extends BaseComponent2{
	
	String titleS;
	String id;
	
	@Test(priority=1)
	public void postTodo() {
		
		Response response = doPostRequest("/save", DataBuilder2.buildTodo().toJSONString());
		
	}
	
	@Test(priority=2)
	public void getTodo() {
		
		Response response = doGetAllRequest();
		
		File jsonFile = new File("todo.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		titleS = jsonPath.getString("title");
		System.out.println(titleS);
		
		JsonPath jsonPath2 = response.jsonPath();
		id = jsonPath2.getString("find{it.title == '"+ titleS +"'}._id");
		System.out.println(id);
		//System.out.println(response.asPrettyString());
		
	}
	
	@Test(priority=3)
	public void delTodo() {
		
		Response response = doDeleteRequest("/delete/", id);
		assertTrue(response.asString().contains("Event deleted"));
		
		
	}
	
}
