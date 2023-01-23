package HomeworkRest;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.collection.IsIn;
import org.hamcrest.number.IsCloseTo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.NumberChecker.numberOnly;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.CargoCapacity.*;


public class Homew8_2{
	
	String starship_name = "https://swapi.dev/api/starships/3/";
	
	@Test(priority=1)
	public void correctSchema() {
		
		Response star = given()
				.get(starship_name)
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		
		JsonPath starship = star.jsonPath();
		
		Response film22 = given()
				.get("https://swapi.dev/api/films/2/")
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		JsonPath film2 = film22.jsonPath();
		
		Response film55 = given()
				.get("https://swapi.dev/api/films/5/")
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		JsonPath film5 = film55.jsonPath();
		
		Response film66 = given()
				.get("https://swapi.dev/api/films/6/")
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		JsonPath film6 = film66.jsonPath();
		
		List<String> film2_ships = film2.getList("starships");
		List<String> film5_ships = film5.getList("starships");
		List<String> film6_ships = film6.getList("starships");
		
		assertThat(starship_name, is(in(film2_ships).or((in(film5_ships)).or(in(film6_ships)));
		//ma bat parantezele, rage quit 
	
		int length = starship.getInt("length");
		assertThat(length, is(closeTo(1500, 200)));
		//am incercat sa aplic ce ziceai pt diametru aici ca nu am gasit nici un diametru
		//nu va merge ca e string ce avem in resp, dar poti sa imi explici ca idee daca e gresit
		
		List<String> pilots = starship.getList("pilots");
		assertThat(pilots, is(emptyCollectionOf(String.class)));
		
		List<String> films = starship.getList("films");
		assertThat(films, is(not(emptyCollectionOf(String.class))));
		
		String model = starship.getString("model");
		assertThat(model, both(containsString("Imperial")).and(containsString("Destroyer")));
		
		int cargo_cap = starship.getInt("cargo_capacity");
		assertThat(cargo_cap, validCargo());
		
	}

}
