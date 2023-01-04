package HomeworkRest;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;


import io.restassured.response.Response;
import utils.BaseComponent;

public class Homew3 extends BaseComponent{
	
	String id;
	
	@Test(priority = 1)
	public void postBooks() {
		
		File data_file =  new File("file.json");
		
		Response respose = doPostRequest("api/v1/Books", data_file, 200);
		id = respose.jsonPath().getString("id");
		//un fel de reverse engineering
		data_file.toString().contains(id);
		//nu am stiut sa citesc ID ul din fisier ca sa fac un assertEquals
		//daca se poate citi asa, ca pe net am vazut ca ar trebui parsat fisierul ca JSON object inainte
		
		
	}

}
