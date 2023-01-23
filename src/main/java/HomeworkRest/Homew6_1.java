package HomeworkRest;

import java.util.List;
import static org.testng.Assert.assertEquals;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;

public class Homew6_1 extends BaseComponent2{
	
	
	@Test
	public void getBooks() {
		
		Response result = doGetAllRequest();
		
		//System.out.println(result.asString());
		
		JsonPath jsonPath = result.jsonPath();
		List<String> filterBooks = jsonPath.getList("findAll{it.pageCount >1000 & it.pageCount <2000}.title");
		System.out.println(filterBooks);
		assertEquals(filterBooks.size(), 9);
		//daca foloseam <>= erau 11 asa ca am ramas la 9 :))
		
	}
	
}
