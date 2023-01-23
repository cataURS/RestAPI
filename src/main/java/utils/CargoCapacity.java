package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CargoCapacity extends TypeSafeMatcher<Integer>{

	@Override
	public void describeTo(Description description) {
			description.appendText("expected valid cargo ");
		
	}

	@Override
	protected boolean matchesSafely(Integer item) {
		
		return item>25000000;
	}
	
	public static Matcher<Integer> validCargo(){
		return new CargoCapacity();
	}

}