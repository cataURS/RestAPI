package HomeworkRest;

import java.io.File;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent3;
import static utils.NumberChecker.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class Homew7 extends BaseComponent3{
	
	@Test(priority=1)
	public void getPeople() {
		
		Response response = doGetAllRequest();
		
		JsonPath jsonPath = response.jsonPath();
		
		//System.out.println(response.asPrettyString());
		String name = jsonPath.getString("name");
		assertThat(name,is(equalTo("Luke Skywalker")));
		
		String height = jsonPath.getString("height");
		assertThat(height,is(greaterThan("171")));
		
		String mass = jsonPath.getString("mass");
		assertThat(mass,is(lessThan("80")));
		
		String hair = jsonPath.getString("hair_color");
		String skin = jsonPath.getString("skin_color");
		String eye = jsonPath.getString("eye_color");
		
		//assertThat((hair,is(equalToIgnoringCase("blond"))), (skin,is(equalTo("fair"))), (eye,is(equalTo("blue"))));
		//ma dau batut aici, nici pe netnu prea gasesc multe explicatii
		
		
		String byear = jsonPath.getString("birth_year");
		assertThat(byear,is(not(numberOnly())));
		
		List<String> species = jsonPath.getList("species");
		assertThat(species, is(emptyCollectionOf(String.class)));
		
		List<String> starships = jsonPath.getList("starships");
		List<String> vehicles = jsonPath.getList("vehicles");
		assertThat(starships, hasSize(2));
		assertThat(vehicles, hasSize(2));
		
		assertThat(starships, is(not(equalTo(vehicles))));

		
		
		
		
		
		
		
	}

}
