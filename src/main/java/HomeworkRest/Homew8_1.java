package HomeworkRest;

import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import utils.BaseComponent3;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;;


public class Homew8_1 extends BaseComponent3{
	
	@Test(priority=1)
	public void correctSchema() {
		
		Response result = doGetAllRequest();
		
		System.out.println(result.asString());
		
		assertThat(result.asString(), matchesJsonSchemaInClasspath("schema2.json"));
		
		
	}
	
	@Test(priority=2)
	public void invalidSchema() {
		
		Response result = doGetAllRequest();
		
		System.out.println(result.asString());
		
		assertThat(result.asString(), matchesJsonSchemaInClasspath("schema3.json"));
		
	}

}
