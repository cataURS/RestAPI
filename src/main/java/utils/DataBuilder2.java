package utils;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder2 {

	public static JSONObject buildTodo() {
		
		JSONObject bodyBuilder =  new JSONObject();
		Faker faker = new Faker();
		
		bodyBuilder.put("title", faker.backToTheFuture().character());
		bodyBuilder.put("body", faker.chuckNorris().fact());
		
		try {

			FileWriter myFileWriter = new FileWriter("todo.json");
			myFileWriter.write(bodyBuilder.toJSONString());
			myFileWriter.close();
			System.out.println("Succesfully wrote to file!");
		} catch (IOException e) {

			System.out.println("Cannot write to file!");
			e.printStackTrace();

		}
		
		System.out.println(bodyBuilder);
		return bodyBuilder;
	}
	
	
}