package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

public class EmptyCol extends TypeSafeMatcher<String> {

	@Override
	public void describeTo(Description description) {
		description.appendText("expected empty collection but got :");
		
	}

	@Override
	protected boolean matchesSafely(String item) {
		try {
			Integer.parseInt(item);
			return true;
		}catch(NumberFormatException e) {
			
			return false;
		}
	}
	
	public static Matcher<String> numberOnly(){
		return new EmptyCol();
	}
	
	

}